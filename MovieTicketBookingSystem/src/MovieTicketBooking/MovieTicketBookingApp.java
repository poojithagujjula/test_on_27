package MovieTicketBooking;

import java.sql.SQLException;
import java.util.Scanner;

public class MovieTicketBookingApp{

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		//movie ticket booking app
		Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Movies Running");
        System.out.println("2 - TicketBooking ");
        System.out.println("3 - BookingConfirmation ");
        System.out.println("4 - TicketCancellation ");
        System.out.println("Enter your choice");
 
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
            	ShowAvailableMovies.displayMovies();
                break;
            case 2:
            	TicketBooking.bookTicket();
            	break;
            case 3:
            	BookingConfirmation.bookConfirmation();
            case 4:
            	TicketCancellation.cancelTicket();
            	break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        scanner.close();
      }
}


