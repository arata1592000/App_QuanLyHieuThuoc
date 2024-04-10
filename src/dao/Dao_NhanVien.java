package dao;

import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import database.ConnectDB;
import entity.NhanVien;
import entity.Thuoc;

public class Dao_NhanVien {
	
	public List<NhanVien> readFromSQL(){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<NhanVien> listNhanVien = new ArrayList();
	    try {
	    	connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM NhanVien");
			while(rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				String gioiTinh = rs.getString(3);
				String soDT = rs.getString(4);
				LocalDate ngaySinh = rs.getDate(5).toLocalDate();
				LocalDate ngayVaoLam = rs.getDate(6).toLocalDate();
				String chucVu = rs.getString(7);
				String soCCCD = rs.getString(8);
				String diaChi = rs.getString(9);
				String trangThai = rs.getNString(10);

				NhanVien nhanVien = new NhanVien(maNV,hoTen,gioiTinh,soDT,ngaySinh,ngayVaoLam,chucVu,soCCCD,diaChi,trangThai);
				listNhanVien.add(nhanVien);
			}
	    }catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
	            if (stmt != null) stmt.close();
	            if (connect != null) {
	            	ConnectDB.close(connect);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	    
		return listNhanVien;
	}

	public boolean addNhanVien(NhanVien nhanVien) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("INSERT INTO NhanVien VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
	        stmt.setString(1, nhanVien.getMaNV());
	        stmt.setString(2, nhanVien.getHoTen());
	        stmt.setString(3, nhanVien.getGioiTinh());
	        stmt.setString(4, nhanVien.getSoDienThoai());
	        stmt.setTimestamp(5, Timestamp.valueOf(nhanVien.getNgaySinh().atStartOfDay()));
	        stmt.setTimestamp(6, Timestamp.valueOf(nhanVien.getNgayVaoLam().atStartOfDay()));
	        stmt.setString(7, nhanVien.getChucVu());
	        stmt.setString(8, nhanVien.getSoCCCD());
	        stmt.setString(9, nhanVien.getDiaChi());
	        stmt.setString(10, nhanVien.getTrangThai());
	        stmt.setBytes(11, nhanVien.getAnh());

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
	public boolean removeNhanVien(String maNV) {
	Connection connect = null;
    PreparedStatement stmt = null;
	int n = 0;
	try {
		connect = ConnectDB.getConnection();
		stmt = connect.prepareStatement("DELETE FROM NhanVien WHERE maNV = ?");
		stmt.setString(1, maNV);
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
	public NhanVien findNhanVien(String maNhanVien) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    NhanVien nhanVien = null;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ?");
	        stmt.setString(1, maNhanVien);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            nhanVien = new NhanVien(
	                rs.getString(1),
	                rs.getString(2),
	                rs.getString(3),
	                rs.getString(4),
	                rs.getDate(5).toLocalDate(),
	                rs.getDate(6).toLocalDate(),
	                rs.getString(7),
	                rs.getString(8),
	                rs.getString(9),
	                rs.getString(10)
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
	    return nhanVien;
	}

	public boolean updateNhanVien(NhanVien nhanVien) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("UPDATE NhanVien SET hoTen=?, gioiTinh=?, soDienThoai=?, ngaySinh=?, ngayVaoLam=?, chucVu=?, soCCCD=?, diaChi=?, trangThai=?,anh=? WHERE maNV=?");
	        stmt.setString(1, nhanVien.getHoTen());
	        stmt.setString(2, nhanVien.getGioiTinh());
	        stmt.setString(3, nhanVien.getSoDienThoai());
	        stmt.setTimestamp(4, Timestamp.valueOf(nhanVien.getNgaySinh().atStartOfDay()));
	        stmt.setTimestamp(5, Timestamp.valueOf(nhanVien.getNgayVaoLam().atStartOfDay()));
	        stmt.setString(6, nhanVien.getChucVu());
	        stmt.setString(7, nhanVien.getSoCCCD());
	        stmt.setString(8, nhanVien.getDiaChi());
	        stmt.setString(9, nhanVien.getTrangThai());
	        stmt.setBytes(10,nhanVien.getAnh());
	        stmt.setString(11, nhanVien.getMaNV());

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
	public NhanVien findNhanVienByMaNV(String maNV) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    NhanVien nv = null;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ?");
	        stmt.setString(1, maNV); // Thiết lập giá trị cho tham số maThuoc

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            nv = new NhanVien(rs.getString(1),
	                    rs.getString(2),
	                    rs.getString(3),
	                    rs.getString(4),
	                    rs.getDate(5).toLocalDate(),
	                    rs.getDate(6).toLocalDate(),
	                    rs.getString(7),
	                    rs.getString(8),
	                    rs.getString(9),
	                    rs.getString(10)
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
	    return nv;
	}
}
