import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.*;

/**
 * This class contains all the information of a show time
 */
public class ShowTime implements Serializable{
    /**
	 * The date and time of the show time
	 */
    private Calendar dateOfShow;	

    /**
	 * The cinema of the show time
	 */
    private Cinema cinema;

    /**
	 * The movie of the show time
	 */
    private Movie movie;

    /**
	 * The booked layout of the show time
	 */
    private ArrayList<ArrayList<Seat>> bookedLayout;

	/**
	 * Creates a {@code ShowTime} object for the given date, time, cinema, seat layout and movie
	 * @param dateOfShow the date and time of the show time
	 * @param cinema the cinema of the show time
	 * @param seatLayout the seat layout of the showtime
	 * @param movie the movie of the showtime
	 */
    public ShowTime(Calendar dateOfShow, Cinema cinema, ArrayList<ArrayList<Seat>> seatLayout, Movie movie) {
        this.dateOfShow = dateOfShow;
        this.cinema = cinema;

        //Create deep copy for the layout
        this.bookedLayout = new ArrayList<ArrayList<Seat>>();
        for (int i=0; i<seatLayout.size(); i++){
            bookedLayout.add(new ArrayList<Seat>());
            for (int j=0; j<seatLayout.get(i).size(); j++){
                bookedLayout.get(i).add(seatLayout.get(i).get(j).copy());
            }
        }
        this.movie = movie;
    }

	/**
	 * This method print the booked layout of the showtime
	 */
    public void checkAvailability(){
        DisplaySeat.displaySeat(bookedLayout);
    }

	/**
	 * This method returns the date and time of the showtime
	 * @return the date and time of the showtime
	 */
    public Calendar getDateOfShow(){
        return this.dateOfShow;
    }

	/**
	 * This method returns the date and time of the showtime as formatted string
	 * @return the formatted string of date and time of the showtime
	 */
    public String getDateOfShowFormat(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String output = formatter.format(dateOfShow.getTime());
        return output;
    }

    /**
	 * This method return the cinema of the showtime
	 * @return the cinema of the showtime
	 */
    public Cinema getCinema() {
        return this.cinema;
    }

	/**
	 * This method return the movie of the showtime
	 * @return the movie of the showtime
	 */
    public Movie getMovie() {
        return this.movie;
    }

	/**
	 * This method return the booked layout of the showtime
	 * @return the booked layout of the showtime
	 */
    public ArrayList<ArrayList<Seat>> getBookedLayout(){
        return this.bookedLayout;
    }

	/**
	 * This method return whether the showtime is booked by at least one user
	 * @return whether the showtime is booked by at least one user
	 */
    public boolean isBookedBySomeone(){
        for (ArrayList<Seat> seatList : bookedLayout){
            for(Seat seat : seatList){
                if(!seat.isEmpty()){
                    return true;                
                }
            }
        }
        return false;
    }
    
	/**
	 * This method prompts user to input date and time and return the according calendar class
	 * @return the calendar class according to the input
	 */
    public static Calendar inputTime() {
        Scanner sc = new Scanner(System.in);
        Calendar aDate = new GregorianCalendar();
        boolean done = false;
        while (!done){        
            try {
                System.out.println("Enter year (e.g. 2010): ");
                int year = IOHandler.nextInt();
                System.out.println("Enter month (e.g. 12 for Dec): ");
                int month = IOHandler.nextInt() - 1; // -1 since months from 0 to 11
                System.out.println("Enter day of month (e.g. 25): ");
                int day = IOHandler.nextInt();
                System.out.println("Enter hour (e.g. 16): ");
                int hour = IOHandler.nextInt();
                System.out.println("Enter minute (e.g. 55): ");
                int minute = IOHandler.nextInt();
                int second = 0;
                aDate = new GregorianCalendar(year, month, day, hour, minute, second);
                aDate.setLenient(false);
                aDate.getTime();
                done = true;
            } catch (Exception e){
                System.out.println("Invalid date, please try again");
            }
        }
        return aDate;
    }
}