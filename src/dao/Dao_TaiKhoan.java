package dao;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;
import utils.AES;

public class Dao_TaiKhoan {
	public List<TaiKhoan> readFromTaiKhoanSQL() {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    List<TaiKhoan> listTaiKhoan = new ArrayList<>();
	    try {
	        connect = ConnectDB.getConnection();
	        Statement stt = connect.createStatement();
	        ResultSet rs = stt.executeQuery("SELECT * FROM TaiKhoan");
	        while (rs.next()) {
	            String tenTaiKhoan = rs.getString("tenTaiKhoan");
	            String matKhau = rs.getString("matKhau");

	            // Tạo đối tượng TaiKhoan và thêm vào danh sách
	            TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau);
	            listTaiKhoan.add(taiKhoan);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (connect != null) {
	                ConnectDB.close(connect);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    if (listTaiKhoan.size() == 0) {
	    	return null;
	    }
	    return listTaiKhoan;
	}
	public boolean addTaiKhoan(TaiKhoan TK) {
        Connection connect = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            connect = ConnectDB.getConnection();
            String sql = "INSERT INTO TaiKhoan (tenTaiKhoan, matKhau) VALUES (?, ?)";
            stmt = connect.prepareStatement(sql);
            stmt.setString(1, TK.getTenTaiKhoan());
            stmt.setString(2, TK.getMatKhau());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connect != null) ConnectDB.close(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows > 0;
    }
	public boolean changePassword(String tenTaiKhoan, String matKhauHienTai, String matKhauMoi, Component parentComponent) throws Exception {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    int rows = 0;
	    try {
	        connect = ConnectDB.getConnection();

	        // Kiểm tra mật khẩu hiện tại
	        String encryptedMatKhauHienTai = (new AES()).encrypt(matKhauHienTai, tenTaiKhoan);
	        String sqlCheck = "SELECT * FROM TaiKhoan WHERE tenTaiKhoan = ? AND matKhau = ?";
	        PreparedStatement checkStmt = connect.prepareStatement(sqlCheck);
	        checkStmt.setString(1, tenTaiKhoan);
	        checkStmt.setString(2, encryptedMatKhauHienTai);
	        ResultSet rs = checkStmt.executeQuery();
	        if (!rs.next()) {
	            // Nếu không tìm thấy kết quả, tức là mật khẩu hiện tại không đúng
	            JOptionPane.showMessageDialog(parentComponent, "Mật khẩu hiện tại không đúng");
	            return false;
	        }

	        // Nếu mật khẩu hiện tại đúng, tiến hành cập nhật mật khẩu mới
	        String sqlUpdate = "UPDATE TaiKhoan SET matKhau = ? WHERE tenTaiKhoan = ?";
	        stmt = connect.prepareStatement(sqlUpdate);
	        String encryptedMatKhauMoi = (new AES()).encrypt(matKhauMoi, tenTaiKhoan);
	        stmt.setString(1, encryptedMatKhauMoi);
	        stmt.setString(2, tenTaiKhoan);
	        rows = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (connect != null) ConnectDB.close(connect);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return rows > 0;
	}
	
	public NhanVien authenticateTaiKhoanForNhanVien(TaiKhoan tk) {
		Connection connect = null;
	    PreparedStatement stmt = null;
	    NhanVien nv = null;
	    try {
	        connect = ConnectDB.getConnection();
	        String sql = "SELECT * FROM TaiKhoan WHERE tenTaiKhoan = ? AND matKhau = ?";
	        stmt = connect.prepareStatement(sql);
	        stmt.setString(1, tk.getTenTaiKhoan());
	        String mkEncrypt = null;
	        try {
				mkEncrypt = (new AES()).encrypt(tk.getMatKhau(), tk.getTenTaiKhoan());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        stmt.setString(2, mkEncrypt);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	        	nv = (new Dao_NhanVien()).findNhanVienByMaNV(tk.getTenTaiKhoan());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (connect != null) ConnectDB.close(connect);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return nv;
	}

}
