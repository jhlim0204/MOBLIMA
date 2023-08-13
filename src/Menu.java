import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.io.*;

/**
 * This class contains all the information of the main menu
 */
public class Menu {

    /**
	 * The list of the cineplexes.
	 */
    protected ArrayList<Cineplex> cineplexList;
    
    /**
	 * The list of the cinemas.
	 */
    protected ArrayList<Cinema> cinemaList;
    
    /**
	 * The list of movies.
	 */
    protected MovieListing movieList; //Class, not array list
    
    /**
	 * The movie ticket price calculator.
	 */
    protected MovieTicCal movieTicCal;
    
    /**
	 * The cinema staff user.
	 */
    protected CinemaStaff cinemaStaff;
    
    /**
	 * The list of movie goer accounts in the system.
	 */
    protected ArrayList<MovieGoer> movieGoerList;
    
    /**
	 * The current movie goer trying to sign into the system.
	 */
    protected MovieGoer currentMovieGoer;

    /**
	 * The scanner used to get user input.
	 */
    protected Scanner sc = new Scanner(System.in);

     /**
	 * Creates a {@code Movie} object with the given name, synopsis, director, casts, show times, movie type, release rating, showing status and duration.
	 * @param dataStore is the datastore of this application
	 */
    public Menu (DataStore dataStore){
        this.cineplexList = dataStore.getCineplexList();
        this.movieList = new MovieListing(dataStore.getMovieList());
        this.movieTicCal = dataStore.getMovieTicCal();
        this.cinemaStaff = dataStore.getCinemaStaff();
        this.movieGoerList = dataStore.getMovieGoerList();
        this.currentMovieGoer = null;
    }

