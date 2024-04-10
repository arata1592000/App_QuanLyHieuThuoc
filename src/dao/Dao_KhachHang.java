package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.KhachHang;
import entity.Thuoc;

public class Dao_KhachHang {
	public void Dao_KhachHang() {
		
	}
	
	public boolean addKhachHang(KhachHang kh) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
	        connect = ConnectDB.getConnection();
			stmt = connect.prepareStatement("INSERT INTO KhachHang VALUES(?, ?, ?)");
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getsDT());

			n = stmt.executeUpdate();
		}  catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Đóng kết nối và statement để tránh lãng phí tài nguyên
	        try {
	            if (stmt != null) stmt.close();
	            if (connect != null) {
	            	ConnectDB.close(connect);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return n>0;
	}
	public List<KhachHang> readKhachHangFromSQL(){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<KhachHang> listKH = new ArrayList();
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM KhachHang");
			while (rs.next()) {
				KhachHang kh = new KhachHang(rs.getString(1),
						rs.getString(2),
						rs.getString(3));
				listKH.add(kh);
			}
		}  catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Đóng kết nối và statement để tránh lãng phí tài nguyên
	        try {
	            if (stmt != null) stmt.close();
	            if (connect != null) {
	            	ConnectDB.close(connect);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		return listKH;
	}
	public KhachHang findKhachHangByMaKH(String maKH) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    KhachHang kh = null;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT * FROM KhachHang WHERE maKH = ?");
	        stmt.setString(1, maKH); // Thiết lập giá trị cho tham số maThuoc

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            kh = new KhachHang(rs.getString(1),
	                    rs.getString(2),
	                    rs.getString(3)
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Đóng kết nối và statement để tránh lãng phí tài nguyên
	        try {
	            if (stmt != null) stmt.close();
	            if (connect != null) {
	                ConnectDB.close(connect);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return kh;
	}
	
}
