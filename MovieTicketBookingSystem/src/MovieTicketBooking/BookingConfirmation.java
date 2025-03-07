package MovieTicketBooking;

import java.sql.*;
import java.util.Scanner;


public class BookingConfirmation {
	// ask username - preparedStatement
	// relating all three tables
	// Booking > USername, Booking Id
	// Movies -> Movie Name
	// Shows -> SHowtime, Seats booked ->
public static void bookConfirmation() throws SQLException {
	Connection conn = DatabaseConnection.getConnection();
	if(conn==null) {
		System.out.println("Database Connection Failed");
		return;
    }
	Scanner scanner = new Scanner(System.in);
    // Step 1: Take user input
    System.out.print("Enter your username: ");
    String username = scanner.nextLine();
    String fetchQuery = 
            "SELECT b.username, b.booking_id, m.title, s.show_time, b.seats_booked " +
            "FROM booking b " +
            "JOIN shows s ON b.show_id = s.show_id " +
            "JOIN movies m ON s.movie_id = m.movie_id " +
            "WHERE b.username = ?";
    try ( PreparedStatement pstmt = conn.prepareStatement(fetchQuery)) {

           pstmt.setString(1, username);
           ResultSet rs = pstmt.executeQuery();

           if (rs.next()) {
               int bookingId = rs.getInt("booking_id");
               String movieName = rs.getString("title");
               String showtime = rs.getString("show_time");
               int seatsBooked = rs.getInt("seats_booked");
               System.out.println("Booking Details :");
               System.out.println("Username: " + username);
                System.out.println("Booking ID: " + bookingId);
                System.out.println("Movie Name: " + movieName);
                System.out.println("Show Time: " + showtime);
                System.out.println("Seats Booked: " + seatsBooked);
            }
           else {
                System.out.println("Booking details not found.");
            }
       } 
    catch (SQLException e) {
           e.printStackTrace();
       }
}

}
	
	//ask username - preparedStatement
	
	//relating all three tables
	
	//Booking > Username, Booking Id
	//Movies ->Movie Name
	//Shows -> Showtime, seats booked ->

/*	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Ask for username
	        System.out.print("Enter your username: ");
	        String username = scanner.nextLine();

	        // Fetch booking confirmation details
	        fetchBookingDetails(username);
	    }

	    private static void fetchBookingDetails(String username) {
	        String query = "SELECT booking.username, booking.bookingid, movies.title AS moviename, shows.showtime, booking.seats " +
	                       "FROM bookings booking " +
	                       "JOIN shows s ON booking.showid = shows.showid " +
	                       "JOIN movies m ON shows.movieid = movies.movieid " +
	                       "WHERE booking.username = ?";

	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "password");
	             PreparedStatement pstmt = conn.prepareStatement(query)) {

	            pstmt.setString(1, username);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                System.out.println("Username: " + rs.getString("username"));
	                System.out.println("Booking ID: " + rs.getInt("bookingid"));
	                System.out.println("Movie Name: " + rs.getString("moviename"));
	                System.out.println("Showtime: " + rs.getTimestamp("showtime"));
	                System.out.println("Seats Booked: " + rs.getInt("seats"));
	                System.out.println("-----------------------------");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	*/

