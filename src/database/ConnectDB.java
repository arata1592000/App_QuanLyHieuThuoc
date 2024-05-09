package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    // Các thông tin kết nối đến cơ sở dữ liệu
    private static final String URL = "jdbc:sqlserver://localhost:1433;databasename=QLBT";
    private static final String USERNAME = "hao123";
    private static final String PASSWORD = "hao123";

    // Phương thức để mở kết nối đến cơ sở dữ liệu
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Tạo kết nối đến cơ sở dữ liệu
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Kết nối đến cơ sở dữ liệu thành công.");
        } catch (SQLException e) {
            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
            e.printStackTrace();
        }
        return connection;
    }

    // Phương thức để đóng kết nối đến cơ sở dữ liệu
    public static void close(Connection connection) {
        if (connection != null) {
            try {                
            	System.out.println("Đã đóng kết nối đến cơ sở dữ liệu.");
                connection.close();
            } catch (SQLException e) {
                System.out.println("Không thể đóng kết nối đến cơ sở dữ liệu.");
                e.printStackTrace();
            }
        }
    }

}
