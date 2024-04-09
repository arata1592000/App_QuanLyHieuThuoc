package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.Thuoc;

public class Dao_Thuoc {
	public Dao_Thuoc() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Thuoc> readThuocFromSQL(){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<Thuoc> listThuoc = new ArrayList();
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM Thuoc");
			while (rs.next()) {
				Thuoc thuoc = new Thuoc(rs.getString(1),
						rs.getString(2),
						rs.getDate(3).toLocalDate(),
						rs.getDate(4).toLocalDate(),
						rs.getDate(5).toLocalDate(),
						rs.getString(6),
						rs.getFloat(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10)
						
					);
				listThuoc.add(thuoc);
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
		return listThuoc;
	}
	
	public boolean addThuoc(Thuoc thuoc) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
	        connect = ConnectDB.getConnection();
			stmt = connect.prepareStatement("INSERT INTO Thuoc VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, thuoc.getMaThuoc());
			stmt.setString(2, thuoc.getTenThuoc());
			stmt.setTimestamp(3, Timestamp.valueOf(thuoc.getNgayNhapVe().atStartOfDay()));
			stmt.setTimestamp(4, Timestamp.valueOf(thuoc.getNgayHetHan().atStartOfDay()));
			stmt.setTimestamp(5, Timestamp.valueOf(thuoc.getNgaySanXuat().atStartOfDay()));		
			stmt.setString(6, thuoc.getNoiSanXuat());
			stmt.setFloat(7, thuoc.getGia());
			stmt.setString(8, thuoc.getDonViTinh());
			stmt.setString(9, thuoc.getThanhPhan());
			stmt.setInt(10, thuoc.getSoLuong());
			stmt.setString(11, thuoc.getKhuyenMai().getMaKM());

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
	

	public boolean removeThuoc(String maThuoc) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
			connect = ConnectDB.getConnection();
			stmt = connect.prepareStatement("DELETE FROM Thuoc WHERE maThuoc = ?");
			stmt.setString(1, maThuoc);
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
	public Thuoc findThuocByMaThuoc(String maThuoc) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    Thuoc thuoc = null;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT * FROM Thuoc WHERE maThuoc = ?");
	        stmt.setString(1, maThuoc); // Thiết lập giá trị cho tham số maThuoc

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            thuoc = new Thuoc(rs.getString(1),
	                    rs.getString(2),
	                    rs.getDate(3).toLocalDate(),
	                    rs.getDate(4).toLocalDate(),
	                    rs.getDate(5).toLocalDate(),
	                    rs.getString(6),
	                    rs.getFloat(7),
	                    rs.getString(8),
	                    rs.getString(9),
	                    rs.getInt(10)
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
	    return thuoc;
	}
}
