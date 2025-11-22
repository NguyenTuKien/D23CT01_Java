package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/QLSV1";
    private static final String USER = "root";
    private static final String PASSWORD = "03012005";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection c = getConnection();
        if (c != null)
            System.out.println("✅ Kết nối thành công!");
        else
            System.out.println("❌ Kết nối thất bại!");
    }
}
