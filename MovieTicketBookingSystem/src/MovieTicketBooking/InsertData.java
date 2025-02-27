package MovieTicketBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InsertData {
	public static void insertMovies() throws SQLException {
		Connection conn= DatabaseConnection.getConnection();
		if(conn == null) {
			System.out.println("Databse Connection Failed");
			return;
		}
		String insertMovieSQL="Insert into Movies(title,genre,duration) values(?,?,?)";
		try(PreparedStatement psmt=conn.prepareStatement(insertMovieSQL)) {
			//movie1
			psmt.setString(1, "Fighter");
			psmt.setString(2, "Action");
			psmt.setInt(3, 150);
			psmt.executeUpdate();
			//movie2
			psmt.setString(1, "humour");
			psmt.setString(2, "Thriller");
			psmt.setInt(3, 110);
			psmt.executeUpdate();
			//movie3
			psmt.setString(1, "akanda");
			psmt.setString(2, "Action");
			psmt.setInt(3, 150);
			psmt.executeUpdate();
			System.out.println("movies daat inserted successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//insert shows
	public static void insertshows() {
		Connection conn= DatabaseConnection.getConnection();
		if(conn == null) {
			System.out.println("Databse Connection Failed");
			return;
		}
		String insertshowsSQL="insert into shows(movie_id,show_time,available_seats) values(?,?,?)";
		try(PreparedStatement psmt=conn.prepareStatement(insertshowsSQL)){
			//one show
			psmt.setInt(1, 1);
			psmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2025,2,15,18,30))); //15 feb 6:30 pm
			psmt.setInt(3, 100); //15 feb 6:30pm
			psmt.executeUpdate();
			//show2
			psmt.setInt(1, 2);
			psmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2025,2,13, 13,30)));
			psmt.setInt(3, 120);
			psmt.executeUpdate();
			//show3
			psmt.setInt(1, 3);
			psmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2025,2,12, 16,30)));
			psmt.setInt(3, 90);
			psmt.executeUpdate();
			System.out.println("shows data inserted successfully");
			//second show
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
        insertMovies();
        insertshows();
    }
 
}
	
	//PreparedStatement
	//two methods
	//insert movies data
	
/*	public static void insertMovies() throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		if(conn == null) {
			System.out.println("Database Connection Failed.");
			return;
		}
		String insertMovieSQL = "Insert into Movies (title, genre, duration) Values (?,?,?)";
		
		
		try(PreparedStatement psmt = conn.prepareStatement(insertMovieSQL)) {
			

			psmt.setString(1,  "Fighter");
			psmt.setString(2, "Action");
			psmt.setInt(3, 150);
			psmt.executeUpdate();
			
			psmt.setString(1,  "A");
			psmt.setString(2, "Horror");
			psmt.setInt(3, 100);
			psmt.executeUpdate();
			
			psmt.setString(1,  "B");
			psmt.setString(2, "Thriller");
			psmt.setInt(3, 250);
			psmt.executeUpdate();
			
			System.out.println("Movies data inserted successfully.");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//insert shows
	public static void insertShows() {
		Connection conn = DatabaseConnection.getConnection();
		if(conn == null) {
			System.out.println("Database Connection Failed.");
			return;
	}
		String insertShowsSQL = "insert into shows(movie_id, show_time, available_seats) values(?.?.?)";
		
		try(PreparedStatement psmt = conn.prepareStatement(insertShowsSQL)){
			
			//one show
			psmt.setInt(1, 1);
			psmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2025,2,14,18,25)));
			psmt.setInt(3, 100);
			psmt.executeUpdate();
			
			psmt.setInt(1, 2);
			psmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2020,3,15,16,30)));
			psmt.setInt(3, 110);
			psmt.executeUpdate();
			
			psmt.setInt(1, 3);
			psmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2023,4,12,17,40)));
			psmt.setInt(3, 90);
			psmt.executeUpdate();
			
			
			
			System.out.println("Shows data inserted successfully");
				
		}
	catch (SQLException e){
		e.printStackTrace();
		
	}
	
  }			
	public static void main(String[] args) throws SQLException {
		insertMovies();
		insertShows();
	} 
}
*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	