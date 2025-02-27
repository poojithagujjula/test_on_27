package MovieTicketBooking;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StroredProcCallable {
	
	//create procedure
	//create  -> procedure name(in, out)
	//begin
	//logical
	//end;
	
	//Statement smt = 
	//creating -> input username -> how many seats are booked
	public static void storedProcedure() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        Scanner scanner = new Scanner(System.in);
 
        // Taking input from user
   /*    System.out.print("Enter your movie Id: ");
        int movieId = scanner.nextInt(); */
        System.out.print("Enter your movie Id: ");
        int movieId = scanner.nextInt();
	
     /*   String procedure = "CREATE PROCEDURE Test2(IN movieId INT, OUT movietitle VARCHAR(255)) "
                + "BEGIN "
                + "SELECT title INTO movietitle FROM movies "
                + "WHERE movie_id = movieId; "
                + "END;";
                */
        String procedure = "CREATE PROCEDURE Test3(IN movieId INT, OUT movietitle VARCHAR(255),OUT movieGenre VARCHAR(255)) "
                + "BEGIN "
                + "SELECT genre INTO movieGenre FROM movies "
                + "WHERE movie_id = movieId; "
                + "END;";
        
	Statement smt = conn.createStatement();
	
	  smt.execute(procedure);
	System.out.println("Stored procedure created successfully.");
	
	}
	public static void ViewUserBookings() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
 
        String procedure = "CREATE PROCEDURE GetMovieDetailsByUsername(IN username VARCHAR(255))"
                         + "BEGIN "
                         + "select * from movies join shows on movies.movie_id = shows.movie_id join bookings on bookings.show_id = shows.show_id where user_name = username;"
                         + "END;";
        Statement stmt = conn.createStatement();
        // Execute the stored procedure creation
        stmt.execute(procedure);
        System.out.println("View Bookings procedure created successfully.");
    }
	public static void main(String[] args) throws SQLException {
		storedProcedure();
		ViewUserBookings();
	}
	
}

























