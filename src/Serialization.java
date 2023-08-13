import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class contains utility methods for serialisation
 */
public class Serialization {	
    
    /**
	 * This method deserializes object from the file 
	 * @param fileName the name of file containing the serialized object
	 * @return the deserialized object
	 */
	public static Object readSerializedObject(String fileName) {
		Object obj = null;
        FileInputStream file = null;
        ObjectInputStream in = null;
		
		try {
			file = new FileInputStream(fileName);
			in = new ObjectInputStream(file);
			
			obj = in.readObject();
			
			in.close();
			//file.close();
			
		} catch (EOFException e) {
            return null;
            
        } catch (IOException ex) {
			ex.printStackTrace();
	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return obj;
	}
    
	/**
	 * This method serializes the object to the file 
	 * @param fileName the name of the file to serialize the object to
	 * @param obj the object to be serialized
	 */
	public static void writeSerializedObject(String fileName, Object obj) {
        FileOutputStream file = null;
        ObjectOutputStream out = null;
		try {
			file = new FileOutputStream(fileName);
			out = new ObjectOutputStream(file);
			
			out.writeObject(obj);
			
			out.close();
			//file.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}