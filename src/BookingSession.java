import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class handles the control flow of movie booking
 */

 public class BookingSession {    
     /**
	 * The selected seat in the booking session
	 */
    private ArrayList<Seat> selectedSeat;

    /**
	 * The showtime of the booking session
	 */
    private ShowTime showTime;

    /**
	 * The movie goer of the booking session
	 */
    private MovieGoer movieGoer;

    /**
	 * The price scheme of the booking session
	 */
    private MovieTicCal movieTicCal;

	/**
	 * Creates a {@code BookingSession} object with the given showtime, movie goer and movie ticket calculator
	 * @param showTime the showtime of the booking session
	 * @param movieGoer the movie goer of the booking session
	 * @param movieTicCal the movie ticket calculator of the booking session
	 */
    public BookingSession(ShowTime showTime, MovieGoer movieGoer, MovieTicCal movieTicCal) { // Constructor
        this.selectedSeat = new ArrayList<Seat>();
        this.showTime = showTime;
        this.movieGoer = movieGoer;
        this.movieTicCal = movieTicCal;
    }

	/**
	 * This method generates and returns the transaction ID of this booking session
	 * @return the transaction ID of this booking session
	 */
    public String generateTID() {
        String tid;
        int cinema = showTime.getCinema().getCinemaCode();
        String time;
        Calendar cal = Calendar.getInstance();
	    // get Date from calendar
        // extract individual fields from calendar
        String year = String.format("%04d",cal.get(Calendar.YEAR));
        String month = String.format("%02d",cal.get(Calendar.MONTH) + 1);
        String day = String.format("%02d",cal.get(Calendar.DAY_OF_MONTH));
        String hour = String.format("%02d",cal.get(Calendar.HOUR_OF_DAY));
        String minute = String.format("%02d",cal.get(Calendar.MINUTE));
        time = year+month+day+hour+minute;  
        tid = cinema+time;

        return tid;
    }

	/**
	 * This method is the first method that is called when booking session has started
	 */
    public void start(){
        int choice = 0;
        do {
        System.out.println("Please select an option");
        System.out.println("1) Select seat");
        System.out.println("2) Unselect seat");
        System.out.println("3) Make payment");
        System.out.println("4) Return");
        choice = IOHandler.nextInt();
        switch (choice){
            case(1): selectSeat(); break;
            case(2): unselectSeat(); break;
            case(3): makePayment(); choice = 4; break;
            default: choice = 4; break;
        }
        } while (choice != 4);
    }

	/**
	 * This method prints the currently selected seat
	 */
    public void showSelectedSeat(){
        System.out.print("Currently selected seat: ");
        for (Seat seat : selectedSeat){
            System.out.print(seat.getSeatID()+" ");
        }
        System.out.println();
    }

	/**
	 * This method prompts the user to select the seat
	 */
    public void selectSeat() {
        showTime.checkAvailability();
        showSelectedSeat();
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the seat you want to select: ");
        String choice = sc.nextLine();

        //Check if selected
        for (int i=0; i<selectedSeat.size(); i++){
            if (selectedSeat.get(i).getSeatID().equals(choice)){
                System.out.println("You have selected this seat");
                return;
              }
        }

        ArrayList<ArrayList<Seat>> bookedLayout = showTime.getBookedLayout();
        Seat pendingSeat = null;

        //Simple loop function to find matching ID
        for (int i=0; i<bookedLayout.size(); i++){
            for (int j=0; j<bookedLayout.get(i).size(); j++){
                if (bookedLayout.get(i).get(j).getSeatID().equals(choice)){
                    pendingSeat = bookedLayout.get(i).get(j);
                }
            }
        }

        //Check if booked or invalid input
        if (pendingSeat == null || !pendingSeat.isEmpty()){
            System.out.println("This seat is not available for booking");
            return;
        }

        //Constraint: Please note that system will not allow you to leave a single unoccupied seat between selected seats.
        for (int i=0; i<selectedSeat.size(); i++){
            if (selectedSeat.get(i).getSeatIDAlphabet().equals(pendingSeat.getSeatIDAlphabet())){
                if (selectedSeat.get(i).getSeatIDNum() == pendingSeat.getSeatIDNum() + 2 || selectedSeat.get(i).getSeatIDNum() == pendingSeat.getSeatIDNum() - 2){
                    Integer IDNumToCheck = (selectedSeat.get(i).getSeatIDNum() + pendingSeat.getSeatIDNum())/2;
                    String IDtoCheck = pendingSeat.getSeatIDAlphabet() + IDNumToCheck.toString();

                    boolean selectedOrBooked = false;

                    for (int j=0; j<selectedSeat.size();j++){
                        if (selectedSeat.get(j).getSeatID().equals(IDtoCheck)){
                        	selectedOrBooked = true;
                        }
                    }

                    if (!selectedOrBooked){
                        for (int k=0; k<bookedLayout.size(); k++){
                            for (int j=0; j<bookedLayout.get(k).size(); j++){
                                if (bookedLayout.get(k).get(j).getSeatID().equals(IDtoCheck) && !bookedLayout.get(k).get(j).isEmpty()){
                                	selectedOrBooked = true;
                                }
                            }
                        }
                    }

                    if (!selectedOrBooked){
                        System.out.println("System will not allow you to leave a single unoccupied seat between selected seats");
                        return;
                    }

                }
            }
        }

        selectedSeat.add(pendingSeat);
        System.out.println("Seat selected succesfully");
    }

	/**
	 * This method prompts the user to unselect the seat
	 */
    public void unselectSeat() {
        showSelectedSeat();
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the seat you want to unselect: ");
        String choice = sc.nextLine();

        int index = -1;

        for (int i=0; i<selectedSeat.size(); i++){
            if (selectedSeat.get(i).getSeatID().equals(choice)){
                index = i; break;  
              }
        }

        if (index == -1){
            System.out.println("Seat ID not found!");
        } else {
            selectedSeat.remove(index);
            System.out.println("Seat unselected!");
        }
    }

    /**
	 * This method prompts the user to make payment
	 */
    public void makePayment() {
    	if (selectedSeat.size() == 0) {
    		System.out.println("You have not selected any seat");
    		return;
    	}
    	
        ArrayList<MovieTicket> tickets = new ArrayList<MovieTicket>();

        // For loop to generate tickets based on selectedSeat
        int choice = -1;

        double totalPrice = 0;
        for (Seat seat : selectedSeat){
            //Prompt the users to input ageGroup, if != P THEN EXCLUDE CHILDREN
            System.out.println("Select the age group for seat " + seat.getSeatID());
            AgeGroup ageGroup = AgeGroup.ADULT;
            if (showTime.getMovie().suitableForChildren()){
                System.out.println("1. Adult");
                System.out.println("2. Senior Citizen");
                System.out.println("3. Children");
                choice = IOHandler.nextInt();
                switch (choice){
                case(2): ageGroup = AgeGroup.SENIOR_CITIZEN; break;
                case(3): ageGroup = AgeGroup.CHILDREN; break;
                default: break;
                }
            } else {
                System.out.println("1. Adult");
                System.out.println("2. Senior Citizen");
                choice = IOHandler.nextInt();
                switch (choice){
                case(2): ageGroup = AgeGroup.SENIOR_CITIZEN; break;
                default: break;
                }
            }

            double price = movieTicCal.calculateTicketPrice(showTime, ageGroup);
            if (seat.getSeatType() == Seat.SeatType.COUPLE){
                price *= 2;
            }
            totalPrice += price;
            seat.assign();
            tickets.add(new MovieTicket(price, showTime, seat, ageGroup));
        }
        double finalPrice = totalPrice * 1.07;
        
        for (MovieTicket ticket : tickets){
            ticket.printTicketSimple();
        }

        System.out.println();
        System.out.printf("Base price: %.2f\n", totalPrice);
        System.out.printf("GST: %.2f\n", finalPrice - totalPrice);
        System.out.printf("Total price: %.2f\n", finalPrice);
        System.out.println("Payment made successfully.");

        //Update ticket sales
        showTime.getMovie().addTicketSales(totalPrice);
        movieGoer.addBookingHistory(new BookingHistory(this.generateTID(), this.showTime, totalPrice, tickets));
    }
 }