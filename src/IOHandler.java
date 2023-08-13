import java.util.Scanner;

/**
 * This class contains utility functions for reading numbers
 */
public class IOHandler {	

    /**
	 * This method reads in an integer with exception handling
	 * @return the read integer
	 */
    public static Integer nextInt() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                Integer output = sc.nextInt(); sc.nextLine();
                return output;
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
        }
    }

    /**
	 * This method reads in a float number with exception handling
	 * @return the read float number
	 */
    public static Float nextFloat() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                Float output = sc.nextFloat(); sc.nextLine();
                return output;
            } catch (Exception e) {
                System.out.println("Please enter a float number");
            }
        }
    }

    /**
	 * This method reads in a number with exception handling
	 * @return the read number
	 */
    public static Double nextDouble() {
		while (true) {
			try {
                Scanner sc = new Scanner(System.in);
                Double output = sc.nextDouble(); sc.nextLine();
                return output;
			} catch (Exception e) {
                System.out.println("Please enter a number");
			}
		}
	}
}