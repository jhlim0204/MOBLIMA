import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.Serializable;

/**
 * This class contains all the information of a movie
 */
public class Movie implements Serializable{

    /**
	 * The name of the movie
	 */
    private String movieName;

    /**
	 * The synopsis of the movie
	 */
    private String movieSynopsis;

    /**
	 * The director of the movie
	 */
    private String movieDirectorName;	

    /**
	 * The list of cast names of the movie
	 */
    private ArrayList<String> movieCast;

    /**
	 * The list of show times of the movie
	 */
    private ArrayList<ShowTime> showTimes;

	/**
	 * The movie type of the movie
	 */
    private MovieType movieType;

    /**
	 * The release rating of the movie
	 */
    private MovieRating movieRating;	

    /**
	 * The showing status of the movie
	 */
    private ShowingStatus showingStatus;

    /**
	 * The duration of the movie in minutes
	 */
    private int duration;

    /**
	 * The date of end of show of the movie
	 */
    private Calendar endOfShow;

    /**
	 * The total sales of the movie
	 */
    private double ticketSales;

    /**
	 * The overall review rating of the movie
	 */
    private float overallReviewRating;

    /**
	 * The list of past review ratings of the movie
	 */
    private ArrayList<Review> pastReviews;

	/**
	 * Creates a {@code Movie} object with the given name, synopsis, director, casts, show times, movie type, release rating, showing status and duration
	 * @param movieName the name of the movie
	 * @param movieSynopsis the synopsis of the movie
	 * @param movieDirectorName the director of the movie
	 * @param movieCast the casts of the movie
	 * @param showTimes the show times of the movie
	 * @param movieType the movie type of the movie
	 * @param movieRating the release rating of the movie
	 * @param showingStatus the showing status of the movie
	 * @param duration the duration of the movie
	 */
    public Movie (String movieName, String movieSynopsis, String movieDirectorName, ArrayList<String> movieCast, ArrayList<ShowTime> showTimes, MovieType movieType, MovieRating movieRating, ShowingStatus showingStatus, int duration){
        this.movieName = movieName;
        this.movieSynopsis = movieSynopsis;
        this.movieDirectorName = movieDirectorName;
        this.movieCast = movieCast;
        this.showTimes = showTimes;
        this.movieType = movieType;
        this.movieRating = movieRating;
        this.showingStatus = showingStatus;
        this.duration = duration;
        this.ticketSales = 0; // Default to 0 upon creation
        this.overallReviewRating = 0; // Any issue with setting this to 0 from the start? NO
        this.pastReviews = new ArrayList<Review>();        
    }

	/**
	 * This method returns the name of the movie
	 * @return the name of the movie
	 */
    public String getMovieName(){
        return movieName;
    }

	/**
	 * This method sets the name for the movie
	 * @param movieName the new name of the movie
	 */
    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

	/**
	 * This method returns the synopsis of the movie
	 * @return the synopsis of the movie
	 */
    public String getMovieSynopsis(){
        return movieSynopsis;
    }

	/**
	 * This method sets the synopsis of the movie
	 * @param movieSynopsis the new synopsis of the movie
	 */
    public void setMovieSynopsis(String movieSynopsis){
        this.movieSynopsis = movieSynopsis;
    }

	/**
	 * This method returns the director name of the movie
	 * @return the director name of the movie
	 */
    public String getMovieDirectorName(){
        return movieDirectorName;
    }

	/**
	 * This method sets director name of the movie
	 * @param movieDirectorName the new director name of the movie
	 */
    public void setMovieDirectorName(String movieDirectorName){
        this.movieDirectorName = movieDirectorName;
    }

	/**
	 * This method returns the list of cast names of the movie
	 * @return the list of cast names of the movie
	 */
    public ArrayList<String> getMovieCast(){
        return movieCast;
    }

	/**
	 * This method sets the list of cast names of the movie
	 * @param movieCast the new list of cast names of the movie
	 */
    public void setMovieCast(ArrayList<String> movieCast){
        this.movieCast = movieCast;
    }

	/**
	 * This method adds a cast name to the list of cast names of the movie
	 * @param cast the cast name to be added
	 */
    public void addMovieCast(String cast){
        movieCast.add(cast);
    }

	/**
	 * This method returns the list of showtimes of a movie
	 * @return the list of showtimes of a movie
	 */
    public ArrayList<ShowTime> getShowTime(){
        return showTimes;
    }

	/**
	 * This method set the list of showtimes of a movie
	 * @param showTimes the new list of showtimes
	 */
    public void setShowTime(ArrayList<ShowTime> showTimes){
        this.showTimes = showTimes;
    }

	/**
	 * This method adds a showtime to the movie
	 * @param showTime the show time to be added
	 */
    public void addShowTime(ShowTime showTime){
        showTimes.add(showTime);
    }

