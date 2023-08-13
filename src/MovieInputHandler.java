import java.util.ArrayList;
import java.util.Scanner;

/**
 * This interface contains utility functions for reading required data for movie
 */
public interface MovieInputHandler {
	
    /**
	 * This method reads in the synopsis and returns it
	 * @return the read synopsis
	 */
	public static String inputSynopsis() {
        String synopsis = null;
        Scanner sc = new Scanner(System.in);
        while (true) {
        	System.out.println("Enter movie synopsis: ");
        	synopsis = sc.nextLine();
        	if (synopsis!=null) break;
        	else {
        		System.out.println("Please enter a valid synopsis.");
        		continue;
        	}
        }
        return synopsis;
	}
	
    /**
	 * This method reads in the director and returns it
	 * @return the read director
	 */
	public static String inputDirector() {
        String director = null;
        Scanner sc = new Scanner(System.in);
        while (true) {
        	System.out.println("Enter movie director name: ");
        	director = sc.nextLine();
        	if (director!=null) break;
        	else {
        		System.out.println("Please enter a valid director name.");
        		continue;
    		}
    	}
        return director;
	}
	
    /**
	 * This method reads in the cast count and returns it
	 * @return the read cast count
	 */
	public static int inputCastCount() {
		int castCount = -1;
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter number of cast: ");
            castCount = IOHandler.nextInt();
            if (castCount>=2) break;
        	else {
        		System.out.println("Please enter a valid cast count (at least 2).");
        		continue;
    		}
		}
        return castCount;
	}
	
    /**
	 * This method prompts users to select the movie type and returns it
	 * @return the selected movie type
	 */
	public static MovieType selectType() {
		MovieType movieType = MovieType.REGULAR;
		int movietypechoice;
	    while (true) {
	    	System.out.println("Movie Types:");
			System.out.println("1.REGULAR");
			System.out.println("2.3D");
			System.out.println("3.BLOCKBUSTER");
			System.out.println("Enter Choice (1-3):");
	    	movietypechoice = IOHandler.nextInt();
		    switch(movietypechoice){
			    case 1:
			    	movieType = MovieType.REGULAR ;
			    	break;
			    case 2:
			    	movieType = MovieType._3D ;
			    	break;
			    case 3:
			    	movieType = MovieType.BLOCKBUSTER;
			    	break;
			    default:
			    	break;
		    }
		    if (movietypechoice>=1 && movietypechoice<=3) break;
		    else {
		    	System.out.println("Please select a valid movie type (1-3).");
		    	continue;
		    }
	    }
	    return movieType;
	}
	
    /**
	 * This method prompts users to select the showing status and returns it
	 * @return the selected showing status
	 */
	public static ShowingStatus selectStatus() {
		ShowingStatus showingStatus = ShowingStatus.COMING_SOON;
		int statusChoice;
		
        while(true) {
        	System.out.println("Showing Status:");
            System.out.println("1.Coming Soon");
            System.out.println("2.Preview");
            System.out.println("3.Now Showing");
            System.out.println("4.End Of Showing");
            System.out.println("Enter Choice (1-4):");
        	
            statusChoice = IOHandler.nextInt();
            switch(statusChoice){
	            case 1:
	            	showingStatus = ShowingStatus.COMING_SOON ;
	            	break;
	            case 2:
	            	showingStatus = ShowingStatus.PREVIEW ;
	            	break;
	            case 3:
	            	showingStatus = ShowingStatus.NOW_SHOWING ;
	            	break;
	            case 4:
	            	showingStatus = ShowingStatus.END_OF_SHOWING ;
	            	break;
	            default:
	            	break;
            }
            if (statusChoice>=1 && statusChoice<=4) break;
		    else {
		    	System.out.println("Please select a valid showing status (1-4).");
		    	continue;
		    }
        }
	    return showingStatus;
	}
	
    /**
	 * This method prompts users to select the movie rating and returns it
	 * @return the selected movie rating
	 */
	public static MovieRating selectMovieRating() {
		MovieRating movieRating = MovieRating.G;
		int movieRatingChoice;
		
        while(true) {
        	System.out.println("Movie Rating:");
            System.out.println("1.G");
            System.out.println("2.PG");
            System.out.println("3.PG13");
            System.out.println("4.NC16");
            System.out.println("5.M18");
            System.out.println("6.R21");
            System.out.println("Enter Choice (1-6):");
        	
        	movieRatingChoice = IOHandler.nextInt();
            switch(movieRatingChoice){
    	        case 1:
    	            movieRating = MovieRating.G ;
    	            break;
    	        case 2:
    	            movieRating = MovieRating.PG ;
    	            break;
    	        case 3:
    	        	movieRating = MovieRating.PG13 ;
    	        	break;
    	        case 4:
    	        	movieRating = MovieRating.NC16 ;
    	        	break;
    	        case 5:
    	        	movieRating = MovieRating.M18 ;
    	        	break;
    	        case 6:
    	        	movieRating = MovieRating.R21 ;
    	        	break;
    	        default:
    	        	break;
            }
            if (movieRatingChoice>=1 && movieRatingChoice<=6) break;
		    else {
		    	System.out.println("Please select a valid movie rating (1-6).");
		    	continue;
		    }
        }
	    return movieRating;
	}
	
    /**
	 * This method prompts users to select the cineplex and returns it
	 * @param cineplexList the respective cineplexList
	 * @return the selected cineplex
	 */
	public static Cineplex selectCineplex(ArrayList<Cineplex> cineplexList) {
		  Cineplex cineplex;
		  int cineplexIndex;
		  
		  while (true) {
		        System.out.println("List of cineplexes:");  
		 		for (int i=0; i<cineplexList.size(); i++) {
		 			System.out.println((i+1) + ") " + cineplexList.get(i).getLocation());
		    	}
		    
		    	System.out.println("Please choose the cineplex:");  
		    	cineplexIndex = IOHandler.nextInt();
		    	if (cineplexIndex>=1 && cineplexIndex<=cineplexList.size()) {
		    		cineplex = cineplexList.get(cineplexIndex-1); 
		    		break;
		    	}
		    	else {
		    		System.out.println("Please select a valid cineplex. (1-" + cineplexList.size() + ')');
		    		continue;
		    	}
		  }
		  return cineplex;
	}
	
    /**
	 * This method prompts users to select the cinema and returns it
	 * @param cineplex the respective cineplex
	 * @return the selected cinema
	 */
	public static Cinema selectCinema(Cineplex cineplex) {
        Cinema cinema;
        int cinemaCode;
        
        while (true) {
	    	System.out.println("List of cinemas:");  
	    	ArrayList<Cinema> cinemaList = cineplex.getCinema();
	    	
	 		for (int i=0; i<cinemaList.size(); i++) {
	 			System.out.println((i+1) + ") Cinema " + cinemaList.get(i).getCinemaCode());
	    	}
	    	
	    	System.out.println("Please choose the cinema:");  
	    	cinemaCode = IOHandler.nextInt();
	    	if (cinemaCode>=1 && cinemaCode<=cinemaList.size()) {
	    		cinema = cinemaList.get(cinemaCode-1); 
	    		break;
        	}
	    	else {
	    		System.out.println("Please select a valid cinema. (1-" + cinemaList.size() + ')');
	    		continue;
	    	}
        }
        return cinema;
	}
}
