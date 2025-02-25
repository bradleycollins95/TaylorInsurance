import java.util.ArrayList;
import java.util.List;

/**
 * The {@code User} class represents a user in the system with a username, password, and a list of associated insurance policies.
 * It provides methods for managing policies, verifying login credentials, and checking the status of active policies.
 */
public class User {
    private String username;
    private String password;
    private List<Policy> policies;

    /**
     * Constructs a new {@code User} object with the specified username and password.
     * The user's list of policies is initially empty.
     *
     * @param username The username for the user.
     * @param password The password for the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.policies = new ArrayList<>();
    }

    /**
     * Verifies the login credentials by comparing the provided username and password with the user's stored values.
     *
     * @param inputUsername The username to verify.
     * @param inputPassword The password to verify.
     * @return {@code true} if the provided username and password match the stored values, {@code false} otherwise.
     */
    public boolean verifyLogin(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    /**
     * Adds a new {@code Policy} to the user's list of policies.
     *
     * @param policy The policy to add.
     */
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

    /**
     * Returns the list of {@code Policy} objects associated with the user.
     *
     * @return A list of the user's policies.
     */
    public List<Policy> getPolicies() {
        return policies;
    }

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks whether the user has an active home insurance policy.
     *
     * @return {@code true} if the user has at least one active {@code HomePolicy}, {@code false} otherwise.
     */
    public boolean hasActiveHomePolicy() {
        return policies.stream().anyMatch(policy -> policy instanceof HomePolicy && policy.isActive());
    }

    /**
     * Checks whether the user has an active auto insurance policy.
     *
     * @return {@code true} if the user has at least one active {@code AutoPolicy}, {@code false} otherwise.
     */
    public boolean hasActiveAutoPolicy() {
        return policies.stream().anyMatch(policy -> policy instanceof AutoPolicy && policy.isActive());
    }

    /**
     * Counts the number of active auto insurance policies the user has.
     *
     * @return The count of active {@code AutoPolicy} objects associated with the user.
     */
    public int getActiveAutoPolicyCount() {
        return (int) policies.stream().filter(policy -> policy instanceof AutoPolicy && policy.isActive()).count();
    }
}