	/**
	 * This method returns the movie type of the movie
	 * @return the movie type of the movie
	 */
    public MovieType getMovieType(){
        return movieType;
    }

	/**
	 * This method sets the movie type of the movie
	 * @param movieType the new movie type of the movie
	 */
    public void setMovieType(MovieType movieType){
        this.movieType = movieType;
    }

	/**
	 * This method returns the release rating of the movie
	 * @return the release rating of the movie
	 */
    public MovieRating getMovieRating(){
        return movieRating;
    }

	/**
	 * This method sets the release rating of the movie
	 * @param movieRating the new release rating of the movie
	 */
    public void setMovieRating(MovieRating movieRating){
        this.movieRating = movieRating;
    }

	/**
	 * This method checks if the movie is suitable for children
	 * @return true if the movie is suitable for children
	 */
    public boolean suitableForChildren(){
        return (movieRating == MovieRating.G || movieRating == MovieRating.PG || movieRating == MovieRating.PG13);
    }
    
	/**
	 * This method returns the showing status of the movie
	 * @return the showing status of the movie
	 */
    public ShowingStatus getShowingStatus(){
        return showingStatus;
    }

	/**
	 * This method sets the showing status of the movie
	 * @param showingStatus the new showing status of the movie
	 */
    public void setShowingStatus (ShowingStatus showingStatus){
        this.showingStatus = showingStatus;
    }

	/**
	 * This method returns the duration of the movie
	 * @return the duration of the movie
	 */
    public int getDuration(){
        return duration;
    }

	/**
	 * This method returns the end of show date of the movie
	 * @return the end of show date of the movie
	 */
    public Calendar getEndOfShow(){
        return endOfShow;
    }


	/**
	 * This method sets the end of show date of the movie
	 * @param endOfShow the new end of show date of the movie
	 */
    public void setEndOfShow(Calendar endOfShow){
        this.endOfShow = endOfShow;
    }

	/**
	 * This method updates the showing status of the movie automatically
	 */
    public void updateShowingStatus(){
        Calendar cal = Calendar.getInstance();
        if (cal.after(endOfShow)){
            this.showingStatus = ShowingStatus.END_OF_SHOWING;
        }
    }

	/**
	 * This method returns the total ticket sales of the movies
	 * @return the total ticket sales of the movie
	 */
    public double getTicketSales(){
        return ticketSales;
    }

	/**
	 * This method adds the ticket sales to the total ticket sales of the movie
	 * @param ticketSales the ticket sales to be added
	 */
    public void addTicketSales(double ticketSales){
        this.ticketSales += ticketSales;
    }

	/**
	 * This method return the overall review rating of the movie
	 * @return the overall review rating of the movie
	 */
    public float getOverallReviewRating(){
        return overallReviewRating;
    }

	/**
	 * This method returns the list of past review ratings of the movie
	 * @return the list of past review ratings of the movie
	 */
    public ArrayList<Review> getPastReviews(){
        return pastReviews;
    }

	/**
	 * This method adds the review to the list of past review ratings and update the overall review rating
	 * @param review the review to be added
	 */
    public void addReview(Review review){
        int numReview = pastReviews.size();
        pastReviews.add(review);
        float newTotalReviewRating = numReview * overallReviewRating + review.getReviewRating();
        overallReviewRating = newTotalReviewRating/pastReviews.size();
    }

	/**
	 * This method prints the showtime of the movie
	 */
    public void printMovieShowTime(){
        //Sort before printing
        Comparator<ShowTime> dateComparator = Comparator.comparing(ShowTime::getDateOfShow);
        showTimes.sort(dateComparator);
        
        int i = 1;
        for (ShowTime showTime : showTimes){
            System.out.println(i + ") " + showTime.getDateOfShowFormat() + " " + showTime.getCinema().getCineplexName() + " Cineplex"+ " - Cinema " + showTime.getCinema().getCinemaCode() + " - " + showTime.getCinema().getCinemaClass());
            i++;
        }
    }

	/**
	 * This method prints the detail of the movie
	 */
    public void printMovieDetail(){
        System.out.println(
            "Title: " + movieName + "\n" +
			"Synopsis: " + movieSynopsis + "\n" +
			"Director: " + movieDirectorName + "\n" +
			"Duration: " + duration + "\n" +
			"Showing Status: " + showingStatus + "\n" +
			"Release Rating: " + movieRating + "\n" +
			"Movie Type: " + movieType
        );

        System.out.println("Cast:");
        for (String castName: movieCast)
			System.out.println("- " + castName);

        System.out.print("Overall Reviewer Rating: ");
        if (pastReviews.size() <= 1){
            System.out.println("Not available");
        } else {
            //One decimal place
            System.out.printf("%.1f\n", overallReviewRating);

            System.out.println("Reviews: ");
            for (Review pastReview : pastReviews){
                System.out.print("- ");
                pastReview.printReview();
            }
        }
    }
}