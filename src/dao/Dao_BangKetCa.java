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
import entity.BangKetCa;
import entity.ChiTietBangKetCa;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;


public class Dao_BangKetCa {
	public String autoCreateMaBangKetCa() {
		Connection connect = null;
	    PreparedStatement stmt = null;
	    String maCa = null;
	    try {
	        connect = ConnectDB.getConnection();
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery("DECLARE @TodayDate AS DATE = GETDATE(),\r\n"
            		+ "            		        @Prefix AS VARCHAR(8),\r\n"
            		+ "            		        @NextNumber AS INT;\r\n"
            		+ "            		SET @Prefix = 'KC' +\r\n"
            		+ "            		              FORMAT(@TodayDate, 'dd') +\r\n"
            		+ "            		              FORMAT(@TodayDate, 'MM') +\r\n"
            		+ "            		              RIGHT(YEAR(@TodayDate), 2);\r\n"
            		+ "            		SELECT @NextNumber = ISNULL(MAX(CAST(SUBSTRING(maCa, 9, 2) AS INT)), 0) + 1\r\n"
            		+ "            		FROM [dbo].[BangKetCa]\r\n"
            		+ "            		WHERE maCa LIKE @Prefix + '%';\r\n"
            		+ "            		SELECT @Prefix + FORMAT(@NextNumber, '00');");
            while(rs.next()) {
                maCa = rs.getString(1);
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
        return maCa;
    }
	
	public boolean addBangKetCa(BangKetCa bangKetCa) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
			
	        connect = ConnectDB.getConnection();
			stmt = connect.prepareStatement("INSERT INTO BangKetCa VALUES(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, bangKetCa.getMaCa());
			stmt.setString(2, bangKetCa.getNhanVien().getMaNV());
			stmt.setTimestamp(3, Timestamp.valueOf(bangKetCa.getNgayLap().atStartOfDay()));
			stmt.setFloat(4, bangKetCa.getTienCoTrongCa());
			stmt.setFloat(5, bangKetCa.getTienMatThuTrongCa());
			stmt.setFloat(6, bangKetCa.getTienATMThuTrongCa());
			stmt.setFloat(7, bangKetCa.getTienLayRa());
			n = stmt.executeUpdate();
			if ((new Dao_ChiTietBangKetCa()).addChiTietBangKetCa(bangKetCa)) {
				return n>0;
			}else {
				removeBangKetCa(bangKetCa.getMaCa());
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

	public boolean removeBangKetCa(String maCa) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
			connect = ConnectDB.getConnection();
			stmt = connect.prepareStatement("DELETE FROM BangKetCa WHERE maCa = ?");
			stmt.setString(1, maCa);
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
	
	public List<BangKetCa> readBangKetCaFromSQL(){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<BangKetCa> listBKC = new ArrayList();
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM BangKetCa");
			while (rs.next()) {
				NhanVien nv = (new Dao_NhanVien()).findNhanVienByMaNV(rs.getString(2));
				BangKetCa bkc = new BangKetCa(rs.getString(1),
						nv,
						rs.getDate(3).toLocalDate(),
						rs.getFloat(4),
						rs.getFloat(5),
						rs.getFloat(6),
						rs.getFloat(7)
					);
				List<ChiTietBangKetCa> listCTBKC = (new Dao_ChiTietBangKetCa()).readChiTietBangKetCaFromSQLByMaHD(bkc.getMaCa());
				bkc.setChiTietBangKetCa(listCTBKC);
				listBKC.add(bkc);
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
		return listBKC;
	}

	public BangKetCa findBangKetCaByMaCa(String maCa) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    BangKetCa bkc = null;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT * FROM BangKetCa WHERE maCa = ?");
	        stmt.setString(1, maCa); // Thiết lập giá trị cho tham số maThuoc

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	        	NhanVien nv = (new Dao_NhanVien()).findNhanVienByMaNV(rs.getString(2));
				bkc = new BangKetCa(rs.getString(1),
						nv,
						rs.getDate(3).toLocalDate(),
						rs.getFloat(4),
						rs.getFloat(5),
						rs.getFloat(6),
						rs.getFloat(7)
					);
				List<ChiTietBangKetCa> listCTBKC = (new Dao_ChiTietBangKetCa()).readChiTietBangKetCaFromSQLByMaHD(bkc.getMaCa());
				bkc.setChiTietBangKetCa(listCTBKC);
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
	    return bkc;
	}
}
