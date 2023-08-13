import java.io.Serializable;
import java.util.*;

/**
 * This class contains all the information of a cinema staff
 */
public class CinemaStaff implements Account, Serializable{
    /**
	 * The username of the cinema staff
	 */
    private String user;

    /**
    * The password of the cinema staff
     */
    private String password;

    /**
     * Creates a new {@code CinemaStaff} object with the given user and password
     * @param user the user of the cinema staff
     * @param password the password of the cinema staff
     */
    public CinemaStaff(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
	 * Gets the user of this CinemaStaff.
     * @return this CinemaStaff's user
	 */
    public String getName() {
    	return user;
    }

    /**
     * Check if inputted parameters are equivalent to the correct user and password in our database.
     * @param user value of user to be verified
     * @param password value of password to be verified
     * @return true or false based on whether it matches
     */
    public boolean login(String user, String password) {
        return(this.user.equals(user) && this.password.equals(password));
    }

    /**
     * Creates a new Movie.
     * @return Movie that has been created.
     */
    public Movie createMovieListing() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie name: ");
        String name = sc.nextLine();
        
        String synopsis = MovieInputHandler.inputSynopsis();
        String director = MovieInputHandler.inputDirector();
        int castCount = MovieInputHandler.inputCastCount();
        
        ArrayList<String> cast = new ArrayList<String>();
        for(int i = 1; i <= castCount; i++) {
            System.out.println("Enter cast " + i + " name: ");
            cast.add(sc.nextLine());
        }
        
        System.out.println("Enter movie duration: ");
        int duration = IOHandler.nextInt();

	    MovieType movieType = MovieInputHandler.selectType();
        MovieRating movieRating = MovieInputHandler.selectMovieRating();
        ShowingStatus showingStatus = MovieInputHandler.selectStatus();
        
        ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
        Movie newMovie = new Movie(name,synopsis,director,cast,showTimes,movieType,movieRating,showingStatus, duration);


        ArrayList<Cineplex> cineplexList = DataManager.getDataStore().getCineplexList();
  	  	Cineplex cineplex = MovieInputHandler.selectCineplex(cineplexList);
        Cinema cinema = MovieInputHandler.selectCinema(cineplex);

        // Creating potential showtimes for the movie
        Calendar dateOfShow;
        System.out.println("Enter number of showtimes: ");
        int showTimeCount = IOHandler.nextInt(); 
        
        for(int i = 0; i < showTimeCount; i++) {
            System.out.println("Enter showtime " + i + " : ");
            dateOfShow = ShowTime.inputTime();
            ShowTime tempShowTime = new ShowTime(dateOfShow,cinema, cinema.getSeatLayout(), newMovie);
            
            boolean clash = cinema.checkClash(tempShowTime, null);

            if(clash){
                System.out.println("The entered showtime CLASHES!! Try again later");
            }
            else{
                showTimes.add(tempShowTime);
                cinema.addShowTime(tempShowTime);
                System.out.println("Successfully created movie showtime.");
            }
        }
        return newMovie;
    } 

    /**
     * Updates an existing Movie's detail.
     * @param movieToUpdate is the movie to be updated
     * @param synopsis is the new synopsis
     * @param director is the director of the movie
     * @param castCount is the number of casts
     */
    public void updateMovieDetails(Movie movieToUpdate, String synopsis, String director, int castCount) {
    	Scanner sc = new Scanner(System.in);
        ArrayList<String> cast = new ArrayList<String>();
        String tempName = null;
        
        movieToUpdate.setMovieSynopsis(synopsis);
        movieToUpdate.setMovieDirectorName(director);
        
        for(int i = 0; i < castCount; i++) {
            while (true) {
            	System.out.println("Enter cast " + (i+1) + " name: ");
                tempName = sc.nextLine();
	            if (tempName.matches(".*\\d.*")) {
	            	System.out.println("Enter a valid name without integers e.g John Tan");
	                continue;
	            }
	            break;
            }
            cast.add(tempName);
        }
        movieToUpdate.setMovieCast(cast);
    }
    
