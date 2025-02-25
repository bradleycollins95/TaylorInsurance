import java.util.Scanner;

/**
 * The {@code QuoteManager} class is responsible for handling the process of requesting insurance quotes.
 * It provides the user with options to request a quote for either home insurance or auto insurance.
 */
public class QuoteManager {

    /**
     * Displays the quote request options and handles the user's selection.
     * The user can choose to request a quote for home insurance or auto insurance.
     * Based on the user's choice, the corresponding premium is calculated and displayed.
     *
     * @param scanner The {@code Scanner} object used to take input from the user.
     */
    public static void requestQuote(Scanner scanner) {
        System.out.println("\n--- Request a Quote ---");
        System.out.println("1. Home Insurance");
        System.out.println("2. Auto Insurance");
        System.out.println("3. Back");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) { // request home insurance quote and display the premium
            float premium = PolicyManager.calculateHomePremium(scanner);
            System.out.println("Estimated Home Insurance Premium: $" + premium);
        } else if (choice == 2) { // request auto insurance quote and display the premium
            float premium = PolicyManager.calculateAutoPremium(scanner);
            System.out.println("Estimated Auto Insurance Premium: $" + premium);
        }
    }
}




