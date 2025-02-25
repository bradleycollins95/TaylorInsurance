/**
 * Represents a home insurance policy.
 * Extends the {@link Policy} class and calculates the premium based on home age, dwelling type,
 * heating type, location, home value, and liability limit.
 */
public class HomePolicy extends Policy {
    private final int homeAge;
    private final String dwellingType;
    private final String heatingType;
    private final String location;
    private final float homeValue;
    private final float liabilityLimit;

    /**
     * Constructs a HomePolicy with the specified attributes.
     *
     * @param homeAge       The age of the home in years.
     * @param dwellingType  The type of dwelling (e.g., house, condo, apartment).
     * @param heatingType   The type of heating used in the home (e.g., electric, oil, wood).
     * @param location      The location of the home (e.g., urban, rural).
     * @param homeValue     The estimated value of the home.
     * @param liabilityLimit The liability coverage limit.
     */
    public HomePolicy(int homeAge, String dwellingType, String heatingType, String location, float homeValue, float liabilityLimit) {
        super("Home", 500);
        this.homeAge = homeAge;
        this.dwellingType = dwellingType;
        this.heatingType = heatingType;
        this.location = location;
        this.homeValue = homeValue;
        this.liabilityLimit = liabilityLimit;
    }

    /**
     * Gets the age of the home.
     *
     * @return The home age in years.
     */
    public int getHomeAge() {
        return homeAge;
    }

    /**
     * Gets the type of dwelling.
     *
     * @return The dwelling type.
     */
    public String getDwellingType() {
        return dwellingType;
    }

    /**
     * Gets the type of heating used in the home.
     *
     * @return The heating type.
     */
    public String getHeatingType() {
        return heatingType;
    }

    /**
     * Gets the location of the home.
     *
     * @return The home location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the estimated value of the home.
     *
     * @return The home value.
     */
    public float getHomeValue() {
        return homeValue;
    }

    /**
     * Gets the liability coverage limit.
     *
     * @return The liability limit.
     */
    public float getLiabilityLimit() {
        return liabilityLimit;
    }

    /**
     * Calculates the total premium for the home policy based on risk factors.
     * Factors include home value, liability coverage, home age, heating type, and location.
     * If the user has an active auto policy, a discount is applied.
     *
     * @param user The user who owns the policy.
     */
    @Override
    public void calculatePremium(User user) {
        float premium = basePremium;

        if (homeValue > 250000) {
            premium += (homeValue - 250000) * 0.002f;
        }

        premium *= (liabilityLimit == 2000000) ? 1.25 : 1.0;

        premium *= (homeAge > 50) ? 1.5 : (homeAge > 25) ? 1.25 : 1.0;

        premium *= heatingType.equalsIgnoreCase("oil") ? 2.0 :
                heatingType.equalsIgnoreCase("wood") ? 1.25 : 1.0;

        premium *= location.equalsIgnoreCase("rural") ? 1.15 : 1.0;

        // Apply discount if the user has an active auto policy
        if (user != null && user.hasActiveAutoPolicy()) {
            premium *= 0.9;
        }

        // 15% tax
        totalPremium = premium * 1.15f;
    }
}





