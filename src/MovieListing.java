import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class contains all the information of the movie listing class
 */
public class MovieListing implements Serializable{	
    /**
	 * The list of movies
	 */
    private ArrayList<Movie> movieList;

 

	/**
	 * Creates a {@code MovieListing} object with the given movie list
	 * @param movieList the list of the movies
	 */
    public MovieListing(ArrayList<Movie> movieList){
        this.movieList = movieList;
    }

	/**
	 * This method return the display top listing status
	 * @return the display top listing status
	 */

	/**
	 * This method adds a movie to the movie list
	 * @param newMovie the movie to be added
	 */
    public void addMovie(Movie newMovie){
        movieList.add(newMovie);
    }

	/**
	 * This method deletes a movie from the movie list by setting the status to END_OF_SHOWING
	 * @param movieToBeRemoved the movie to be removed
	 */
    public void deleteMovie(Movie movieToBeRemoved){
        for (int i=0; i<movieList.size(); i++){
            if (movieList.get(i) == movieToBeRemoved){
                movieList.get(i).setShowingStatus(ShowingStatus.END_OF_SHOWING);
                break;
            }
        }
    }

	/**
	 * This method returns the list of movies which showing status is not end of show
	 * @return the list of movies
	 */
    public ArrayList<Movie> getMovieList(){
    	ArrayList<Movie> returnMovieList = new ArrayList<Movie>();
        for (int i=0; i<movieList.size();i++){
            movieList.get(i).updateShowingStatus();            
            if (movieList.get(i).getShowingStatus() != ShowingStatus.END_OF_SHOWING){
                returnMovieList.add(movieList.get(i));
            }
        }
        return returnMovieList;
    }

	/**
	 * This method returns the list of bookable movies
	 * @return the list of bookable movies
	 */
    public ArrayList<Movie> getBookableMovieList(){
    	ArrayList<Movie> bookableMovieList = new ArrayList<Movie>();
        for (int i=0; i<movieList.size();i++){
            movieList.get(i).updateShowingStatus();
            if (movieList.get(i).getShowingStatus() == ShowingStatus.PREVIEW || movieList.get(i).getShowingStatus() == ShowingStatus.NOW_SHOWING){
                bookableMovieList.add(movieList.get(i));
            }
        }
        return bookableMovieList;
    }
    
	/**
	 * This method search and return the according movie
	 * @param movieName the name of the movie to be searched
	 * @return the searched movie, if movie not found then return null
	 */
    public Movie searchMovie(String movieName){
        ArrayList<Movie> searchingMovieList = this.getMovieList();

        for(int i = 0;i<searchingMovieList.size();i++){
            if(movieName.equalsIgnoreCase(searchingMovieList.get(i).getMovieName())){
                return searchingMovieList.get(i);
            }
        }
        return null;
    }

	/**
	 * This method print the list of movies which showing status is not end of show
	 */
    public void printMovieList(){
        ArrayList<Movie> printingMovieList = this.getMovieList();

        for(int i = 0;i<printingMovieList.size();i++){
            //Sanity check, update the showingstatus automatically when user trying to search/view
            int index = i+1;
            System.out.println(index + ") " + printingMovieList.get(i).getMovieName());
        }
    }

	/**
	 * This method print the list of bookable movies
	 */
    public void printMovieListBooking(){       
        int count = 1;
        for(int i = 0;i<movieList.size();i++){
            movieList.get(i).updateShowingStatus();
            if (movieList.get(i).getShowingStatus() == ShowingStatus.PREVIEW || movieList.get(i).getShowingStatus() == ShowingStatus.NOW_SHOWING){
                System.out.println(count + ") " + movieList.get(i).getMovieName());
                count ++;
            }
        }
    }

	/**
	 * This method print the top 5 movie by overall review rating
	 */
    public void printTopMovieReview(){
        // copy this arrayList to sortedMovieList
        ArrayList<Movie> pendingMovieList = this.getMovieList();
        ArrayList<Movie> sortedMovieListReview = new ArrayList<Movie>();
        for(int i = 0;i<pendingMovieList.size();i++){
            sortedMovieListReview.add(pendingMovieList.get(i));
        }
        
        // do sorting Insertion
        Movie temp;
        for(int i = 1;i<sortedMovieListReview.size();i++){
            for(int j = i;j>0;j--){
                if(sortedMovieListReview.get(j).getOverallReviewRating ()>sortedMovieListReview.get(j-1).getOverallReviewRating ()){
                    // do swapping
                    temp = sortedMovieListReview.get(j);
                    sortedMovieListReview.set(j, sortedMovieListReview.get(j-1));
                    sortedMovieListReview.set(j-1,temp);
                }
                else{
                    break;
                }
            }
        }

        // then print top 5 movies
        for(int i = 0;i<sortedMovieListReview.size();i++){
            if (i==5){
                break;
            }
            int num = i+1;
            sortedMovieListReview.get(i).updateShowingStatus();
            Float rating = sortedMovieListReview.get(i).getOverallReviewRating();
            String ratingString = String.format("%.01f", rating);
            System.out.println((num + ") " + sortedMovieListReview.get(i).getMovieName()) + " - " + ratingString);
        }
  
    }

	/**
	 * This method print the top 5 movie by ticket sales
	 */
    public void printTopMoviesSales(){
        // copy this arrayList to sortedMovieList
        ArrayList<Movie> pendingMovieList = this.getMovieList();
        ArrayList<Movie> sortedMovieListSales = new ArrayList<Movie>();
        for(int i = 0;i<pendingMovieList.size();i++){
            sortedMovieListSales.add(pendingMovieList.get(i));
        }
        
        // do sorting Insertion
        Movie temp;
        for(int i = 1;i<sortedMovieListSales.size();i++){
            for(int j = i;j>0;j--){
                if(sortedMovieListSales.get(j).getTicketSales()>sortedMovieListSales.get(j-1).getTicketSales()){
                    // do swapping
                    temp = sortedMovieListSales.get(j);
                    sortedMovieListSales.set(j, sortedMovieListSales.get(j-1));
                    sortedMovieListSales.set(j-1,temp);
                }
                else{
                    break;
                }
            }
        }

        // then print top 5 movies
        for(int i = 0;i<sortedMovieListSales.size();i++){
            if (i==5){
                break;
            }
            int num = i+1;
            sortedMovieListSales.get(i).updateShowingStatus();
            Double sales = sortedMovieListSales.get(i).getTicketSales();
            String salesString = String.format("%.02f", sales);
            System.out.println((num + ") " + sortedMovieListSales.get(i).getMovieName()) + " - $" + salesString);
        }
    }
}