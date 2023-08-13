import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class contains all the information of a cinema
 */
public class Cinema implements Serializable{

    /**
	 * The cinema code of the cinema
	 */
    private int cinemaCode;

    /**
	 * The cineplex name that the cinema belongs to
	 */
    private String cineplexName;

    /**
	 * The cinema class of the cinema
	 */
    private CinemaClass cinemaClass;

    /**
	 * The seating layout of the cinema
	 */
    private ArrayList<ArrayList<Seat>> seatLayout;

    /**
	 * The showtimes of the cinema
	 */
    private ArrayList<ShowTime> showTimes;

    /**
	 * The maximum seat per row of the cinema
	 */
    private int maxSeatPerRow ;

    /**
	 * The number of row of the cinema
	 */
    private int numRow = 0;

    /**
	 * The number of couple row of the cinema
	 */
    private int numRowCouple;

    /**
	 * The number of single row of the cinema
	 */
    private int numRowSingle;

	/**
	 * Creates a {@code Cinema} object for the given cinema code, cinema class, number of row, number of couple row, maximum seat per row and the cineplex name that the cinema belongs to
	 * @param cinemaCode the code of the cinema
	 * @param cinemaClass the cinema class of the cinema
	 * @param numRow the number of row of the cinema
	 * @param numRowCouple the number of couple row of the cinema
	 * @param maxSeatPerRow the maximum seat per row of the cinema
	 * @param cineplexName the cineplex name that the cinema belongs to
	 */
    public Cinema(int cinemaCode, CinemaClass cinemaClass, int numRow, int numRowCouple, int maxSeatPerRow, String cineplexName) {
        seatLayout = new ArrayList<ArrayList<Seat>>();
        showTimes = new ArrayList<ShowTime>();
        this.cinemaCode = cinemaCode;
        this.cinemaClass = cinemaClass;
        this.numRow = numRow;
        this.numRowCouple = numRowCouple;
        this.maxSeatPerRow = maxSeatPerRow;
        numRowSingle = numRow-numRowCouple;

        // create arrayList for seatLayout;
        int i,j;
        for(i = 0; i < numRowSingle ; i++) {
            seatLayout.add(new ArrayList<Seat>());
            for(j = 0; j < maxSeatPerRow; j ++) {
                Integer temp = j+1;
                seatLayout.get(i).add(new Seat((char)(i+65) + temp.toString(), Seat.SeatType.NORMAL));
                
            }
        }
        for(i = numRowSingle; i <numRow ; i++) {
            seatLayout.add(new ArrayList<Seat>());
            for(j = 0; j < maxSeatPerRow/2; j ++) {
                Integer temp = j+1;
                seatLayout.get(i).add(new Seat((char)(i+65) + temp.toString(), Seat.SeatType.COUPLE));
                
            }
        }
        this.cineplexName = cineplexName;
    }

	/**
	 * This method returns the cinema code of the cinema
	 * @return the cinema code of the cinema
	 */
    public int getCinemaCode(){
        return cinemaCode;
    }

	/**
	 * This method returns the cineplex name that the cinema belongs to
	 * @return the cineplex name that the cinema belongs to
	 */
    public String getCineplexName(){
        return cineplexName;
    }

	/**
	 * This method returns the cinema class of the cinema
	 * @return the cinema class of the cinema
	 */
    public CinemaClass getCinemaClass(){
        return cinemaClass;
    }

    /**
	 * This method prints the seating layout of the cinema
	 */
    public void printSeatLayout() {
        DisplaySeat.displaySeat(seatLayout);
    }

	/**
	 * This method returns the seating layout of the cinema
	 * @return the seating layout of the cinema
	 */
    public ArrayList<ArrayList<Seat>> getSeatLayout(){
        return seatLayout;
    }

	/**
	 * This method returns the list of showtimes of the cinema
	 * @return the list of showtimes of the cinema
	 */
    public ArrayList<ShowTime> getShowTime(){
        return showTimes;
    }

	/**
	 * This method adds a new showtime to the list of showtimes of the cinema
	 * @param showTime the new showtime to be added
	 */
    public void addShowTime(ShowTime showTime){
        showTimes.add(showTime);
    }

	/**
	 * This method checks whether there is a clash for the new showtime and the existing show times in the cinema
	 * @param newShowTime the new show time
	 * @param currentShowTime the current show time
	 * @return if there's a clash, it will return true, otherwise it returns false
	 */
    public boolean checkClash(ShowTime newShowTime, ShowTime currentShowTime){
        for (ShowTime showTime: showTimes) {
            Calendar startDateTime = newShowTime.getDateOfShow();
            Calendar endDateTime = (Calendar) newShowTime.getDateOfShow().clone();
            int duration = newShowTime.getMovie().getDuration();

            endDateTime.add(Calendar.MINUTE, duration);

			Calendar oldShowTimeStart = showTime.getDateOfShow();
            Calendar oldShowTimeEnd = (Calendar) oldShowTimeStart.clone();
            oldShowTimeEnd.add(Calendar.MINUTE, showTime.getMovie().getDuration());

			boolean isClash = ((oldShowTimeStart.before(endDateTime) && !oldShowTimeStart.equals(endDateTime)) &&
				(startDateTime.before(oldShowTimeEnd) && !startDateTime.equals(oldShowTimeEnd)) &&
				showTime != currentShowTime
			);
			
			if (isClash)
				return true;
		}
		return false;
    }
}