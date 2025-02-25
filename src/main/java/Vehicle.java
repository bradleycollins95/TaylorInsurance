import java.time.Year;

/**
 * The {@code Vehicle} class represents a vehicle with details such as make, model, and year.
 * It provides methods to access these details and calculate the vehicle's age.
 */
public class Vehicle {
    private final String make;
    private final String model;
    private final int year;

    /**
     * Constructs a new {@code Vehicle} object with the specified make, model, and year.
     *
     * @param make  The make (brand) of the vehicle.
     * @param model The model of the vehicle.
     * @param year  The year the vehicle was manufactured.
     */
    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    /**
     * Returns the make (brand) of the vehicle.
     *
     * @return The make of the vehicle.
     */
    public String getMake() {
        return make;
    }

    /**
     * Returns the model of the vehicle.
     *
     * @return The model of the vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the year the vehicle was manufactured.
     *
     * @return The year the vehicle was manufactured.
     */
    public int getYear() {
        return year;
    }

    /**
     * Calculates and returns the age of the vehicle based on the current year.
     *
     * @return The age of the vehicle in years.
     */
    public int getAge() {
        return Year.now().getValue() - year;
    }
}




