import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains all the information of a movie ticket calculator
 */
public class MovieTicCal implements Serializable{

	/**
	 * The holiday dates
	 */
    private ArrayList<Calendar> holidayDates = new ArrayList<Calendar>();

	/**
	 * The base price of the ticket
	 */
    private double basePrice;

	/**
	 * The cinema class multipliers
	 */
    private HashMap<CinemaClass, Double> cinemaMultipliers = new HashMap<CinemaClass, Double>();    

    /**
	 * The age group multipliers
	 */
    private HashMap<AgeGroup, Double> ageMultipliers = new HashMap<AgeGroup, Double>();

    /**
	 * The movie type multipliers
	 */
    private HashMap<MovieType, Double> movieMultipliers = new HashMap<MovieType, Double>();

    /**
	 * The date type multipliers
	 */
    private HashMap<DateType, Double> dateMultipliers = new HashMap<DateType, Double>();
    /**
 	 * The current display top listing status
 	 */
     private int topListingStatus;
    /**
	 * Creates a {@code MovieTicCal} object
	 */
    public MovieTicCal(){
        basePrice = 8.50;
        cinemaMultipliers.put(CinemaClass.NORMAL, 1.0);
        cinemaMultipliers.put(CinemaClass.PLATINUM_MOVIE_SUITE, 1.2);

        ageMultipliers.put(AgeGroup.ADULT, 1.0);
        ageMultipliers.put(AgeGroup.CHILDREN, 0.8);
        ageMultipliers.put(AgeGroup.SENIOR_CITIZEN, 0.5);

        movieMultipliers.put(MovieType.REGULAR, 1.0);
        movieMultipliers.put(MovieType._3D, 1.1);
        movieMultipliers.put(MovieType.BLOCKBUSTER, 1.1);

        dateMultipliers.put(DateType.WEEKDAY, 1.0);
        dateMultipliers.put(DateType.WEEKEND, 1.1);
        dateMultipliers.put(DateType.HOLIDAY, 1.1);
        this.topListingStatus = 1;

    }

	/**
     * This method returns the price of a ticket with the given show time and age group
     * @param showTime the show time of the ticket
     * @param ageGroup the age group of the ticket
     * @return the price of the ticket
     */
    public double calculateTicketPrice(ShowTime showTime, AgeGroup ageGroup) {
        double price = this.basePrice;

        CinemaClass cinemaClass = showTime.getCinema().getCinemaClass();
        MovieType movieType = showTime.getMovie().getMovieType();
        Calendar date = showTime.getDateOfShow();
        DateType dateType = getDateType(date);

        price *= cinemaMultipliers.get(cinemaClass);
        if (dateType != DateType.HOLIDAY){
            //If public holiday then different age group have the same price
            price *= ageMultipliers.get(ageGroup);
        }
        price *= movieMultipliers.get(movieType);
        price *= dateMultipliers.get(dateType);

        return price;
    }

