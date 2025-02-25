import java.util.HashMap;
import java.util.Map;

/**
 * Manages user authentication and registration.
 * This class provides methods to authenticate existing users
 * and register new users using a simple in-memory user storage.
 */
public class AuthManager {
    private static Map<String, User> users = new HashMap<>();

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username The username of the user attempting to log in.
     * @param password The password provided for authentication.
     * @return The authenticated {@link User} object if credentials are valid, otherwise {@code null}.
     */
    public static User authenticate(String username, String password) {
        User user = users.get(username);
        return (user != null && user.verifyLogin(username, password)) ? user : null;
    }

    /**
     * Registers a new user with the given username and password.
     *
     * @param username The desired username for the new user.
     * @param password The password associated with the new user account.
     * @return {@code true} if registration is successful, {@code false} if the username already exists.
     */
    public static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        users.put(username, new User(username, password));
        return true;
    }
}