    /**
     * Updates an existing Movie's MovieType.
     * @param movieToUpdate is the movie to be updated
     * @param movieType is the new MovieType
     */
    public void updateMovieType(Movie movieToUpdate, MovieType movieType) {
        movieToUpdate.setMovieType(movieType);
    }
    
    /**
     * Updates an existing Movie's MovieRating.
     * @param movieToUpdate is the movie to be updated
     * @param movieRating is the new MovieRating
     */
    public void updateMovieRating(Movie movieToUpdate, MovieRating movieRating) {
        movieToUpdate.setMovieRating(movieRating);
    }
    
    /**
     * Updates an existing Movie's ShowingStatus.
     * @param movieToUpdate is the movie to be updated
     * @param showingStatus is the new ShowingStatus
     */
    public void updateMovieShowingStatus(Movie movieToUpdate, ShowingStatus showingStatus) {
        movieToUpdate.setShowingStatus(showingStatus);
    }

    /**
     * Removes an existing Movie.
     * @param movieList the movie listing of the movie to be removed
     */
    public void removeMovieListing(MovieListing movieList) {
        movieList.printMovieList();
        ArrayList<Movie> pendingMovieList = movieList.getMovieList();
        System.out.println("Enter movie index to remove: ");
        
        int index =  IOHandler.nextInt();
        
        if (1 <= index && index <= pendingMovieList.size()) {
            Movie movieToBeRemoved = pendingMovieList.get(index - 1);
            movieList.deleteMovie(movieToBeRemoved);
        } else {
            System.out.println("Invalid input");
        }
   } 

   /**
     * Create a new ShowTime for a Movie.
     * @param cinema is the Cinema in which the Movie is airing
     * @param movie is the Movie that will be have a new ShowTime
     */
   public void createMovieShowTime(Cinema cinema, Movie movie) {
        Calendar dateOfShow;
        dateOfShow = ShowTime.inputTime();
        ShowTime newShowTime = new ShowTime(dateOfShow, cinema, cinema.getSeatLayout(), movie);

        boolean clash = cinema.checkClash(newShowTime, null);
        
        if(clash){
            System.out.println("The entered showtime CLASHES!! Try again later");
        }
        else{
            cinema.getShowTime().add(newShowTime);
            movie.getShowTime().add(newShowTime);
            System.out.println("Successfully created movie showtime.");
        }
    }

    /**
     * Updates an existing Movie's ShowTime.
     * @param movie is the Movie to edit
     * @param cinema is the Cinema in which the Movie is airing
     */
    public void updateMovieShowTime(Movie movie, Cinema cinema) {
        removeMovieShowTime(cinema, movie);
        createMovieShowTime(cinema, movie);
    }

    /**
     * Removes an existing Movie's ShowTime.
     * @param cinema is the cinema of the showtime to delete
     * @param movie is the movie of the showtime to delete
     * @return return whether the showtime can be deleted or not
     */
    public boolean removeMovieShowTime(Cinema cinema, Movie movie) {
    	int count = 0;
        ArrayList<ShowTime> movieShowTime = movie.getShowTime();
        System.out.println("ShowTime:");
        for(int i = 0; i < movieShowTime.size(); i++){
            if (movieShowTime.get(i).getCinema() == cinema) {
            	count++;
                System.out.println(i+1 + ") "+movieShowTime.get(i).getDateOfShowFormat());
            }
        }

        if (count == 0) {
        	System.out.println("There is no available showtime for this cinema");
        	return false;
        }
        
        int index;
        while (true) {
	        System.out.println("Enter index of Movie ShowTime to be removed:");
	        index =  IOHandler.nextInt()-1;
	        if (index >= 0 && index <= movieShowTime.size()) break;
	        else {
	        	System.out.println("You are trying to remove an invalid movie showtime.");
	        	return false;
	        }
        }
        
        ShowTime affectedShowTime = movieShowTime.get(index);
        
        if(affectedShowTime.isBookedBySomeone()){
            System.out.println("You are trying to remove an booked movie showTime.");
            return false;
        }
        else {
        	Cinema affectedCinema = affectedShowTime.getCinema();
        	
            // remove the ShowTime from the cinema and movie showtime
            for(int i = 0;i<affectedCinema.getShowTime().size();i++){
                if(affectedCinema.getShowTime().get(i).equals(affectedShowTime)){
                	affectedCinema.getShowTime().remove(i);
                    movieShowTime.remove(index);  
                    break;
                }
            }
            return true;
        }   
    }
    