	/**
     * This method returns true if the given date is a holiday
     * @param date the date to check whether it's a holiday
     * @return true if the given date is a holiday
     */
	private boolean isHoliday(Calendar date) {
		for (Calendar holidayDate: holidayDates) {
			if (holidayDate.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR) && holidayDate.get(Calendar.YEAR) == date.get(Calendar.YEAR)){
				        return true;
                  }
		}
		return false;
	}

	/**
     * This method returns the date type of the given date
     * @param date the date to check
     * @return the date type of the given date
     */
    private DateType getDateType(Calendar date) {
		if (this.isHoliday(date))
			return DateType.HOLIDAY;
		else if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return DateType.WEEKEND;
		else
			return DateType.WEEKDAY;
	}

	/**
     * This method returns the list of holiday date
     * @return the list of holiday date
     */
	public ArrayList<Calendar> getHolidayDates() {
		return this.holidayDates;
	}

	/**
     * This method add the date to the holiday dates
     * @param date the holiday date to be added
     */
    public void addHoliday(Calendar date){
        holidayDates.add(date);
    }

	/**
     * This method remove the date from the holiday dates
     * @param date the holiday date to be removed
     */
    public void removeHoliday(Calendar date){
        int index = -1;
        int count = 0;

        while (index == -1){
            count = 0;
            for (Calendar holidayDate : holidayDates) {
                if (holidayDate.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR) && holidayDate.get(Calendar.YEAR) == date.get(Calendar.YEAR)){
                            index = count;
                            break;
                    }
                count++;
		    }
            if (index != -1){
                holidayDates.remove(index);
                index = -1;
            } else {
                System.out.println("Date removal succesful");
                return;
            }
        }
    }

	/**
     * This method returns the base price of the movie ticket calculator
     * @return the base price of the movie ticket calculator
     */
	public double getBasePrice() {
		return basePrice;
	}
	/**
     * This method sets the base price of the movie ticket calculator
     * @param basePrice the new base price of the movie ticket calculator
     */
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	/**
     * This method returns the the multiplier corresponding to a given cinema class.
     * @param cinemaClass the cinema class to get the multiplier of
     * @return the multiplier corresponding to the cinema class
     */
    public Double getCinemaMultiplier(CinemaClass cinemaClass) {
		return this.cinemaMultipliers.get(cinemaClass);
	}

	/**
     * This method sets the the multiplier corresponding to a given cinema class.
     * @param cinemaClass the cinema class to set the multiplier of
     * @param cinemaMultiplier the new multiplier for the cinema class
     */
    public void setCinemaMultiplier(CinemaClass cinemaClass, Double cinemaMultiplier) {
		this.cinemaMultipliers.put(cinemaClass, cinemaMultiplier);
	}

    /**
     * This method prints the multiplier corresponding to its given cinema type.
     */    
    public void printCinemaMultiplier() {
    	for(CinemaClass name: cinemaMultipliers.keySet()) {
    		String key = name.toString();
    		String value = cinemaMultipliers.get(name).toString();
    		System.out.println(key + " " + value);
    	}
    }

	/**
     * This method returns the the multiplier corresponding to a given age group.
     * @param ageGroup the age group to get the multiplier of
     * @return the multiplier corresponding to the age group
     */
	public Double getAgeMultiplier(AgeGroup ageGroup) {
		return this.ageMultipliers.get(ageGroup);
	}

    /**
     * This method sets the the multiplier corresponding to a given age group.
     * @param ageGroup the age group to set the multiplier of
     * @param ageMultiplier the new multiplier for the age group
     */
    public void setAgeMultiplier(AgeGroup ageGroup, Double ageMultiplier) {
		this.ageMultipliers.put(ageGroup, ageMultiplier);
	}

    /**
     * This method prints the multiplier corresponding to its given age type.
     */
    public void printAgeMultiplier() {
    	for(AgeGroup name: ageMultipliers.keySet()) {
    		String key = name.toString();
    		String value = ageMultipliers.get(name).toString();
    		System.out.println(key + " " + value);
    	}
    }

	/**
     * This method returns the the multiplier corresponding to a given movie type.
     * @param movieType the movie type to get the multiplier of
     * @return the multiplier corresponding to the movie type
     */
	public Double getMovieMultiplier(MovieType movieType) {
		return this.movieMultipliers.get(movieType);
	}

	/**
     * This method sets the the multiplier corresponding to a given movie type.
     * @param movieType the movie type to set the multiplier of
     * @param movieMultiplier the new multiplier for the movie type
     */
    public void setMovieMultiplier(MovieType movieType, Double movieMultiplier) {
		this.movieMultipliers.put(movieType, movieMultiplier);
	}

    /**
     * This method prints the multiplier corresponding to its given movie type.
     */
    public void printMovieMultiplier() {
    	for(MovieType name: movieMultipliers.keySet()) {
    		String key = name.toString();
    		String value = movieMultipliers.get(name).toString();
    		System.out.println(key + " " + value);
    	}
    }

	/**
     * This method returns the the multiplier corresponding to a given date type.
     * @param dateType the date type to get the multiplier of
     * @return the multiplier corresponding to the date type
     */
	public Double getDateMultiplier(DateType dateType) {
		return this.dateMultipliers.get(dateType);
	}

	/**
     * This method sets the multiplier corresponding to a given date type.
     * @param dateType the date type to set the multiplier of
     * @param dateMultiplier the new multiplier for the date type
     */
    public void setDateMultiplier(DateType dateType, Double dateMultiplier) {
		this.dateMultipliers.put(dateType, dateMultiplier);
	}

    /**
     * This method prints the multiplier corresponding to its given date type.
     */
    public void printDateMultiplier() {
    	for(DateType name: dateMultipliers.keySet()) {
    		String key = name.toString();
    		String value = dateMultipliers.get(name).toString();
    		System.out.println(key + " " + value);
    	}
    }
    
	/**
	 * This method returns the display top listing status
	 * @return the top listing status
	 */
    public int getTopListingStatus(){
        return topListingStatus;
    }

	/**
	 * This method set the display top listing status
	 * @param topListingStatus the status to be set
	 */
    public void setTopListingStatus(int topListingStatus){
        this.topListingStatus = topListingStatus;
    }

}