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
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Thuoc;

public class Dao_HoaDon {
	public void Dao_HoaDon() {
		
	}
	
	public boolean addHoaDon(HoaDon hd) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
			
	        connect = ConnectDB.getConnection();
			stmt = connect.prepareStatement("INSERT INTO HoaDon VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setTimestamp(2, Timestamp.valueOf(hd.getNgayLap().atStartOfDay()));
			stmt.setString(3, hd.getNhanVien().getMaNV());
			(new Dao_KhachHang()).addKhachHang(hd.getKhachHang());
			stmt.setString(4, hd.getKhachHang().getMaKH());
			stmt.setFloat(5, hd.getTongTien());
			stmt.setString(6, hd.getLoaiHD());
			stmt.setString(7, hd.getKhuyenMai().getMaKM());
			stmt.setFloat(8, hd.getThue());
			stmt.setString(9, hd.getGhiChu());
			n = stmt.executeUpdate();
			if (addChiTietHoaDon(hd)) {
				return n>0;
			}else {
				removeHoaDon(hd.getMaHD());
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
		return n>0;
	}
	
	public boolean removeHoaDon(String maHD) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
			connect = ConnectDB.getConnection();
			stmt = connect.prepareStatement("DELETE FROM HoaDon WHERE maHD = ?");
			stmt.setString(1, maHD);
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
	
	public boolean addChiTietHoaDon(HoaDon hd) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
	        connect = ConnectDB.getConnection();
	        List<ChiTietHoaDon> listCTHD = hd.getChiTietHoaDon();
	        int hang = 0;
	        for (ChiTietHoaDon cthd : listCTHD) {
	        	hang++;
	        	stmt = connect.prepareStatement("INSERT INTO ChiTietHoaDon VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, hd.getMaHD());
				stmt.setInt(2, hang);
				stmt.setString(3, cthd.getTenThuoc());
				stmt.setInt(4, cthd.getSoLuong());
				stmt.setString(5, cthd.getDonViTinh());
				stmt.setFloat(6, cthd.getGia());
				stmt.setFloat(7, cthd.getKhuyenMai());
				stmt.setFloat(8, cthd.getTongTienSanPham());

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
		return n>0;
	}
	
	public List<ChiTietHoaDon> readChiTietHoaDonFromSQLByMaHD(String maHD){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<ChiTietHoaDon> listCTHD = new ArrayList();
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
	        stmt = connect.prepareStatement("SELECT * FROM ChiTietHoaDon WHERE maHD = ?");
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon(rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getFloat(5),
						rs.getFloat(6),
						rs.getFloat(7)
						);
				listCTHD.add(cthd);
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
		return listCTHD;
	}
	
	public List<HoaDon> readHoaDonFromSQL(){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<HoaDon> listHD = new ArrayList();
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM HoaDon");
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = (new Dao_KhachHang()).findKhachHangByMaKH(rs.getString(4));
				KhuyenMai km = new KhuyenMai();
				HoaDon hd = new HoaDon(rs.getString(1),
						rs.getDate(2).toLocalDate(),
						rs.getFloat(5),
						rs.getString(6),
						rs.getFloat(8),
						rs.getString(9)
					);
				hd.setNhanVien(nv);
				hd.setKhachHang(kh);
				hd.setKhuyenMai(km);
				listHD.add(hd);
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
		return listHD;
	}
	
//	public HoaDon findHoaDonByMaHD(String maHD) {
//	    Connection connect = null;
//	    PreparedStatement stmt = null;
//	    HoaDon hd = null;
//	    try {
//	        connect = ConnectDB.getConnection();
//	        stmt = connect.prepareStatement("SELECT * FROM HoaDon WHERE maHD = ?");
//	        stmt.setString(1, maHD); // Thiết lập giá trị cho tham số maThuoc
//
//	        ResultSet rs = stmt.executeQuery();
//	        if (rs.next()) {
//	            hd = new HoaDon(rs.getString(1),
//	                    rs.getDate(2).toLocalDate(),
//	                    rs.getDate(3).toLocalDate(),
//	                    rs.getDate(4).toLocalDate(),
//	                    rs.getDate(5).toLocalDate(),
//	                    rs.getString(6),
//	                    rs.getFloat(7),
//	                    rs.getString(8),
//	                    rs.getString(9),
//	                    rs.getInt(10)
//	            );
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	        // Đóng kết nối và statement để tránh lãng phí tài nguyên
//	        try {
//	            if (stmt != null) stmt.close();
//	            if (connect != null) {
//	                ConnectDB.close(connect);
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    return thuoc;
//	}
}
