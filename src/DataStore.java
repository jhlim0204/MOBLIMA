import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains all the data of the application
 */
public class DataStore implements Serializable {
	/**
	 * The list of cineplexes
	 */
    private ArrayList<Cineplex> cineplexList;
    /**
	 * The list of movies
	 */
    private ArrayList<Movie> movieList;	
    /**
	 * The pricing scheme
	 */
    private MovieTicCal movieTicCal;
    /**
	 * The cinema staff info
	 */
    private CinemaStaff cinemaStaff;
    /**
	 * The movie goer list
	 */
    private ArrayList<MovieGoer> movieGoerList;
    
    /**
	 * Creates a {@code DataStore} object with default data
	 */
    public DataStore(){
        cineplexList = new ArrayList<Cineplex>();
        //create 3 cineplexes
        cineplexList.add(new Cineplex("Jurong"));
        cineplexList.add(new Cineplex("Orchard"));
        cineplexList.add(new Cineplex("Woodlands"));

        //3 cinemas for each cineplex
        for (int i=0; i<3; i++){
            for (int j=0; j<2; j++){
                cineplexList.get(i).getCinema().add(new Cinema(i*3+j,CinemaClass.NORMAL, 15,2,14, cineplexList.get(i).getLocation()));
            }
            cineplexList.get(i).getCinema().add(new Cinema(i*3+2,CinemaClass.PLATINUM_MOVIE_SUITE, 10,3,10, cineplexList.get(i).getLocation()));
        }

        movieList = new ArrayList<Movie>();

        //2 Default movies for testing purpose
        ArrayList<String> movieCast1 = new ArrayList<String>() {
            {
                add("Ra Mi-ran");
                add("Song Sae-byeok");
            }
        };
        Movie movie1 = new Movie("Come Back Home", "In the cold winter, a group of Shenzhen tourist families take a trip to the northeast Changbai Mountain. It was originally intended to be a happy and harmonious holiday, but due to the negligence of his father, an 8-year-old boy is unfortunately lost. The parents seek the help of the local authorities, and the search and rescue operation begins immediately. The golden rescue time of 24 hours passes, followed by the routine safety limit of 48 hours, but the child is still nowhere to be found. Even if there is little hope, the father and the search and rescue teams will not give up.", "Lee Yeon-woo", movieCast1, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.G, ShowingStatus.PREVIEW, 100);

        movieList.add(movie1);
        Calendar dateShowTime1 = new GregorianCalendar(2022, 11, 27, 10, 0, 0);
        Cinema cinema1 = cineplexList.get(0).getCinema().get(0);
        ShowTime showTime1 = new ShowTime(dateShowTime1, cinema1, cinema1.getSeatLayout(), movie1);
        movie1.addShowTime(showTime1);
        cinema1.addShowTime(showTime1);

        ArrayList<String> movieCast2 = new ArrayList<String>() {
            {
                add("Jacqueline Byers");
                add("Virginia Madsen");
            }
        };

        Movie movie2 = new Movie("Pray for Devil", "Sister Ann (Jacqueline Byers) believes she is answering a calling to be the first female exorcist but who, or what, called her? In response to a global rise in demonic possessions, Ann seeks out a place at an exorcism school reopened by the Catholic Church. Until now these schools have only trained priests in the Rite of Exorcism but a professor (Colin Salmon) recognizes Sister Ann gifts and agrees to train her. Thrust onto the spiritual frontline with fellow student Father Dante (Christian Navarro), Sister Ann finds herself in a battle for the soul of a young girl, who Sister Ann believes is possessed by the same demon that tormented her own mother years ago. Determined to root out the evil, Ann soon realizes the Devil has her right where he wants her.\r\n"
        		, "Daniel Stamm", movieCast2, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.NC16, ShowingStatus.COMING_SOON, 70);
        movieList.add(movie2);
        
        ArrayList<String> movieCast3 = new ArrayList<String>() {
            {
                add("Hong Huifang");
                add("Kang Hyung Suk");
            }
        };
        
        Movie movie3 = new Movie("Ajoomma","Produced by award-winning filmmaker Anthony Chen. Auntie (Hong Huifang), is a middle-aged Singaporean woman who has dedicated the best years of her life to caring for her family. Now widowed with her grown up son, Sam (Shane Pow) about to fly the roost, Auntie is left to contend with a whole new identity beyond her roles of daughter, wife, and mother.\r\n"
        		+ "A solo trip to Korea becomes a wild adventure for Auntie, where she meets Kwon-Woo (Kang Hyung Suk), a young tour guide who can seem to get his life in order, and Jung Su (Jung Dong-Hwan), an elderly security guard. The trio embark on an unexpected roller coaster ride where hearts flutter and unlikely bonds are formed.\r\n"
        		+ "Inspired by the director mother, AJOOMMA is the story of a woman journey of self-discovery, where Auntie learns to embrace her new independent life with renewed confidence and panache.","He Shuming", movieCast3, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.NC16, ShowingStatus.NOW_SHOWING, 70);
        movieList.add(movie3);
        
        ArrayList<String> movieCast4 = new ArrayList<String>() {
            {
                add("Zheng Ge Ping");
                add("Vincent Ng");
            }
        };
        
        Movie movie4 = new Movie("Deleted","The story starts with a Malaysian police detective Chia Zhong Yi. In his desperate search for his daughter Hazel who was being kidnapped by child traffickers. He unintentionally caused grievous hurt to a male suspect in a moment of rashness. As a consequence of his actions, he was convicted and sentenced to 3 years in prison. Nevertheless, he never gave up hope in finding his daughter. Exploiting his status as an ex-convict, he infiltrated the crime syndicate and befriended a human trafficker Ghost, to find out about his daughter whereabouts.","Ken Ng Lai Huat", movieCast4, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.NC16, ShowingStatus.NOW_SHOWING,70);
        movieList.add(movie4);
        
        
        ArrayList<String> movieCast5 = new ArrayList<String>() {
            {
                add( "Sam Rockwell");
                add("David Oyelowo");
            }
        };
        
        Movie movie5 = new Movie("See How They Run","In the West End of 1950s London, plans for a movie version of a smash-hit play come to an abrupt halt after a pivotal member of the crew is murdered. When world-weary Inspector Stoppard (Sam Rockwell) and eager rookie Constable Stalker (Saoirse Ronan) take on the case, the two find themselves thrown into a puzzling whodunit within the glamorously sordid theater underground, investigating the mysterious homicide at their own peril.\r\n"
        		+ "","Tom George", movieCast5, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.NC16, ShowingStatus.NOW_SHOWING, 70);
        movieList.add(movie5);
        
        
        ArrayList<String> movieCast6 = new ArrayList<String>() {
            {
                add("Dwayne Johnson");
                add("Noah Centineo");
            }
        };
        
        Movie movie6 = new Movie("Black Adam","Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods -- and imprisoned just as quickly -- Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.\r\n"
        		+ "","Jaume Collet-Serra", movieCast6, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.PG13, ShowingStatus.NOW_SHOWING, 70);
        movieList.add(movie6);
        
        
        ArrayList<String> movieCast7 = new ArrayList<String>() {
            {
                add("Jamie Lee Curtis");
                add("Judy Greer");
            }
        };
        
        Movie movie7 = new Movie("Halloween Ends","Laurie Strode prepares for a final showdown with masked killer Michael Myers.\r\n"
        		+ "", "David Gordon Green", movieCast7, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.M18, ShowingStatus.NOW_SHOWING, 70);
        movieList.add(movie7);
        
        
        ArrayList<String> movieCast8 = new ArrayList<String>() {
            {
                add("Sosie Bacon");
                add("Kyle Gallner");
            }
        };
        
        Movie movie8 = new Movie("Smile","After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter (Sosie Bacon) starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying new reality.\r\n"
        		+ "","Parker Finn", movieCast8, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.M18, ShowingStatus.NOW_SHOWING, 70);
        movieList.add(movie8);
        
        
        ArrayList<String> movieCast10 = new ArrayList<String>() {
            {
                add("Dwayne Johnson");
                add("Diego Luna");
            }
        };
        
        Movie movie10 = new Movie("DC League Of Super-Pets","In \"DC League of Super-Pets,\" Krypto the Super-Dog and Superman are inseparable best friends, sharing the same superpowers and fighting crime in Metropolis side by side. When Superman and the rest of the Justice League are kidnapped, Krypto must convince a rag-tag shelter pack--Ace the hound, PB the potbellied pig, Merton the turtle and Chip the squirrel--to master their own newfound powers and help him rescue the superheroes.\r\n"
        		+ "","Jared Stern\r\n"
        		+ "\r\n"
        		+ "", movieCast10, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.PG13, ShowingStatus.NOW_SHOWING, 70);
        movieList.add(movie10);
        
        
        
        ArrayList<String> movieCast9 = new ArrayList<String>() {
            {
                add("Fuka Koshiba");
                add("Kazuma Kawamura");
            }
        };
        
        Movie movie9 = new Movie("Sadako Dx","The Ring Curse mutates and spreads. Ayaka Ichijo (Fuka Koshiba) is a graduate student with an IQ of 200 who tries to investigate the strange deaths happening nationwide after people supposedly watched a cursed video. Ayaka is sceptical about the video and the legend surrounding it, which have become a viral sensation. She soon learns that her younger sister has viewed a copy of the video out of curiosity, and her death is set to occur in 24 hours. Can Ayaka uncover the mystery behind the cursed video and save her sister?\r\n"
        		+ "","Hisashi Kimura\r\n"
        		+ "\r\n"
        		+ "", movieCast9, new ArrayList<ShowTime>(), MovieType.REGULAR, MovieRating.M18, ShowingStatus.COMING_SOON, 70);
        movieList.add(movie9);
        

        movieTicCal = new MovieTicCal();
        cinemaStaff = new CinemaStaff("admin", "admin123");
        movieGoerList = new ArrayList<MovieGoer>();
    }

	/**
	 * This method returns the cineplex list
	 * @return the cineplex list
	 */
    public ArrayList<Cineplex> getCineplexList(){
        return cineplexList;
    }

	/**
	 * This method returns the movie list
	 * @return the movie list
	 */
    public ArrayList<Movie> getMovieList(){
        return movieList;
    }

    /**
	 * This method returns the pricing scheme
	 * @return the pricing scheme
	 */
    public MovieTicCal getMovieTicCal(){
        return movieTicCal;
    }

    /**
	 * This method returns the cinema staff
	 * @return the cinema staff
	 */
    public CinemaStaff getCinemaStaff(){
        return cinemaStaff;
    }

    /**
	 * This method returns the movie goer list
	 * @return the movie goer list
	 */
    public ArrayList<MovieGoer> getMovieGoerList(){
        return movieGoerList;
    }
}