    /**
     * Updates the BaseMultiplier for Ticket Price.
     * @param movieTicCal is the movieTicCal used to compute ticket prices
     * @param newBaseMultiplier is the new value to set BaseMultiplier to
     */
    public void updateBaseMultiplier(MovieTicCal movieTicCal, double newBaseMultiplier) {
    	movieTicCal.setBasePrice(newBaseMultiplier);
    }

    /**
     * Updates the CinemaMultiplier for Ticket Price.
     * @param movieTicCal is the movieTicCal used to compute ticket prices
     * @param cinemaClass is the CinemaClass that we want to edit multiplier for
     * @param multiplier is the new value to set CinemaMultiplier to
     */
    public void updateCinemaMultiplier(MovieTicCal movieTicCal, CinemaClass cinemaClass, double multiplier) {
    	movieTicCal.setCinemaMultiplier(cinemaClass, multiplier);
    }

    /**
     * Updates the AgeMultiplier for Ticket Price.
     * @param movieTicCal is the movieTicCal used to compute ticket prices
     * @param ageGroup is the AgeGroup that we want to edit multiplier for
     * @param multiplier is the new value to set the specific AgeMultiplier to
     */ 
    public void updateAgeMultiplier(MovieTicCal movieTicCal, AgeGroup ageGroup, double multiplier) {
    	movieTicCal.setAgeMultiplier(ageGroup, multiplier);
    }

    /**
     * Updates the MovieMultiplier for Ticket Price.
     * @param movieTicCal is the movieTicCal used to compute ticket prices
     * @param movieType is the MovieType that we want to edit multiplier for
     * @param multiplier is the new value to set the specific MovieMultiplier to
     */ 
    public void updateMovieMultiplier(MovieTicCal movieTicCal, MovieType movieType, double multiplier) {
    	movieTicCal.setMovieMultiplier(movieType, multiplier);
    }

    /**
     * Updates the DateMultiplier for Ticket Price.
     * @param movieTicCal is the movieTicCal used to compute ticket prices
     * @param dateType is the DateType that we want to edit multiplier for
     * @param multiplier is the new value to set the specific DateMultiplier to
     */ 
    public void updateDateMultiplier(MovieTicCal movieTicCal, DateType dateType, double multiplier) {
    	movieTicCal.setDateMultiplier(dateType, multiplier);
    }

    /**
     * Set a date to be have Holiday DateType
     * @param movieTicCal is the movieTicCal used to compute ticket prices
     */ 
    public void setHoliday(MovieTicCal movieTicCal){
       System.out.println("Enter Options:");
        System.out.println("1) Add Holiday");
        System.out.println("2) Remove Holiday");
        int option = IOHandler.nextInt();
        Calendar date;
        
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

                int hour = 0;
                int minute = 0;
                int second = 0;
                
                aDate = new GregorianCalendar(year, month, day, hour, minute, second);
                aDate.setLenient(false);
                aDate.getTime();
                done = true;
            } catch (Exception e){
                System.out.println("Invalid date, please try again");
            }
        }
        date = aDate;

        switch(option){
            case 1: movieTicCal.addHoliday(date);break;
            case 2: movieTicCal.removeHoliday(date);break;
            default: break;
        }
    }
}
