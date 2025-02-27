package MovieTicketBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TicketBooking {
	
	public static void bookTicket() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
 
        ShowAvailableMovies.displayMovies();
        System.out.print("Enter the show_id you want to book: ");
        int showId = scanner.nextInt();
 
        String checkSeatsQuery = "SELECT available_seats FROM shows WHERE show_id = ?;";
        int availableSeats = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(checkSeatsQuery)) {
            pstmt.setInt(1, showId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                availableSeats = rs.getInt("available_seats");
            } else {
                System.out.println("Show not found.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
 
        System.out.print("Enter the number of seats you want to book: ");
        int numberOfSeats = scanner.nextInt();
 
        if (availableSeats >= numberOfSeats) {
            String bookTicketQuery = "INSERT INTO booking (username, show_id, seats_booked) VALUES (?, ?, ?);";
            try (PreparedStatement pstmt = conn.prepareStatement(bookTicketQuery)) {
                pstmt.setString(1, username);
                pstmt.setInt(2, showId);
                pstmt.setInt(3, numberOfSeats);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
 
            String updateSeatsQuery = "UPDATE shows SET available_seats = available_seats - ? WHERE show_id = ?;";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSeatsQuery)) {
                pstmt.setInt(1, numberOfSeats);
                pstmt.setInt(2, showId);
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Ticket booked successfully!");
                } else {
                    System.out.println("Failed to update available seats.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not enough seats available.");
        }
    }
}	


