import java.time.LocalDate;

/**
 * The {@code Policy} class represents a generic insurance policy.
 * It defines common attributes and behaviors for different types of policies,
 * including premium calculation, renewal, and cancellation.
 * <p>
 * This class is abstract and should be extended by specific policy types (e.g., AutoPolicy, HomePolicy).
 */
public abstract class Policy {
    protected String policyType;
    protected float basePremium;
    protected float totalPremium;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected boolean isActive;

    /**
     * Constructs a new policy with the specified type and base premium.
     * The policy is set to active by default and has a duration of one year.
     *
     * @param policyType  The type of policy (e.g., "Auto", "Home").
     * @param basePremium The base premium amount before adjustments.
     */
    public Policy(String policyType, float basePremium) {
        this.policyType = policyType;
        this.basePremium = basePremium;
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusYears(1);
        this.isActive = true;
    }

    /**
     * Abstract method to calculate the total premium based on user details and policy factors.
     * This method must be implemented by subclasses.
     *
     * @param user The user associated with the policy, used for discount calculations.
     */
    public abstract void calculatePremium(User user);

    /**
     * Renews the policy by updating the start and end dates.
     * The policy remains active for another year.
     */
    public void renewPolicy() {
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusYears(1);
        System.out.println(policyType + " policy renewed successfully.");
    }

    /**
     * Cancels the policy, setting it to inactive.
     * A message is displayed informing the user about billing implications.
     */
    public void cancelPolicy() {
        this.isActive = false;
        System.out.println(policyType + " policy canceled. You will still be billed for the rest of the month.");
    }

    /**
     * Checks if the policy is currently active.
     *
     * @return {@code true} if the policy is active, {@code false} otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Gets the total premium amount for the policy after all calculations.
     *
     * @return The total premium amount.
     */
    public float getTotalPremium() {
        return totalPremium;
    }

    /**
     * Gets the type of policy.
     *
     * @return The policy type as a string.
     */
    public String getPolicyType() {
        return policyType;
    }
}

