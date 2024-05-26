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
import entity.ChiTietBangKetCa;
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
			stmt = connect.prepareStatement("INSERT INTO HoaDon VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setTimestamp(2, Timestamp.valueOf(hd.getNgayLap().atStartOfDay()));
			stmt.setString(3, hd.getNhanVien().getMaNV());
			stmt.setString(4, hd.getKhachHang().getMaKH());
			stmt.setFloat(5, hd.getTongTien());
			stmt.setString(6, hd.getLoaiHD());
			stmt.setString(7, hd.getKhuyenMai().getMaKM());
			stmt.setFloat(8, hd.getThue());
			stmt.setFloat(9, hd.getThanhTien());
			stmt.setString(10, hd.getPhuongThucTT());
			stmt.setFloat(11, hd.getTienKhachDua());
			stmt.setFloat(12, hd.getTienThua());
			stmt.setString(13, hd.getGhiChu());
			n = stmt.executeUpdate();
			if ((new Dao_ChiTietHoaDon()).addChiTietHoaDon(hd)) {
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
	
	public List<HoaDon> readHoaDonFromSQL(){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<HoaDon> listHD = new ArrayList();
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM HoaDon");
			while (rs.next()) {
				NhanVien nv = (new Dao_NhanVien()).findNhanVienByMaNV(rs.getString(3));
				KhachHang kh = (new Dao_KhachHang()).findKhachHangByMaKH(rs.getString(4));
				KhuyenMai km = (new Dao_KhuyenMai()).findKhuyenMaiByID(rs.getString(7));
				HoaDon hd = new HoaDon(rs.getString(1),
						rs.getDate(2).toLocalDate(),
						rs.getFloat(5),
						rs.getString(6),
						rs.getFloat(8),
						rs.getFloat(9),
						rs.getString(10),
						rs.getFloat(11),
						rs.getFloat(12),
						rs.getString(13)
					);
				hd.setNhanVien(nv);
				hd.setKhachHang(kh);
				hd.setKhuyenMai(km);
				List<ChiTietHoaDon> listCTHD = (new Dao_ChiTietHoaDon()).readChiTietHoaDonFromSQLByMaHD(hd.getMaHD());
				hd.setChiTietHoaDon(listCTHD);
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
	
	public HoaDon findHoaDonByMaHD(String maHD) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    HoaDon hd = null;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT * FROM HoaDon WHERE maHD = ?");
	        stmt.setString(1, maHD); // Thiết lập giá trị cho tham số maThuoc

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	        	NhanVien nv = (new Dao_NhanVien()).findNhanVienByMaNV(rs.getString(3));
	        	KhachHang kh = (new Dao_KhachHang()).findKhachHangByMaKH(rs.getString(4));
				KhuyenMai km = (new Dao_KhuyenMai()).findKhuyenMaiByID(rs.getString(7));
	            List<ChiTietHoaDon> listCTHD = (new Dao_ChiTietHoaDon()).readChiTietHoaDonFromSQLByMaHD(maHD);
	        	hd = new HoaDon(rs.getString(1),
	                    rs.getDate(2).toLocalDate(),
	                    rs.getFloat(5),
	                    rs.getString(6),
	                    rs.getFloat(8),
	                    rs.getFloat(9),
						rs.getString(10),
						rs.getFloat(11),
						rs.getFloat(12),
						rs.getString(13)
	            );
	        	hd.setNhanVien(nv);
	        	hd.setKhachHang(kh);
	        	hd.setKhuyenMai(km);
	        	hd.setChiTietHoaDon(listCTHD);
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
	    return hd;
	}
	
	public String autoCreateMaHD() {
		Connection connect = null;
	    PreparedStatement stmt = null;
	    String maHD = null;
	    try {
	        connect = ConnectDB.getConnection();
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery("DECLARE @TodayDate AS DATE = GETDATE(),\r\n"
            		+ "        @Prefix AS VARCHAR(8),\r\n"
            		+ "        @NextNumber AS INT;\r\n"
            		+ "SET @Prefix = 'HD' + \r\n"
            		+ "              FORMAT(@TodayDate, 'dd') + \r\n"
            		+ "              FORMAT(@TodayDate, 'MM') + \r\n"
            		+ "              RIGHT(YEAR(@TodayDate), 2);\r\n"
            		+ "SELECT @NextNumber = ISNULL(MAX(CAST(SUBSTRING(maHD, 9, 4) AS INT)), 0) + 1\r\n"
            		+ "FROM [dbo].[HoaDon]\r\n"
            		+ "WHERE maHD LIKE @Prefix + '%';\r\n"
            		+ "SELECT @Prefix + FORMAT(@NextNumber, '0000');");
            while(rs.next()) {
                maHD = rs.getString(1);
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
        return maHD;
    }
	
	public List<Integer> getListYearOfOrder(){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<Integer> listNam = new ArrayList<>();
	    try {
	        connect = ConnectDB.getConnection();
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery("SELECT DISTINCT YEAR(ngayLap) AS nam\r\n"
            		+ "FROM HoaDon\r\n"
            		+ "ORDER BY nam DESC;");
            while(rs.next()) {
                listNam.add(rs.getInt(1));
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
        return listNam;
	}
	
	public float calTotalCashRevenue(String maNV) {
		Connection connect = null;
	    PreparedStatement stmt = null;
	    float totalCashRevenue = 0;
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
	        stmt = connect.prepareStatement("SELECT SUM(CEILING(thanhTien/1000.0)*1000) AS tongDoanhThuTM\r\n"
	        		+ "FROM HoaDon\r\n"
	        		+ "WHERE maNV = ? \r\n"
	        		+ "    AND phuongThucTT = N'Tiền mặt' \r\n"
	        		+ "    AND loaiHD = N'Bán hàng'\r\n"
	        		+ "    AND CAST(ngayLap AS DATE) = CAST(GETDATE() AS DATE)");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				totalCashRevenue = rs.getFloat(1);
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
		return totalCashRevenue;
	}
	
	public float calTotalCashATM(String maNV) {
		Connection connect = null;
	    PreparedStatement stmt = null;
	    float totalCashATM = 0;
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
	        stmt = connect.prepareStatement("SELECT SUM(CEILING(thanhTien/1000.0)*1000) AS tongDoanhThuATM\r\n"
	        		+ "FROM HoaDon\r\n"
	        		+ "WHERE maNV = ? \r\n"
	        		+ "    AND phuongThucTT = N'ATM' \r\n"
	        		+ "    AND loaiHD = N'Bán hàng'\r\n"
	        		+ "    AND CAST(ngayLap AS DATE) = CAST(GETDATE() AS DATE)");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				totalCashATM = rs.getFloat(1);
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
		return totalCashATM;
	}
	
	public float calTotalMoneySpent(String maNV) {
		Connection connect = null;
	    PreparedStatement stmt = null;
	    float totalMoneySpent = 0;
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
	        stmt = connect.prepareStatement("SELECT ROUND(SUM(tienThua),-3) AS tongTienThua\r\n"
	        		+ "FROM HoaDon\r\n"
	        		+ "WHERE maNV = ? \r\n"
	        		+ "    AND loaiHD = N'Bán hàng'\r\n"
	        		+ "    AND CAST(ngayLap AS DATE) = CAST(GETDATE() AS DATE)\r\n");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				totalMoneySpent = rs.getFloat(1);
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
		return totalMoneySpent;
	}
}
