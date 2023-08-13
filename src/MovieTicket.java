import java.io.Serializable;

/**
 * This class contains all the information of a ticket
 */
public class MovieTicket implements Serializable{	

    /**
	 * The price of the ticket
	 */
    private double price;
    
    /**
	 * The show time of the booked movie
	 */
    private ShowTime showTime;

    /**
	 * The seat of the ticket
	 */
    private Seat seat;
    
    /**
	 * The age group of the ticket
	 */
    private AgeGroup ageGroup;

	/**
	 * Creates a {@code MovieTicket} object of seat booked
	 * @param price price of the ticket
     * @param showTime show time of the movie
     * @param seat seat of the ticket
	 * @param ageGroup age group of the ticket
	 */
    public MovieTicket(double price, ShowTime showTime, Seat seat, AgeGroup ageGroup){
        this.price = price;
        this.showTime = showTime;
        this.seat = seat;
        this.ageGroup = ageGroup;
    }

	/**
	 * This method returns the price of the ticket
	 * @return the price of the ticket
	 */
    public double getPrice(){
        return price;
    }

	/**
	 * This method returns the show time of the booked movie
	 * @return the show time of the booked movie
	 */
    public ShowTime getShowTime(){
        return showTime;
    }

	/**
	 * This method returns the seat of the ticket
	 * @return the seat of the ticket
	 */
    public Seat getSeat(){
        return seat;
    }

	/**
	 * This method returns the age group of the ticket
	 * @return the age group of the ticket
	 */
    public AgeGroup getAgeGroup(){
        return ageGroup;
    }

	/**
	 * Print the detail of the ticket
	 */
    public void printTicket(){
        System.out.println("============================");
        System.out.println("Movie: " + showTime.getMovie().getMovieName());
        System.out.println("Cinema: " + showTime.getCinema().getCinemaCode());
        System.out.println("Date and Time: " + showTime.getDateOfShowFormat());
        System.out.println("Seat: " + seat.getSeatID());
        System.out.printf("Price: %.2f\n", price);
        System.out.println("Age Group: " + ageGroup);
        System.out.println("============================");
    }

    /**
	 * Print the simple info of the ticket
	 */
    public void printTicketSimple(){
        System.out.print("Ticket for Seat: " + seat.getSeatID());
        System.out.print(" - Age Group: " + ageGroup);
        System.out.printf(" - Price: %.2f\n", price);
        //System.out.println();
    }
}