    /**
	 * This method starts the menu for the user.
	 */
    public void start(){
        int choice;
        do{
            System.out.println("Please select a portal");
            System.out.println("1) Admin");
            System.out.println("2) User");
            System.out.println("3) Exit");
            choice = IOHandler.nextInt();
            boolean isAdmin = false;
            if (choice == 1){
                if (!AdminLoginModule()){
                    continue;
                } 
                else {
                    isAdmin = true;
                }
            } 
            else if (choice == 2) {
                this.currentMovieGoer = UserLoginModule();
            }
            else if (choice == 3) break;
            else if (choice > 3 || choice == 0 ) {
                System.out.println("Invalid integer! Please enter number 1-3! ");
                continue;
            }
            
            if (isAdmin){
                //displayAdminMenu
                AdminMenu();
            } 
            else {
                UserMenu();
            }
     } while(choice != 3 );

}
    /**
	 * This method logs the user into the system.
	 * @return returns the logged in user account.
	 */
    public MovieGoer UserLoginModule(){

    	String email ,name ,mobileNumber ;

        while(true)
        {
            System.out.println("Please enter your email address: ");
             email = sc.nextLine();
            for (MovieGoer movieGoer : movieGoerList){
                if (movieGoer.getEmail().equals(email)){
                    System.out.println("Logged in successfully");
                    return movieGoer;
                }   
            }
            if (!(email.contains("@") && email.contains(".com")))
            {
                System.out.println("Enter a valid email address format e.g. abc@123.com");
                continue;
            }
            else break;
        }
        
        while (true) {
            System.out.println("Please enter your name: ");
             name = sc.nextLine();

            if (name.matches(".*\\d.*")) {
                System.out.println("Enter a valid name without integers e.g John Tan");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Please enter your mobile number: ");
             mobileNumber = sc.nextLine();

            if (!(mobileNumber.matches("[0-9]+")) || mobileNumber.length() != 8) {
                System.out.println("Enter a valid 8 digit phone number without characters, e.g 12345678");
                continue;
            }
            break;
        }

        MovieGoer newUser = new MovieGoer(name, mobileNumber, email);
        movieGoerList.add(newUser);
        System.out.println("Account created successfully");
        
        return newUser;
    }
    /**
	 * This method logs the admin/cinema staff into the system.
	 * @return returns the boolean value of whether login is successful.
	 */
    public boolean AdminLoginModule(){
        System.out.println("Please enter your username: ");
        String user = sc.nextLine();
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();

        if (cinemaStaff.login(user, password)){
            System.out.println("Logged in successfully");
            return true;
        } else {
            System.out.println("Log in failed");
            return false;
        }
    }

    /**
	 * This method shows the user menu to the logged in user.
	 */
    public void UserMenu(){
            int choice;
            do{
                System.out.println("Welcome " + currentMovieGoer.getName() + "!");
                System.out.println("Reminder: You must use the Exit option in the menu to get all changes saved.");
                System.out.println("______________________________________________________");
                System.out.println("Please select an option");
                System.out.println("1) List Movie");
                System.out.println("2) Search Movie");
                System.out.println("3) View Movie Details");
                System.out.println("4) Book Movie");
                System.out.println("5) View Booking Details");
                System.out.println("6) Give Review");
                System.out.println("7) Exit");

                choice = IOHandler.nextInt();
                switch (choice){
                    case(1): movieListMenu(); break;
                    case(2): MovieSearchMenu(); break;
                    case(3): MovieDetailFromListMenu(); break;
                    case(4): BookMovieMenu(); break;
                    case(5): BookingDetailMenu(); break;
                    case(6): GiveReviewMenu(); break;
                    default: choice = 7; break;
                }
            } while (choice != 7);
    }

    /**
	 * This method shows the admin menu to the logged in user.
	 */
    public void AdminMenu(){
        System.out.println("Welcome " + cinemaStaff.getName() + "!");
            System.out.println("Reminder: You must use the Exit option in the menu to get all changes saved.");
        System.out.println("______________________________________________________");

            int choice;
            do{
                System.out.println("Please select an option");
                System.out.println("1) Create Movie Listing");
                System.out.println("2) Update Movie Listing");
                System.out.println("3) Remove Movie Listing");
                System.out.println("4) Create Movie ShowTime");
                System.out.println("5) Update Movie ShowTime");
                System.out.println("6) Remove Movie ShowTime");
                System.out.println("7) Change Ticket Price");
                System.out.println("8) Set Holiday");
                System.out.println("9) Set top movie listing");
                System.out.println("10) Update movie end of show date");
                System.out.println("11) Exit");

                choice = IOHandler.nextInt();
                switch (choice){
                    case(1): createMovieListingMenu(); break;
                    case(2): updateMovieListingMenu(movieList); break;
                    case(3): removeMovieListingMenu(); break;
                    case(4): createMovieShowTimeMenu(); break;
                    case(5): updateMovieShowTimeMenu(); break;
                    case(6): removeMovieShowTimeMenu(); break;
                    case(7): changeTicketPrice(); break;
                    case(8): setHolidayMenu();break;
                    case(9): setTopMovieListMenu();break;
                    case(10): updateMovieEndOfShowMenu();break;

                    default: choice = 11; break;
                }
            } while (choice != 11);
    }

    /**
	 * This method calls the createmovielisting method from cinemastaff class
	 */
    public void createMovieListingMenu() {
    	movieList.addMovie(cinemaStaff.createMovieListing());
        System.out.println("Movie has been successfully added.");
    }

    /**
	 * This method updates the current movielist
     * @param movieList pass in the movielist to be edited
	 */
    public void updateMovieListingMenu(MovieListing movieList) {
    	int choice;
    	
    	while (true) {
    		System.out.println("Admin: You are updating Movie Listing. Please select an option: ");
	        System.out.println("1) Update Movie Details");
	        System.out.println("2) Update Movie Type");
	        System.out.println("3) Update Movie Showing Status");
	        System.out.println("4) Update Movie Rating");
	        
	        choice = IOHandler.nextInt();
	        if (choice < 1 || choice > 4) {
	        	System.out.println("Please select a valid option (1-4)");
	        	continue;
	        }
	        else break;
    	}
	        
        int movieIndex = 0;
        
        Movie movieToUpdate = null;
        
        if (choice >= 1 && choice <= 4) {
	        while (true) {
		        System.out.println("Please select a movie to update:");
		        movieList.printMovieList();
		        movieIndex = IOHandler.nextInt();
		        if (movieIndex>=1 && movieIndex<=movieList.getMovieList().size()) break;
		        else {
		        	System.out.println("Please select a valid movie (1-" + movieList.getMovieList().size() + ')');
		        	continue;
		        }
	        }
	        movieToUpdate = movieList.getMovieList().get(movieIndex-1);
	        System.out.println("Updating Movie: " + movieToUpdate.getMovieName());
        }
           
        switch (choice){
            case(1): 
	            String synopsis = MovieInputHandler.inputSynopsis();
            	String director = MovieInputHandler.inputDirector();
    			int castCount = MovieInputHandler.inputCastCount();
	            
            	cinemaStaff.updateMovieDetails(movieToUpdate, synopsis, director, castCount); 
            	System.out.println(movieToUpdate.getMovieName() + " has been successfully updated.");
            	break;
            	
            case(2): 
			    MovieType movieType = MovieInputHandler.selectType();
			    
				cinemaStaff.updateMovieType(movieToUpdate, movieType); 
				System.out.println("Successfully updated movie type for - " + movieToUpdate.getMovieName());
				break;
				
            case(3): 
	            ShowingStatus showingStatus = MovieInputHandler.selectStatus();
	            
            	cinemaStaff.updateMovieShowingStatus(movieToUpdate, showingStatus); 
            	System.out.println("Successfully updated showing status for - " + movieToUpdate.getMovieName());
				break;
            
            case(4): 
	            MovieRating movieRating = MovieInputHandler.selectMovieRating();
	            
            	cinemaStaff.updateMovieRating(movieToUpdate, movieRating); 
            	System.out.println("Successfully updated movie rating for - " + movieToUpdate.getMovieName());
            	break;
            
            default: 
            	choice = 5; 
            	break;
        }
    } 
    
    /**
	 * This method prompt cinemaStaff to update the endOfShow; 
	 */
    public void updateMovieEndOfShowMenu(){
    	int index;
    	while (true) {
	        System.out.println("Please select a movie to update:");
	        movieList.printMovieList();
	        index = IOHandler.nextInt();
	        if (index>=1 && index<=movieList.getMovieList().size()) break;
	        else {
	        	System.out.println("Please select a valid movie (1-" + movieList.getMovieList().size() + ')');
	        	continue;
	        }
        }
    	
        Movie movie = movieList.getMovieList().get(index - 1);

        System.out.println("Enter new end of show date for movie: " + movie.getMovieName());
        Calendar endOfShowDate;
        endOfShowDate = ShowTime.inputTime();

        movie.setEndOfShow(endOfShowDate);
    }
    
    /**
	 * This method calls the cinemastaff method of removiemovielisting
	 */
    public void removeMovieListingMenu() {
    	cinemaStaff.removeMovieListing(movieList);
        System.out.println("Movie has been successfully removed.");
    }
    
    /**
	 * This method in charges of creating movie show time
	 */
   public void createMovieShowTimeMenu(){
  	  	Cineplex cineplex = MovieInputHandler.selectCineplex(cineplexList);
        Cinema cinema = MovieInputHandler.selectCinema(cineplex);
        
        int index;
        Movie movie;
        
        while (true) {
	        System.out.println("Please select a movie to create showtime for:");
	        movieList.printMovieList();
	        index = IOHandler.nextInt();
	        if (index>=1 && index<=movieList.getMovieList().size()) {
	        	movie = movieList.getMovieList().get(index - 1);
	        	cinemaStaff.createMovieShowTime(cinema, movie);
	        	break;
	        }
	        else {
	        	System.out.println("Please select a valid movie (1-" + movieList.getMovieList().size() + ')');
	        	continue;
	        }
        }
    }

    /**
	 * This method call two methods removeMovieShowTimeMenu() and createMovieShowTimeMenu() to update the movieshowtime
	 */
    public void updateMovieShowTimeMenu(){

    	int index;
        Movie movieToUpdate;
        
        while (true) {
	        System.out.println("Please select a movie to update showtime for:");
	        movieList.printMovieList();
	        index = IOHandler.nextInt();
	        if (index>=1 && index<=movieList.getMovieList().size()) {
	        	movieToUpdate = movieList.getMovieList().get(index - 1);
	        	break;
	        }
	        else {
	        	System.out.println("Please select a valid movie (1-" + movieList.getMovieList().size() + ')');
	        	continue;
	        }
        }
        
  	  	Cineplex cineplex = MovieInputHandler.selectCineplex(cineplexList);
        Cinema cinema = MovieInputHandler.selectCinema(cineplex);
    	// if removable, add new showtime in specified movie, cinema and cineplex
        if (cinemaStaff.removeMovieShowTime(cinema, movieToUpdate)) {
	    	System.out.println("Movie Showtime is successfully removed.");
	        System.out.println("Enter new movie showtime: ");
	        Calendar dateOfShow;
	        dateOfShow = ShowTime.inputTime();
	        ShowTime newShowTime = new ShowTime(dateOfShow, cinema, cinema.getSeatLayout(), movieToUpdate);
	
	        boolean clash = cinema.checkClash(newShowTime, null);
	
	        if(clash){
	            System.out.println("The entered showtime CLASHES!! Please add another showtime.");
	        }
	        else{
	            cinema.getShowTime().add(newShowTime);
	            movieToUpdate.getShowTime().add(newShowTime);
	            System.out.println("Successfully updated movie showtime for - " + movieToUpdate.getMovieName());
	        }
        }
        
        // not removable
        else {
        	System.out.println("Movie showtime cannot be updated.");
        	return;
        }
    }

    /**
    * This method remove the showtime for the specific movie
    * @return whether the showtime is successfully removed
    */ 
  public boolean removeMovieShowTimeMenu(){
         // list out the list of movie
	    int index;
	    Movie movie;
	  
	    while (true) {
	        System.out.println("Please select a movie to update showtime for:");
	        movieList.printMovieList();
	        index = IOHandler.nextInt();
	        if (index>=1 && index<=movieList.getMovieList().size()) {
	        	movie = movieList.getMovieList().get(index - 1);
	        	break;
	        }
	        else {
	        	System.out.println("Please select a valid movie (1-" + movieList.getMovieList().size() + ')');
	        	continue;
	        }
		}
	  
	    Cineplex cineplex = MovieInputHandler.selectCineplex(cineplexList);
	    Cinema cinema = MovieInputHandler.selectCinema(cineplex);
        
	    if (cinemaStaff.removeMovieShowTime(cinema, movie)) {
	    	System.out.println("Movie Showtime is successfully removed.");
	    	return true;
	    }
	    return false;
    }

 /**
    * This method show a movielist menu that allow user to choose to show top 5 movie
    * based on ticket sales or reviewers' ratings
    */ 
    public void movieListMenu(){
        int choice;
        if (movieTicCal.getTopListingStatus() == 1){        
            do {
            System.out.println("Please select an option");
            System.out.println("1) Default listing");
            System.out.println("2) List the Top 5 Movies by Ticket Sales");
            System.out.println("3) List the Top 5 Movies by Reviewers' Ratings");
            System.out.println("4) Return");
            choice = IOHandler.nextInt();
            switch (choice){
                case(1): movieList.printMovieList(); EnterToContinue(); break;
                case(2): movieList.printTopMoviesSales(); EnterToContinue(); break;
                case(3): movieList.printTopMovieReview(); EnterToContinue(); break;
                default: choice = 4; break;
            }
            } while (choice != 4);
        } else if (movieTicCal.getTopListingStatus() == 2){            
            do {
            System.out.println("Please select an option");
            System.out.println("1) Default listing");
            System.out.println("2) List the Top 5 Movies by Ticket Sales");
            System.out.println("3) Return");
            choice = IOHandler.nextInt();
            switch (choice){
                case(1): movieList.printMovieList(); EnterToContinue(); break;
                case(2): movieList.printTopMoviesSales(); EnterToContinue(); break;
                default: choice = 3; break;
            }
            } while (choice != 3);
        } else {            do {
            System.out.println("Please select an option");
            System.out.println("1) Default listing");
            System.out.println("2) List the Top 5 Movies by Reviewers' Ratings");
            System.out.println("3) Return");
            choice = IOHandler.nextInt();
            switch (choice){
                case(1): movieList.printMovieList(); EnterToContinue(); break;
                case(2): movieList.printTopMovieReview(); EnterToContinue(); break;
                default: choice = 3; break;
            }
            } while (choice != 3);
        }
    }

    /**
    * This method show the Menu for setting holidays
    */ 
    public void setHolidayMenu(){
        cinemaStaff.setHoliday(movieTicCal);
    }
    
    /**
	 * This method in charges of setting the top movie list display type
	 */
    public void setTopMovieListMenu(){
        int choice;
        do {
            System.out.println("Please select an option");
            System.out.println("1) Set to display both");
            System.out.println("2) Set to display the Top 5 Movies by Ticket Sales");
            System.out.println("3) Set to display the Top 5 Movies by Reviewers' Ratings");
            System.out.println("4) Return");
            choice = IOHandler.nextInt();
            switch (choice){
             case(1): movieTicCal.setTopListingStatus(1);System.out.println("Set to display both!"); break;
                case(2): movieTicCal.setTopListingStatus(2);System.out.println("Set to display by Ticket Sales!"); break;
                case(3): movieTicCal.setTopListingStatus(3);System.out.println("Set to display by Reviewers' Ratings!"); break;
                default: choice = 4; break;
            }
        } while (choice != 4);
    }

    /**
    * This method help to search whether the movie indicated is in the movielist
    */ 
    public void MovieSearchMenu(){
        System.out.println("Please type the movie name: ");
        String movieName = sc.nextLine();
        Movie movie = movieList.searchMovie(movieName);
        if (movieList.searchMovie(movieName) != null){
            movie.printMovieDetail();
            EnterToContinue();
        } else {
            System.out.println("Movie not found");
        }
    }

    /**
    * This method help to search whether the movie indicated is in the movielist
    */ 

    public void MovieDetailFromListMenu(){
        System.out.println("Please select a movie");
        movieList.printMovieList();
        ArrayList<Movie> movieListArray = movieList.getMovieList();

        int choice = IOHandler.nextInt();

        if (1<=choice && choice <= movieListArray.size()){
            movieListArray.get(choice-1).printMovieDetail();
            EnterToContinue();
        } else {
            System.out.println("Invalid input");
        }
    }

    /**
     * This method helps to book a movie for the user
    */ 

    public void BookMovieMenu(){
        System.out.println("Please select a movie");
        movieList.printMovieListBooking();
        ArrayList<Movie> movieListArray = movieList.getBookableMovieList();

        int choice =IOHandler.nextInt();        
        if (1<=choice && choice <= movieListArray.size()){
            System.out.println("Available Showtimes: ");
            System.out.println("Please choose an available showtime: ");
            ArrayList<ShowTime> showTimeList = movieListArray.get(choice-1).getShowTime();
            if (showTimeList.size() == 0){
                System.out.println("No showtime available");
            } else {
                movieListArray.get(choice-1).printMovieShowTime();
                Movie movie = movieListArray.get(choice-1);
                choice = IOHandler.nextInt();        
                if (1<=choice && choice <= showTimeList.size()){
                    ShowTime showTime = showTimeList.get(choice-1);
                    BookingSession bookingSession = new BookingSession(showTime, currentMovieGoer, movieTicCal);
                    bookingSession.start();
                } else {
                    System.out.println("Invalid input");
                }
            }
        } else {    
            System.out.println("Invalid input");
        }
    }
    /**
     * This method will print out the booking details of the user
    */ 
    public void BookingDetailMenu(){
        currentMovieGoer.viewBookingDetail();
    }

    /**
     * This method is for user to give a review for a movie
    */ 
    public void GiveReviewMenu(){
        System.out.println("Please select a movie");
        movieList.printMovieList();
        ArrayList<Movie> movieListArray = movieList.getMovieList();

        int choice = IOHandler.nextInt();
        if (1<=choice && choice <= movieListArray.size()){
            ReviewMaker.makeReview(movieListArray.get(choice-1));
        } else {
            System.out.println("Invalid input");
        }
    }

    /**
     * This method ensure the user is able to go back to the menu
    */ 
    public void EnterToContinue(){
        System.out.println("Press enter key to continue");
        sc.nextLine();
    }
    
    /**
     * This method allows the admin to change the price of tickets
    */ 
     public void changeTicketPrice() {
    	int choice;
        do {
        	System.out.println("Please select option to change: ");
        	System.out.println("1) Base multiplier");
        	System.out.println("2) Cinema multipliers");
        	System.out.println("3) Age multipliers");
        	System.out.println("4) Movie Type multipliers");
        	System.out.println("5) Date multipliers");
            System.out.println("6) Return");
            choice = IOHandler.nextInt();
            switch (choice){
                case(1): updateBaseMultiplier();System.out.println("Base multiplier is succesfully changed"); break;
                case(2): updateCinemaMultiplier();System.out.println("Cinema multiplier is succesfully changed"); break;
                case(3): updateAgeMultiplier(); System.out.println("Age multiplier is succesfully changed");break;
                case(4): updateMovieTypeMultiplier();System.out.println("MovieType multiplier is succesfully changed"); break;
                case(5): updateDateMultiplier();System.out.println("Date multiplier is succesfully changed"); break; 
                case(6):break;
                default: 
                System.out.println("Please enter integer 1-6!");
                continue;
            }
        } while (choice != 6);
    }

    /**
     * This method allows the admin to change the multipler 
    */ 
    public void updateBaseMultiplier() {
    	System.out.println("Current base multiplier is " + movieTicCal.getBasePrice());
    	System.out.println("Enter new base multiplier (eg. 8.50): ");
    	double newBaseMultiplier = IOHandler.nextDouble();
    	cinemaStaff.updateBaseMultiplier(movieTicCal, newBaseMultiplier);
    }

    /**
     * This method allows the admin to change the cinemamultipler 
    */ 
   public void updateCinemaMultiplier() {
    	movieTicCal.printCinemaMultiplier();
    	int choice;
    	double multiplier;
        do {
        	while(true) {
        	System.out.println("Please select cinema type to change: ");
        	System.out.println("1) Normal");
        	System.out.println("2) Platinum Movie Suite");
            choice = IOHandler.nextInt();
            if(choice < 1 || choice > 2) {
            	System.out.println("Please enter choice (1-2)!");
            	continue;}
            else break;
        	}
            System.out.println("Please enter new multiplier: ");
            multiplier = IOHandler.nextDouble();
            switch (choice){
                case(1): cinemaStaff.updateCinemaMultiplier(movieTicCal, CinemaClass.NORMAL, multiplier); break;
                case(2): cinemaStaff.updateCinemaMultiplier(movieTicCal, CinemaClass.PLATINUM_MOVIE_SUITE, multiplier); break;
                case(3): break;
                default: System.out.println("Please only enter integer 1-3 ");continue;
            }
            break;
        } while (choice != 3);
    }
    
    /**
     * This method allows the admin to change the age multipler 
    */ 
    public void updateAgeMultiplier() {
    	movieTicCal.printAgeMultiplier();
    	int choice;
    	double multiplier;
        do {
        	while(true) {
        	System.out.println("Please select age to change: ");
        	System.out.println("1) Children");
        	System.out.println("2) Adult");
        	System.out.println("3) Senior citizen");
            choice = IOHandler.nextInt();
            if(choice < 1 || choice > 3) {
            	System.out.println("Please enter choice (1-3)!");
            	continue;}
            else break;
        	}
            System.out.println("Please enter new multiplier: ");
            multiplier = IOHandler.nextDouble();
            switch (choice){
            	case(1): cinemaStaff.updateAgeMultiplier(movieTicCal, AgeGroup.CHILDREN, multiplier); break;
                case(2): cinemaStaff.updateAgeMultiplier(movieTicCal, AgeGroup.ADULT, multiplier); break;
                case(3): cinemaStaff.updateAgeMultiplier(movieTicCal, AgeGroup.SENIOR_CITIZEN, multiplier); break;
                default: choice = 4; break;
            }
           break;
        } while (choice != 4);
    }
    
    
    /**
     * This method updates movietype multiplier
    */ 
   public void updateMovieTypeMultiplier() {
    	movieTicCal.printMovieMultiplier();
    	int choice;
    	double multiplier;
        do {
        	while(true) {
        	System.out.println("Please select movie type to change: ");
        	System.out.println("1) Regular");
        	System.out.println("2) 3D");
        	System.out.println("3) Blockbuster");
            choice = IOHandler.nextInt();
            if(choice < 1 || choice > 3) {
            	System.out.println("Please enter choice (1-3)!");
            	continue;}
            else break;
        	}
        	
            System.out.println("Please enter new multiplier: ");
            multiplier = IOHandler.nextDouble();
            switch (choice){
                case(1): cinemaStaff.updateMovieMultiplier(movieTicCal, MovieType.REGULAR, multiplier); break;
                case(2): cinemaStaff.updateMovieMultiplier(movieTicCal, MovieType._3D, multiplier); break;
                case(3): cinemaStaff.updateMovieMultiplier(movieTicCal, MovieType.BLOCKBUSTER, multiplier); break;
                default: choice = 4; break;
            }
            break;
        } while (choice != 4);
    }

    /**
     * This method allows the admin to change the date multipler 
    */ 
    public void updateDateMultiplier() {
    	movieTicCal.printDateMultiplier();
    	int choice;
    	double multiplier;
        do {
        	while(true) {
        	System.out.println("Please select date type to change: ");
        	System.out.println("1) Weekday");
        	System.out.println("2) Weekend");
        	System.out.println("3) Holiday");
            choice = IOHandler.nextInt();
            if(choice < 1 || choice > 3) {
            	System.out.println("Please enter choice (1-3)!");
            	continue;}
            else break;
        	}
            System.out.println("Please enter new multiplier: ");
            multiplier = IOHandler.nextDouble();
            
            switch (choice){
                case(1): cinemaStaff.updateDateMultiplier(movieTicCal, DateType.WEEKDAY, multiplier); break;
                case(2): cinemaStaff.updateDateMultiplier(movieTicCal, DateType.WEEKEND, multiplier); break;
                case(3): cinemaStaff.updateDateMultiplier(movieTicCal, DateType.HOLIDAY, multiplier); break;
                default: choice = 4; break;
            }
            break;
        } while (choice != 4);
    }
}