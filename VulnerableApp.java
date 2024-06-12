import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VulnerableApp {
    public static void main(String[] args) {
        String user = args[0]; // User input should be sanitized
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=root");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + user + "'"); // Vulnerable to SQL Injection

            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }
}
