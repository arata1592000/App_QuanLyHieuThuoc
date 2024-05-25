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
				if (rs.getString(11) != null) {
					thuoc.setKhuyenMai((new Dao_KhuyenMai()).findKhuyenMaiByID(rs.getString(11)));	
				}
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
	            if (rs.getString(11) != null) {
					thuoc.setKhuyenMai((new Dao_KhuyenMai()).findKhuyenMaiByID(rs.getString(11)));	
				}
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
	
	public List<Thuoc> listThuocContainTenThuocOrMaThuoc(String text){
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<Thuoc> listThuoc = new ArrayList<Thuoc>();
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT * FROM Thuoc WHERE tenThuoc LIKE CONCAT('%', ? , '%') OR maThuoc LIKE CONCAT('%', ?, '%')");
	        stmt.setString(1, text);
	        stmt.setString(2, text);

	        ResultSet rs = stmt.executeQuery();
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
	    return listThuoc;
	}
	public List<Thuoc> listThuocNoDiscount()
	{
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<Thuoc> listThuoc = new ArrayList<Thuoc>();
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("select * from Thuoc where maKMSP IS NULL AND ngayHetHan > GETDATE()");
	        //stmt.setString(1, maThuoc); // Thiết lập giá trị cho tham số maThuoc

	        ResultSet rs = stmt.executeQuery();
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
	    return listThuoc;
		
	}
	public List<Thuoc> listThuocDiscountByID(String maKM)
	{
		Connection connect = null;
	    PreparedStatement stmt = null;
	    List<Thuoc> listThuoc = new ArrayList<Thuoc>();
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("select * from Thuoc where maKMSP = ?");
	        stmt.setString(1, maKM); // Thiết lập giá trị cho tham số maThuoc

	        ResultSet rs = stmt.executeQuery();
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
	            thuoc.setKhuyenMai((new Dao_KhuyenMai()).findKhuyenMaiByID(rs.getString(11)));;

	            listThuoc.add(thuoc);
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
	    return listThuoc;
	}
	
	public boolean setCountByMaThuoc(List<Thuoc> listThuoc, List<Integer> listSoLuong) {
		Connection connect = null;
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	    	for (int i = 0 ; i < listThuoc.size(); i++) {
	    		connect = ConnectDB.getConnection();
		        stmt = connect.prepareStatement("UPDATE Thuoc SET soLuong = soLuong - ? WHERE maThuoc=?");
		        stmt.setInt(1, listSoLuong.get(i)); // Thiết lập giá trị cho tham số maThuoc
		        stmt.setString(2, listThuoc.get(i).getMaThuoc());
		        
		        n = stmt.executeUpdate();
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
	    return n>0;
	}
	
	public boolean checkCountByMaThuoc(String maThuoc, int soLuong) {
        Connection connect = null;
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT CASE \r\n"
	        		+ "        WHEN ? > soLuong THEN 0\r\n"
	        		+ "        ELSE 1 \r\n"
	        		+ "      END AS isGreater \r\n"
	        		+ "FROM Thuoc \r\n"
	        		+ "WHERE maThuoc = ?");
	        stmt.setInt(1, soLuong); // Thiết lập giá trị cho tham số maThuoc
	        stmt.setString(2, maThuoc);
	        
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next() && (rs.getInt(1) == 1)) {
	        	n = 1;
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
	    return n>0;
	}
	
	public Thuoc getThuocByTenSoLuongDonViTinh(String tenThuoc, int soLuong, String donViTinh) {
		Connection connect = null;
	    PreparedStatement stmt = null;
	    Thuoc thuoc = null;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("SELECT *\r\n"
	        		+ "FROM Thuoc\r\n"
	        		+ "WHERE tenThuoc = ?\r\n"
	        		+ "AND donViTinh = ? \r\n"
	        		+ "AND soLuong >= ?\r\n"
	        		+ "AND GETDATE() < ngayHetHan");
	        stmt.setString(1, tenThuoc); // Thiết lập giá trị cho tham số maThuoc
	        stmt.setString(2, donViTinh);
	        stmt.setInt(3, soLuong);
	        
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next()) {
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
	
	public boolean checkAndUpdateDiscount() {
		Connection connect = null;
	    PreparedStatement stmt = null;
	    int n = -1;
	    try {
	        connect = ConnectDB.getConnection();
	        stmt = connect.prepareStatement("UPDATE Thuoc \r\n"
	        		+ "SET maKMSP = NULL \r\n"
	        		+ "FROM Thuoc \r\n"
	        		+ "LEFT JOIN KhuyenMai ON Thuoc.maKMSP = KhuyenMai.maKM \r\n"
	        		+ "WHERE KhuyenMai.ngayKetThuc < GETDATE();");
	       
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
	    return n>-1;
	}
	public List<Thuoc> getThuocByNgayNhapVe(LocalDate tuNgay, LocalDate denNgay) {
        List<Thuoc> listThuoc = new ArrayList<>();
        String query = "SELECT * FROM Thuoc WHERE ngayNhapVe BETWEEN ? AND ?";
        try (Connection connect = ConnectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(query)) {
            ps.setDate(1, java.sql.Date.valueOf(tuNgay));
            ps.setDate(2, java.sql.Date.valueOf(denNgay));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Thuoc thuoc = new Thuoc(
                        rs.getString("maThuoc"),
                        rs.getString("tenThuoc"),
                        rs.getDate("ngayNhapVe").toLocalDate(),
                        rs.getDate("ngaySanXuat").toLocalDate(),
                        rs.getDate("ngayHetHan").toLocalDate(),
                        rs.getString("noiSanXuat"),
                        rs.getFloat("gia"),
                        rs.getString("donViTinh"),
                        rs.getString("thanhPhan"),
                        rs.getInt("soLuong")
                    );
                    listThuoc.add(thuoc);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listThuoc;
    }
}
