import java.util.Scanner;

/**
 * The {@code InsuranceConsoleApp} class represents a command-line insurance management application.
 * It provides users with options to log in, create an account, request quotes, start policies, and view existing policies.
 */
public class InsuranceConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static User loggedInUser = null;

    /**
     * The entry point of the application.
     * It continuously displays the login/signup menu and the main menu after authentication.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        while (true) {
            showLoginOrSignupMenu();
            showMainMenu();
        }
    }

    /**
     * Displays the login or signup menu.
     * Allows the user to log in, create an account, or exit the application.
     */
    private static void showLoginOrSignupMenu() {
        while (loggedInUser == null) {
            System.out.println("\n--- Welcome to Taylor Insurance ---");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> login();
                case 2 -> createAccount();
                case 3 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Handles user login.
     * Prompts the user for credentials and authenticates using {@link AuthManager}.
     * If authentication is successful, the user is logged in.
     */
    private static void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        loggedInUser = AuthManager.authenticate(username, password);
        if (loggedInUser != null) {
            System.out.println("\nLogin successful! Welcome, " + loggedInUser.getUsername() + "!\n");
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    /**
     * Handles account creation.
     * Prompts the user for a new username and password, then registers the user with {@link AuthManager}.
     * If registration is successful, the user is logged in.
     */
    private static void createAccount() {
        System.out.println("\n--- Create Account ---");
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();

        if (AuthManager.authenticate(username, "") != null) {
            System.out.println("Username already exists! Please choose a different username.");
            return;
        }

        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        boolean registered = AuthManager.registerUser(username, password);
        if (registered) {
            loggedInUser = new User(username, password);
            System.out.println("Account created successfully! You are now logged in.");
        } else {
            System.out.println("Account creation failed. Please try again.");
        }
    }

    /**
     * Displays the main menu for logged-in users.
     * Provides options to request a quote, start a new policy, view existing policies, log out, or exit.
     */
    private static void showMainMenu() {
        while (loggedInUser != null) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Request a Quote");
            System.out.println("2. Start a New Policy");
            System.out.println("3. View Current Policies");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> QuoteManager.requestQuote(scanner);
                case 2 -> PolicyManager.startNewPolicy(loggedInUser, scanner);
                case 3 -> PolicyManager.viewPolicies(loggedInUser, scanner);
                case 4 -> {
                    loggedInUser = null;
                    System.out.println("Logged out successfully.");
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}


