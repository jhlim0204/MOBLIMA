import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class contains all the information of a movie goer
 */
public class MovieGoer implements Serializable{	
    /**
	 * The name of the movie goer
	 */
    private String name;

    /**
	 * The mobile number of the movie goer
	 */
    private String mobileNumber;

    /**
	 * The email address of the movie goer
	 */
    private String email;

    /**
	 * The booking history of the movie goer
	 */
    private ArrayList<BookingHistory> bookingHistory;

	/**
     * Creates a {@code MovieGoer} object with the given name, mobile number and email address
     * @param name the name of the movie goer
     * @param mobileNumber the mobile number of the movie goer
     * @param email the email address of the movie goer
     */
    public MovieGoer(String name, String mobileNumber , String email)
    {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.bookingHistory = new ArrayList<BookingHistory>();
    }

	/**
     * This method returns the name of the movie goer
     * @return the name of the movie goer
     */
    public String getName(){
        return name;
    }

	/**
     * This method returns the mobile number of the movie goer
     * @return the mobile number of the movie goer
     */
    public String getMobileNumber(){
        return mobileNumber;
    }

	/**
     * This method returns the email address of the movie goer.
     * @return the email address of the movie goer
     */
    public String getEmail(){
        return email;
    }

	/**
     * This method adds new booking to the booking history of the movie goer.
	 * @param bookingHist the booking history to be added
     */
    public void addBookingHistory(BookingHistory bookingHist){
        bookingHistory.add(bookingHist);
    }

	/**
     * This method prompts the movie goer to select the booking and print its detail accordingly.
     */
    public void viewBookingDetail(){
        if (bookingHistory.size() == 0){
            System.out.println("Your booking history is empty");
        } else {
            viewBooking();
            int choice;
            System.out.println("Please select the booking that you want to view by the number");
            choice = IOHandler.nextInt();
            //check index
            if (1<=choice && choice<=bookingHistory.size()+1){
                bookingHistory.get(choice-1).viewTicket();
            } else {
                System.out.println("Invalid input");
            }
        }
    }

	/**
     * This method prints booking history of the movie goer.
     */
    public void viewBooking(){
        int count = 1;
        for (BookingHistory booking : bookingHistory){
            System.out.println(count);
            booking.viewBooking();
            count++;
        }
    }

}