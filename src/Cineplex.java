import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class contains all the information of a cineplex
 */
public class Cineplex implements Serializable{	

    /**
	 * The list of cinemas in the cineplex
	 */
    private ArrayList<Cinema> cinemas;

    /**
	 * The location of the cineplex
	 */
    private String location;

	/**
	 * Creates a {@code Cineplex} with the given location
	 * @param location the location of the cineplex
	 */
    public Cineplex(String location) {
        this.location = location;
        cinemas = new ArrayList<Cinema>();
    }

    /**
	 * This method return the list of cinemas in the cineplex
	 * @return the list of cinemas in the cineplex
	 */
    public ArrayList<Cinema> getCinema() {
        return cinemas;
    }

    /**
	 * This method set the list of cinemas in the cineplex
	 * @param cinemas the list of cinemas to be set
	 */
    public void setCinema(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

	/**
	 * This method returns the location of the cineplex
	 * @return the location of the cineplex
	 */
    public String getLocation() {
        return location;
    }

	/**
	 * This method sets the location of the cineplex
	 * @param location the location of the cineplex to be set
	 */
    public void setLocation(String location) {
        this.location = location;
    }
}