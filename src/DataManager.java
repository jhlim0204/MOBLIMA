/**
 * This class manages the data object
 */
public class DataManager {

	/**
	 * The filename of the database
	 */
	private static final String FILENAME = "src/moblima.dat";

    /**
	 * The data store
	 */
	private static DataStore dataStore;

    /**
	 * Creates a {@DataManager} object
	 */
	private DataManager() {}

	/**
	 * This method returns the data store
	 * @return the data store
	 */
	public static DataStore getDataStore() {
		return dataStore;
	}

	/**
	 * This method initialises a data store
	 */
	public static void initialise() {
		dataStore = new DataStore();
	}

	/**
	 * This method tries to load the data store from the file. If fails, it initialises a data store
	 */
	public static void load() {
		Object obj = Serialization.readSerializedObject(FILENAME);
		
		if (obj == null || !(obj instanceof DataStore))
			initialise();
		else
			dataStore = (DataStore) obj;
	}

	/**
	 * This method update the data store
	 */
	public static void update() {
		Serialization.writeSerializedObject(FILENAME, dataStore);
	}
}