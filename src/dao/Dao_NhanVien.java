package dao;

import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import database.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;
import entity.Thuoc;
import utils.AES;

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
				if (maNV.equals("NV000000")) {
	                continue;
	            }
				String hoTen = rs.getString(2);
				String gioiTinh = rs.getString(3);
				String soDT = rs.getString(4);
				LocalDate ngaySinh = rs.getDate(5).toLocalDate();
				LocalDate ngayVaoLam = rs.getDate(6).toLocalDate();
				String chucVu = rs.getString(7);
				String soCCCD = rs.getString(8);
				String diaChi = rs.getString(9);
				String trangThai = rs.getNString(10);
				String anh = rs.getString(11); 

				NhanVien nhanVien = new NhanVien(maNV,hoTen,gioiTinh,soDT,ngaySinh,ngayVaoLam,chucVu,soCCCD,diaChi,trangThai,anh);
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
	
	public boolean createNhanVienADMIN() {
		NhanVien nvADMIN = new NhanVien("NV000000",
				"ADMIN",
				"",
				"",
				LocalDate.now(),
				LocalDate.now(),
				"ADMIN",
				"",
				"",
				"Làm Việc",
				"images/avatar-default.png");
		if (addNhanVien(nvADMIN)) {
			return true;
		}
		return false;
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
	        stmt.setString(11, nhanVien.getAnh());

	        n = stmt.executeUpdate();
	        
	        // Sau khi thêm nhân viên thành công, tạo tài khoản dựa trên mã nhân viên
	        if (n > 0) {
	            String tenTaiKhoan = nhanVien.getMaNV();
	            String matKhau = "1111"; 
	            try {
					matKhau = (new AES()).encrypt(matKhau, tenTaiKhoan);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            Dao_TaiKhoan daoTaiKhoan = new Dao_TaiKhoan();
	            TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau);
	            daoTaiKhoan.addTaiKhoan(taiKhoan);
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
	public NhanVien findNhanVienByMaNV(String maNhanVien) {
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
	                rs.getString(10),
	                rs.getString(11)
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
	        stmt.setString(10,nhanVien.getAnh());
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
	public String autoCreateMaNhanVien(String loaiNV) {
	    Connection connect = null;
	    PreparedStatement stmt = null;
	    String maNhanVien = null;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("DECLARE @LoaiNV NVARCHAR(50) = ?; " +
	                                        "DECLARE @NamCuoi INT; " +
	                                        "SET @NamCuoi = RIGHT(YEAR(GETDATE()), 2); " +
	                                        "DECLARE @SoTT INT; " +
	                                        "SET @SoTT = ISNULL((SELECT MAX(CAST(SUBSTRING(MaNV, 6, 3) AS INT)) FROM [dbo].[NhanVien] WHERE SUBSTRING(MaNV, 1, 4) = 'NV' + "
	                                        + "CONVERT(NVARCHAR(2), @NamCuoi) AND SUBSTRING(MaNV, 5, 1) = CASE "
	                                        + "WHEN @LoaiNV = N'Quản Lý' THEN '1'"
	                                        + "WHEN @LoaiNV = N'Nhân Viên' Then '2' "
	                                        + "ELSE '0' END), 0) + 1; " +
	                                        "DECLARE @SoTTStr NVARCHAR(3); " +
	                                        "SET @SoTTStr = RIGHT('000' + CONVERT(NVARCHAR(3), @SoTT), 3); " +
	                                        "DECLARE @MaNV NVARCHAR(10); " +
	                                        "SET @MaNV = 'NV' + CONVERT(NVARCHAR(2), @NamCuoi) + CASE "
	                                        + "WHEN @LoaiNV = N'Quản Lý' THEN '1' "
	                                        + "WHEN @LoaiNV = N'Nhân Viên'THEN '2' "
	                                        + "ELSE '0' END + @SoTTStr; " +
	                                        "SELECT @MaNV AS MaNhanVien;");
	        stmt.setString(1, loaiNV);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            maNhanVien = rs.getString("MaNhanVien");
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
	    return maNhanVien;
	}

	
//	public byte[] getAnhByMaNV(String maNV) {
//		Connection connect = null;
//	    PreparedStatement stmt = null;
//	    byte[] anhByte = null;
//	    try {
//	        connect = ConnectDB.getConnection();
//	        stmt = connect.prepareStatement("SELECT anh FROM NhanVien WHERE maNV = ?");
//	        stmt.setString(1, maNV);
//
//	        ResultSet rs = stmt.executeQuery();
//	        if (rs.next()) {
//	        	InputStream anh = rs.getBinaryStream(1); 
//					try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
//					    int nRead;
//					    byte[] data = new byte[16384]; // Adjust buffer size as needed
//					    while ((nRead = anh.read(data, 0, data.length)) != -1) {
//					        buffer.write(data, 0, nRead);
//					    }
//					    buffer.flush();
//					    anhByte = buffer.toByteArray(); // Chuyển dữ liệu từ ByteArrayOutputStream thành mảng byte
//					} catch (IOException e) {
//					    e.printStackTrace();
//					}
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	        try {
//	            if (stmt != null) stmt.close();
//	            if (connect != null) {
//	                ConnectDB.close(connect);
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    return anhByte;
//	}
}
