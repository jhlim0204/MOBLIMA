import java.util.Scanner;

/**
 * This class is used to make review
 */
public class ReviewMaker{
    
    /**
	 * This static method will prompt the user to type their review and add it into the corresponding movie
	 * @param movie movie to make review
	 */
    public static void makeReview(Movie movie){
        // prompt user to type reviewText and reviewRating
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Review Text:");
        String newReviewText = sc.nextLine();
        System.out.println("Enter review rating (1-5):");
        float newReviewRating = IOHandler.nextFloat();
        if (newReviewRating < 1.0f){
            newReviewRating = 1.0f;
        } else if (newReviewRating > 5.0f){
            newReviewRating = 5.0f;
        }

        // create a review Object using the above reviewText and review Rating
        Review newReview = new Review(newReviewText, newReviewRating);

        // put this review into the target movie
        movie.addReview(newReview);
    }
}