package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.BangKetCa;
import entity.ChiTietBangKetCa;
import entity.ChiTietHoaDon;

public class Dao_ChiTietBangKetCa {
	public boolean addChiTietBangKetCa(BangKetCa bangKetCa) {
		Connection connect = null;
	    PreparedStatement stmt = null;
		int n = 0;
		try {
	        connect = ConnectDB.getConnection();
	        List<ChiTietBangKetCa> listCTBKC = bangKetCa.getChiTietBangKetCa();
	        for (ChiTietBangKetCa ctbkc : listCTBKC) {
	        	stmt = connect.prepareStatement("INSERT INTO ChiTietBangKetCa VALUES(?, ?, ?)");
				stmt.setString(1, bangKetCa.getMaCa());
				stmt.setFloat(2, ctbkc.getMenhGia());
				stmt.setInt(3, ctbkc.getSoLuong());
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
	
	public List<ChiTietBangKetCa> readChiTietBangKetCaFromSQLByMaHD(String maCa){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<ChiTietBangKetCa> listCTBKC = new ArrayList();
		try {
	        connect = ConnectDB.getConnection();
			Statement stt = connect.createStatement();
	        stmt = connect.prepareStatement("SELECT * FROM ChiTietBangKetCa WHERE maCa = ?");
			stmt.setString(1, maCa);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietBangKetCa ctbkc = new ChiTietBangKetCa(rs.getFloat(2),
						rs.getInt(3)
						);
				listCTBKC.add(ctbkc);
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
		return listCTBKC;
	}
}
