package MovieTicketBooking;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.Date;
import java.util.Scanner;

public class Test {

	public static void Testing1() {
	    //public static void main(String[] args) {
	        Connection conn = null;
	        CallableStatement cstmt = null;
	        try {
	            conn = DatabaseConnection.getConnection();
	            if (conn == null) {
	                System.out.println("Database Connection Failed");
	                return;
	            }
	            cstmt = conn.prepareCall("{CALL Test2(?, ?,?)}");
	            System.out.print("Enter your movie Id: ");
	            Scanner scanner = new Scanner(System.in);
	            int movieId = scanner.nextInt();
	            cstmt.setInt(1, movieId);
	            cstmt.registerOutParameter(2, Types.VARCHAR);
	            cstmt.registerOutParameter(3, Types.VARCHAR);
	            //cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
	            cstmt.execute();
	            //csmt
	            String title = cstmt.getString(2);
	            String genre = cstmt.getString(3);
	            //int duration = cstmt.getInt(4);
	            System.out.println("Movie Title: " + title);
	            System.out.println("Movie Genre: " + genre);
	            //System.out.println("Movie Duration: " + duration);
	        } 
	        catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        finally {
	            try {
	                if (cstmt != null) 
	                	cstmt.close();
	                if (conn != null) 
	                	conn.close();
	            } 
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		public static void Testing2() {
			Connection conn = null;
	        CallableStatement cstmt = null;
	        ResultSet rs = null;
	        try {
	            conn = DatabaseConnection.getConnection();
	            if (conn == null) {
	                System.out.println("Database Connection Failed");
	                return;
	            }
	            cstmt = conn.prepareCall("{CALL GetMovieDetailsByUsername1(?)}");
	            System.out.print("Enter your username: ");
	            Scanner scanner = new Scanner(System.in);
	            String username = scanner.nextLine();
	            cstmt.setString(1, username);
	            rs = cstmt.executeQuery();
	 
	            while (rs.next()) {
	                String name = rs.getString("username");
	                String title = rs.getString("title");
	                String genre = rs.getString("genre");
	                int seatsBooked = rs.getInt("seats_booked");
	                Time showTime = rs.getTime("show_time");
	                Date bookingDate = rs.getDate("booking_date");
	                // Print the columns
	                System.out.println("=============================================================================================");
	                System.out.println("Username: " + name);
	                System.out.println("Movie Title: " + title);
	                System.out.println("Movie Genre: " + genre);
	                System.out.println("Seats Booked: " + seatsBooked);
	                System.out.println("Show Time: " + showTime);
	                System.out.println("Booking Date: " + bookingDate);
	                System.out.println("=============================================================================================");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (cstmt != null) cstmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		}
	    public static void main(String[] args) {
	        Testing1();
	        //Testing2();
	    }
	}
	 
	//out -> 1,2
	//fetch multiple rows->returns resultset
	//->based on usernmae ->fetch all bookings->IN username
	//xyz->atleast 2 bookings
	//create procedure->no out variable
	//test the procedure
	//csmt->one placeholder->/
	//setString(1,username)
	//resultSet rs=csmt.executeQuery()
	//while(rs.next())->display all booking details