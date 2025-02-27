package MovieTicketBooking;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class WebRowSetApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = DatabaseConnection.getConnection();
		try
		{
			WebRowSet wrs = RowSetProvider.newFactory().createWebRowSet();
			wrs.setCommand("select * from movies");
			wrs.execute(conn);
			
			//close the conn
			conn.close();
			System.out.println("Connection Closed.");
			
			//Save data xml
			FileWriter writer= new FileWriter("movies.xml");
			wrs.writeXml(writer);
			writer.close();
			
			//Read data from xml
			WebRowSet new_wrs = RowSetProvider.newFactory().createWebRowSet();
			FileReader reader = new FileReader("movies.xml");
			new_wrs.readXml(reader);
			reader.close();
			
			while(new_wrs.next()) {
				System.out.println("ID: "+new_wrs.getInt("movie_id")+"Title :"+new_wrs.getString("title"));
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}














