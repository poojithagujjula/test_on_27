package MovieTicketBooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


public class ShowAvailableMovies {
	//conn
	//display movies
	//display shows
	//select -> statement ->
	public static void displayMovies() throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		if(conn==null) {
			System.out.println("Database Connection Failed");
			return;
	}
	//method 1 ->display movies
	String query = "select * from movies join shows on movies.movie_id = shows.movie_id;";
	//combine with shows, join
	try(Statement smt=conn.createStatement()){
		ResultSet rs = smt.executeQuery(query);
		while(rs.next()) {
			int movieId = rs.getInt("movie_id");
			String title = rs.getString("title");
			String genre = rs.getString("genre");
			int duration = rs.getInt("duration");
			Timestamp showtime = rs.getTimestamp("show_time");
			int available_seats = rs.getInt("available_seats");
			System.out.println("Movie Id :"+movieId );
			System.out.println("Title :" +title); 
			System.out.println("Genre :"+genre);
			System.out.println("Duration :"+duration); 
			System.out.println("Showtime :"+showtime);
			System.out.println("Available :"+available_seats);
			System.out.println("_____________________________");
		}
	}
}
}
	
	
