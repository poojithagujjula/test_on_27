package MovieTicketBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserLogin {
	public static boolean loginUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return false;
        }
        String query = "SELECT admin FROM users WHERE username = ? AND pwd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                boolean isAdmin = rs.getBoolean("admin");
                System.out.println("Login successful!");
                return isAdmin;
            } else {
                System.out.println("Invalid username or password!");
                return false;
            }
        }
    }

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		loginUser();

	}

}
