import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class contains all the information of the booking history
 */
public class BookingHistory implements Serializable{  

    /**
	 * The transaction ID of the booking
	 */
    private String transactionID;

    /**
	 * The show time of the booking
	 */
    private ShowTime showTime;

    /**
	 * The total price of the booking
	 */
    private double totalPrice;

    /**
	 * The tickets of the booking
	 */
    private ArrayList<MovieTicket> tickets;

	/**
	 * Creates a {@code BookingHistory} object with the given transaction ID, showtime, total price and tickets
	 * @param transactionID the transaction ID of the booking
	 * @param showTime the show time of the booking
	 * @param totalPrice the total price of the booking
	 * @param tickets the tickets of the booking
	 */
    public BookingHistory(String transactionID, ShowTime showTime, double totalPrice, ArrayList<MovieTicket> tickets){
        this.transactionID = transactionID;
        this.showTime = showTime;
        this.totalPrice = totalPrice;
        this.tickets = tickets;
    }

	/**
	 * This method returns the transaction ID of the booking
	 * @return the transaction ID of the booking
	 */
    public String getTransactionID(){
        return transactionID;
    }

	/**
	 * This method returns the showtime of the booking
	 * @return the showtime of the booking
	 */
    public ShowTime getShowTime(){
        return showTime;
    }

	/**
	 * This method returns the total price of the booking
	 * @return the total price of the booking
	 */
    public double getTotalPrice(){
        return totalPrice;
    }

	/**
	 * This method returns the tickets of the booking
	 * @return the tickets of the booking
	 */
    public ArrayList<MovieTicket> getTickets(){
        return tickets;
    }

	/**
	 * This method prints the booking detail
	 */
    public void viewBooking() {
        System.out.println("===============================");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Movie: " + showTime.getMovie().getMovieName());
        System.out.println("Number of tickets: " + tickets.size());
        System.out.printf("Total Price: %.2f\n", totalPrice*1.07);
        System.out.println("===============================");
    }

	/**
	 * This method prints the tickets of the booking
	 */
    public void viewTicket() {
        for (MovieTicket i : tickets){
            i.printTicket(); 
        }
    }

}
