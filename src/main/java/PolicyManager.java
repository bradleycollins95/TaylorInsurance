import java.util.Scanner;

/**
 * The PolicyManager class handles the creation and management of insurance policies.
 * It allows users to start new policies, calculate premiums, and view or cancel existing policies.
 */
public class PolicyManager {

    /**
     * Starts a new insurance policy for a user.
     * Prompts the user to choose between Home or Auto insurance and collects the necessary information.
     *
     * @param user The user creating the policy.
     * @param scanner The scanner used to read input from the user.
     */
    public static void startNewPolicy(User user, Scanner scanner) {
        System.out.println("\n--- Start a New Policy ---");
        System.out.println("1. Home Insurance");
        System.out.println("2. Auto Insurance");
        System.out.println("3. Back");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter home age (in years): ");
            int homeAge = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter dwelling type(townhouse, apartment, etc): ");
            String dwellingType = scanner.nextLine();

            System.out.print("Enter heating type (oil, wood, or other) : ");
            String heatingType = scanner.nextLine();

            System.out.print("Enter location (urban/rural): ");
            String location = scanner.nextLine();

            System.out.print("Enter home value: ");
            float homeValue = scanner.nextFloat();
            scanner.nextLine();

            System.out.print("Enter liability limit ($1M or $2M): ");
            String liabilityInput = scanner.nextLine().trim();

            // set liability limit based on user input
            float liabilityLimit = liabilityInput.equalsIgnoreCase("$2M") || liabilityInput.equals("2M") ? 2_000_000f : 1_000_000f;

            // create HomePolicy and add it to the user's policies
            HomePolicy policy = new HomePolicy(homeAge, dwellingType, heatingType, location, homeValue, liabilityLimit);
            user.addPolicy(policy);
            policy.calculatePremium(user);
            System.out.println("Home policy created successfully!");
        }
        else if (choice == 2) {
            System.out.println("Enter model year of your car: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter vehicle make: ");
            String make = scanner.next();

            System.out.println("Enter vehicle model: ");
            String model = scanner.next();

            System.out.println("Enter driver's age: ");
            int driverAge = scanner.nextInt();
            scanner.nextLine();

            System.out.println("How many accidents in the last 5 years: ");
            int accidentCount = scanner.nextInt();
            scanner.nextLine();

            // create Vehicle and AutoPolicy instances, then add it to the user's policies
            Vehicle vehicle = new Vehicle(make, model, year);
            AutoPolicy autoPolicy = new AutoPolicy(driverAge, accidentCount, vehicle);
            user.addPolicy(autoPolicy);
            autoPolicy.calculatePremium(user);
            System.out.println("Auto policy created successfully!");
        } else if (choice == 3) {
            System.out.println("Returning to the main menu.");
        }
        else {
            System.out.println("Invalid choice, please try again.");
        }
    }

    /**
     * Calculates a home insurance premium based on user input.
     *
     * @param scanner The scanner used to read input from the user.
     * @return The calculated home insurance premium.
     */
    public static float calculateHomePremium(Scanner scanner) {
        System.out.println("\n--- Home Insurance Quote ---");
        System.out.print("Enter home value: ");
        float homeValue = scanner.nextFloat();
        System.out.print("Enter home age (in years): ");
        int homeAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter heating type (oil/wood/other): ");
        String heatingType = scanner.nextLine();
        System.out.print("Enter location (urban/rural): ");
        String location = scanner.nextLine();
        System.out.print("Enter liability limit (1000000 or 2000000): ");
        float liabilityLimit = scanner.nextFloat();

        // creates a temporary HomePolicy object to calculate the quote
        HomePolicy tempPolicy = new HomePolicy(homeAge, "standalone", heatingType, location, homeValue, liabilityLimit);
        tempPolicy.calculatePremium(null);

        return tempPolicy.getTotalPremium();
    }

    /**
     * Calculates an auto insurance premium based on user input.
     *
     * @param scanner The scanner used to read input from the user.
     * @return The calculated auto insurance premium.
     */
    public static float calculateAutoPremium(Scanner scanner) {
        System.out.println("\n--- Auto Insurance Quote ---");
        System.out.print("Enter driver age: ");
        int driverAge = scanner.nextInt();
        System.out.print("Enter number of accidents in last 5 years: ");
        int accidentCount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter vehicle year: ");
        int year = scanner.nextInt();

        // creates a temporary AutoPolicy object to calculate the quote
        AutoPolicy tempPolicy = new AutoPolicy(driverAge, accidentCount, new Vehicle(make, model, year));
        tempPolicy.calculatePremium(null);

        return tempPolicy.getTotalPremium();
    }

    /**
     * Displays a list of the user's current policies and allows them to cancel one.
     *
     * @param user The user whose policies will be displayed.
     * @param scanner The scanner used to read input from the user.
     */
    public static void viewPolicies(User user, Scanner scanner) {
        System.out.println("\n--- Your Policies ---");
        if (user.getPolicies().isEmpty()) {
            System.out.println("You have no active policies.");
            return;
        }

        // display each policy's details
        for (int i = 0; i < user.getPolicies().size(); i++) {
            Policy policy = user.getPolicies().get(i);

            System.out.println("\n" + (i + 1) + ". " + policy.getPolicyType() + " Policy");
            System.out.println("   - Premium: $" + String.format("%.2f", policy.getTotalPremium()));
            System.out.println("   - Start Date: " + policy.startDate);
            System.out.println("   - End Date: " + policy.endDate);
            System.out.println("   - Status: " + (policy.isActive() ? "Active" : "Canceled"));
            System.out.println("------------------------------------------------------------------------------");

            if (policy instanceof AutoPolicy autoPolicy) {
                Vehicle vehicle = autoPolicy.getVehicle();
                System.out.println("   - Vehicle: " + vehicle.getMake() + " " + vehicle.getModel() + " (" + vehicle.getYear() + ")");
                System.out.println("   - Driver Age: " + autoPolicy.getDriverAge());
                System.out.println("   - Accidents in Last 5 Years: " + autoPolicy.getAccidentCount());
            } else if (policy instanceof HomePolicy homePolicy) {
                System.out.println("   - Home Value: $" + homePolicy.getHomeValue());
                System.out.println("   - Home Age: " + homePolicy.getHomeAge() + " years");
                System.out.println("   - Location: " + homePolicy.getLocation());
                System.out.println("   - Heating Type: " + homePolicy.getHeatingType());
                System.out.println("   - Liability Limit: $" + homePolicy.getLiabilityLimit());
            }
        }

        System.out.println("\n" + (user.getPolicies().size() + 1) + ". Back");
        System.out.print("Select a policy to cancel, or choose 'Back': ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= user.getPolicies().size()) {
            Policy policyToCancel = user.getPolicies().get(choice - 1);
            policyToCancel.cancelPolicy();

            user.getPolicies().remove(choice - 1);
            System.out.println("Policy removed successfully.");
        }
    }
}





