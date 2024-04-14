package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.KhuyenMai;
import entity.NhanVien;


public class Dao_KhuyenMai {
	public Dao_KhuyenMai() {
		// TODO Auto-generated constructor stub
	}
	
	public List<KhuyenMai> readKMFromSQL(){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<KhuyenMai> listKM = new ArrayList();
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM KhuyenMai");
			while (rs.next()) {
				KhuyenMai km = new KhuyenMai(rs.getString(1),
						rs.getDate(2).toLocalDate(),
						rs.getDate(3).toLocalDate(),
						rs.getFloat(4),
						rs.getString(5)
						
					);
				listKM.add(km);
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
		return listKM;
	}
	
	public boolean addKM(KhuyenMai khuyenMai) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
	        connect = ConnectDB.getConnection();
			stmt = connect.prepareStatement("INSERT INTO KhuyenMai VALUES(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, khuyenMai.getMaKM());
			stmt.setTimestamp(2, Timestamp.valueOf(khuyenMai.getNgayBatDau().atStartOfDay()));
			stmt.setTimestamp(3, Timestamp.valueOf(khuyenMai.getNgayKetThuc().atStartOfDay()));
			stmt.setFloat(4, khuyenMai.getTyLeKM());
			stmt.setString(5, khuyenMai.getLoaiKM());
			stmt.setFloat(6, khuyenMai.getGiaTriHD());
			
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
	
	public boolean setListThuocDiscount(String maKM, List<String> listMaThuocDiscount, List<String> listMaThuocNoDiscount) 
	{
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
	        connect = ConnectDB.getConnection();
	        for (String maThuoc : listMaThuocDiscount) {
	        	stmt = connect.prepareStatement("UPDATE Thuoc\r\n"
						+ "SET maKMSP = ?\r\n"
						+ "WHERE maThuoc = ?;");
				stmt.setString(1, maKM);
				stmt.setString(2, maThuoc);
				n = stmt.executeUpdate();
	        }
	        for(String maThuoc : listMaThuocNoDiscount)
	        {
	        	stmt = connect.prepareStatement("UPDATE Thuoc\r\n"
						+ "SET maKMSP = ?\r\n"
						+ "WHERE maThuoc = ?;");
	        	stmt.setString(1, null);
				stmt.setString(2, maThuoc);
				n = stmt.executeUpdate();
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
		return true;
	}
	public boolean updateKhuyenMai(KhuyenMai KM) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("UPDATE KhuyenMai SET ngayBatDau=?,ngayKetThuc=?,tyleKM=?,loaiKM=? WHERE maKM=?");
	        stmt.setTimestamp(1, Timestamp.valueOf(KM.getNgayBatDau().atStartOfDay()));
	        stmt.setTimestamp(2, Timestamp.valueOf(KM.getNgayKetThuc().atStartOfDay()));
	        stmt.setFloat(3, KM.getTyLeKM());
	        stmt.setString(4, KM.getLoaiKM());
	        stmt.setString(5, KM.getMaKM());

	        n = stmt.executeUpdate();
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
	    return n > 0;
	}
	public KhuyenMai findKhuyenMaiByID(String maKhuyenMai) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    KhuyenMai km = null;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT * FROM KhuyenMai WHERE maKM = ?");
	        stmt.setString(1, maKhuyenMai);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            km = new KhuyenMai(
	                rs.getString(1),
	                rs.getDate(2).toLocalDate(),
	                rs.getDate(3).toLocalDate(),
	                rs.getFloat(4),
	             
	                rs.getString(5)
	           
	            );
	        }
	    } catch (SQLException e) {
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
	    return km;
	}
	
}
