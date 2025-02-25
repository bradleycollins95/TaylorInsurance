/**
 * Represents an auto insurance policy.
 * Extends the {@link Policy} class and calculates the premium based on driver age, accident history, and vehicle age.
 */
public class AutoPolicy extends Policy {
    private final int driverAge;
    private final int accidentCount;
    private final Vehicle vehicle;

    /**
     * Constructs an AutoPolicy with the specified driver age, accident count, and vehicle.
     *
     * @param driverAge    The age of the driver.
     * @param accidentCount The number of accidents in the last 5 years.
     * @param vehicle      The vehicle associated with the policy.
     */
    public AutoPolicy(int driverAge, int accidentCount, Vehicle vehicle) {
        super("Auto", 750);
        this.driverAge = driverAge;
        this.accidentCount = accidentCount;
        this.vehicle = vehicle;
    }

    /**
     * Calculates the total premium for the auto policy based on multiple risk factors.
     * Factors include driver age, accident history, and vehicle age.
     * If the user has an active home policy, a discount is applied.
     *
     * @param user The user who owns the policy.
     */
    @Override
    public void calculatePremium(User user) {
        float premium = basePremium;

        // Adjust premium based on driver age
        premium *= driverAge < 25 ? 2.0 : 1.0;

        // Adjust premium based on accident count
        premium *= (accidentCount > 2) ? 2.5 : (accidentCount == 1) ? 1.25 : 1.0;

        // Adjust premium based on vehicle age
        premium *= vehicle.getAge() > 10 ? 2.0 : vehicle.getAge() > 5 ? 1.5 : 1.0;

        // Apply discount if the user has an active home policy
        if (user != null && user.hasActiveHomePolicy()) {
            premium *= 0.9;
        }

        // Apply a 15% tax
        totalPremium = premium * 1.15f;
    }

    /**
     * Gets the vehicle associated with this policy.
     *
     * @return The insured vehicle.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Gets the driver's age.
     *
     * @return The age of the driver.
     */
    public int getDriverAge() {
        return driverAge;
    }

    /**
     * Gets the number of accidents in the last 5 years.
     *
     * @return The number of recorded accidents.
     */
    public int getAccidentCount() {
        return accidentCount;
    }
}








