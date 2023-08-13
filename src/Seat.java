import java.io.Serializable;
/**
 * This class contains all the information of a seat
 */
public class Seat implements Serializable{

    /**
	 * The seat ID
	 */
    private String seatID;

    /**
	 * The availability of the seat
	 */
    private boolean empty;

    /**
	 * The enumeration of the seat type
	 */
    public enum SeatType {	
    	/**
    	 * Couple
    	 */
    	COUPLE, 
    	/**
    	 * Normal
    	 */
    	NORMAL};

    /**
	 * The type of the seat
	 */
    private SeatType seatType;

    /**
	 * Creates a {@code Seat} object
	 * @param seatID ID of the seat
     * @param seatType type of the seat
	 */
    public Seat(String seatID, SeatType seatType){
        this.seatID = seatID;
        this.empty = true;
        this.seatType = seatType;
    }

    /**
	 * This method returns the alphabet part of the ID of the seat
	 * @return the the alphabet part of the ID of the seat
	 */
    public String getSeatIDAlphabet(){
        return Character.toString(seatID.charAt(0));
    }

    /**
	 * This method returns the number part of the ID of the seat
	 * @return the the number part of the ID of the seat
	 */
    public int getSeatIDNum(){
        return Integer.parseInt(this.seatID.substring(1));
    }

    /**
	 * This method returns the ID of the seat
	 * @return the ID of the seat
	 */
    public String getSeatID(){
        return seatID;
    }
    
	/**
	 * This method returns the type of the seat
	 * @return the type of the seat
	 */
    public SeatType getSeatType(){
        return seatType;
    }

	/**
	 * This method returns the availability of the seat
	 * @return the availability of the seat
	 */
    public boolean isEmpty(){
        return empty;
    }

	/**
	 * This method set the seat to be booked
	 */
    public void assign(){
        this.empty = false;
    }

	/**
	 * This method set the seat to be not booked
	 */
    public void unassign(){
        this.empty = true;
    }

	/**
	 * This method do a deep copy and return the copied seat
	 * @return the copied seat
	 */
    public Seat copy(){
        return new Seat(seatID, seatType);
    }
}