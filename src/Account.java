import java.io.Serializable;

/**
 * This is the interface for all the accounts
 */
public interface Account{   	
    /**
	 * This method returns true if the login using the password is successful
     * @param user the username of the account
     * @param password the password to test against
	 * @return true if the login is successful, false if not
	 */
    public boolean login(String user, String password);
}