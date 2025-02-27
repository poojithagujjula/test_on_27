package MovieTicketBooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class DatabaseMetaDataApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Connection conn = DatabaseConnection.getConnection()){
			DatabaseMetaData dbMd= (DatabaseMetaData) conn.getMetaData();
			//fetch db info
			System.out.println("Database Product name :"+dbMd.getDatabaseProductName());
			System.out.println("Database Product version :"+dbMd.getDatabaseProductVersion());
			System.out.println("JDBC Driver name :"+dbMd.getDriverName());
			System.out.println("Jdbc Driver version :"+dbMd.getDriverVersion());
			System.out.println("Database urtl :"+dbMd.getURL());
			System.out.println("Database Username :"+dbMd.getUserName());
			//retrieve tables in db
			ResultSet rs=dbMd.getTables("movie_booking", null, "%", new String[] {"TABLE"});
			while(rs.next()) {
				System.out.println((rs.getString("TABLE_NAME")));
			}
			ResultSet rs2=dbMd.getColumns(null,null,"shows","%");
			while(rs2.next()) {
				System.out.println((rs2.getString("COLUMN_NAME")));
				System.out.println((rs2.getString("TYPE_NAME")));
				//primary key->check for shows tables
				//support transaction
				//get the metadata
				System.out.println(dbMd.supportsTransactions());
			}
			ResultSet rs3 = dbMd.getPrimaryKeys(null, null, "Shows");
            while (rs3.next()) {
                System.out.println("Primary Key: " + rs3.getString("COLUMN_NAME"));
            }

	    }
	    catch(SQLException e) {
		   e.printStackTrace();
	    }	
	}


}
