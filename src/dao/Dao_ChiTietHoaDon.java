package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;

public class Dao_ChiTietHoaDon {
	public Dao_ChiTietHoaDon() {
		
	}
	
	public List<Object[]> statisticalThuocByThang(int thang, int nam) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    List<Object[]> listObj = new ArrayList<>();
	    try {
	        connect = ConnectDB.getConnection();
	        String query = "SELECT " +
	                "    MONTH(hd.ngayLap) AS Thang, " +
	                "    ct.maThuoc, " +
	                "    ct.tenThuoc, " +
	                "    ct.donViTinh, " +
	                "    ct.gia, " +
	                "    SUM(ct.soLuong) AS TongSoLuong, " +
	                "    SUM(ct.tongTien) AS TongTien " +
	                "FROM " +
	                "    HoaDon hd " +
	                "JOIN " +
	                "    ChiTietHoaDon ct ON hd.maHD = ct.maHD " +
	                "WHERE " +
	                "    YEAR(hd.ngayLap) = ? AND " +
	                "    MONTH(hd.ngayLap) = ? " +
	                "GROUP BY " +
	                "    MONTH(hd.ngayLap), " +
	                "    ct.maThuoc, " +
	                "    ct.donViTinh, " +
	                "    ct.gia, " +
	                "    ct.tenThuoc;";
	        stmt = connect.prepareStatement(query);
	        stmt.setInt(1, nam); 
	        stmt.setInt(2, thang); 
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String maThuoc = rs.getString("maThuoc");
	            String tenThuoc = rs.getString("tenThuoc");
	            String donViTinh = rs.getString("donViTinh");
	            double gia = rs.getDouble("gia");
	            int tongSoLuong = rs.getInt("TongSoLuong");
	            double tongTien = rs.getDouble("TongTien");

	            Object[] row = {maThuoc, tenThuoc, donViTinh, gia, tongSoLuong, tongTien};

	            listObj.add(row);
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
	    return listObj;
	}
	
	public List<Object[]> statisticalThuocByQuy(int quy, int nam) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    List<Object[]> listObj = new ArrayList<>();
	    try {
	        connect = ConnectDB.getConnection();
	        String query = "SELECT\r\n"
	                + "    CASE\r\n"
	                + "        WHEN MONTH(hd.ngayLap) BETWEEN 1 AND 3 THEN 1\r\n"
	                + "        WHEN MONTH(hd.ngayLap) BETWEEN 4 AND 6 THEN 2\r\n"
	                + "        WHEN MONTH(hd.ngayLap) BETWEEN 7 AND 9 THEN 3\r\n"
	                + "        ELSE 4\r\n"
	                + "    END AS Quy,\r\n"
	                + "    ct.maThuoc,\r\n"
	                + "    ct.tenThuoc,\r\n"
	                + "    ct.donViTinh,\r\n"
	                + "    ct.gia,\r\n"
	                + "    SUM(ct.soLuong) AS TongSoLuong,\r\n"
	                + "    SUM(ct.tongTien) AS TongTien\r\n"
	                + "FROM\r\n"
	                + "    HoaDon hd\r\n"
	                + "JOIN\r\n"
	                + "    ChiTietHoaDon ct ON hd.maHD = ct.maHD\r\n"
	                + "WHERE\r\n"
	                + "    YEAR(hd.ngayLap) = ? AND\r\n"
	                + "    MONTH(hd.ngayLap) BETWEEN ? AND ? -- Giá trị năm và quý được truyền vào\r\n"
	                + "GROUP BY\r\n"
	                + "    CASE\r\n"
	                + "        WHEN MONTH(hd.ngayLap) BETWEEN 1 AND 3 THEN 1\r\n"
	                + "        WHEN MONTH(hd.ngayLap) BETWEEN 4 AND 6 THEN 2\r\n"
	                + "        WHEN MONTH(hd.ngayLap) BETWEEN 7 AND 9 THEN 3\r\n"
	                + "        ELSE 4\r\n"
	                + "    END,\r\n"
	                + "    ct.maThuoc,\r\n"
	                + "    ct.tenThuoc,\r\n"
	                + "    ct.donViTinh,\r\n"
	                + "    ct.gia;";
	        stmt = connect.prepareStatement(query);
	        stmt.setInt(1, nam);
	        // Tính toán tháng bắt đầu và kết thúc của quý
	        int thangBatDau = (quy - 1) * 3 + 1;
	        int thangKetThuc = quy * 3;
	        stmt.setInt(2, thangBatDau); // Tháng bắt đầu của quý
	        stmt.setInt(3, thangKetThuc); // Tháng kết thúc của quý
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String maThuoc = rs.getString("maThuoc");
	            String tenThuoc = rs.getString("tenThuoc");
	            String donViTinh = rs.getString("donViTinh");
	            double gia = rs.getDouble("gia");
	            int tongSoLuong = rs.getInt("TongSoLuong");
	            double tongTien = rs.getDouble("TongTien");

	            Object[] row = {maThuoc, tenThuoc, donViTinh, gia, tongSoLuong, tongTien};

	            listObj.add(row);
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
	    return listObj;
	}


}
