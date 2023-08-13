import java.io.Serializable;

/**
 * This class contains all the information of a review rating
 */
public class Review implements Serializable{	

    /**
	 * The review text
	 */
    private String reviewText;

    /**
	 * The rating
	 */
    private float reviewRating;

    /**
	 * Creates a {@code Review} object with the given review and rating
	 * @param reviewText the review text
	 * @param reviewRating the rating
	 */
    public Review(String reviewText , float reviewRating)
    {
        this.reviewText = reviewText;
        this.reviewRating = reviewRating;
    }

	/**
	 * This method returns the review text
	 * @return the review
	 */
    public String getReviewText() {
		return reviewText;
	}

	/**
	 * This method returns the rating
	 * @return the rating
	 */
    public float getReviewRating(){
        return reviewRating;
    }

	/**
	 * This method prints the review
	 */
    public void printReview(){
        String reviewRatingString = String.format("%.1f", reviewRating);
        System.out.println(reviewRatingString + " - " + reviewText);
    }
}