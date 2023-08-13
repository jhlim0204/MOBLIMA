/**
 * This class is the main class of the application.
 */
public class MOBLIMA {	
    /**
	 * Launches the application
	 * @param args unused
	 */
    public static void main(String[] args) {
        System.out.println("Hello");
	    DataManager.load();

        Menu testing = new Menu(DataManager.getDataStore());
        testing.start();

        DataManager.update();
        System.out.println("Thank you for using MOBLIMA. Changes have been saved successfully");
}
}