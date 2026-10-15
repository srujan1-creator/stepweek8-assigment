import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 2: The Campus Parking Charge Calculator
 *
 * Task:
 * The campus parking lot allows bikes, cars, and trucks. Each vehicle type is charged
 * differently based on the number of hours it was parked. For every parked vehicle,
 * calculate the parking charge, display it, and then display the total charge collected.
 *
 * Input:
 * The first line contains an integer N, the number of parked vehicles. Each of the next N lines
 * contains a vehicle type and the number of hours parked:
 * - BIKE Hours
 * - CAR Hours
 * - TRUCK Hours
 *
 * Output:
 * For each vehicle, display VehicleType: Charge, formatted to two decimal places.
 * Finally, display Total: GrandTotal, also formatted to two decimal places.
 *
 * Business Rules:
 * - Bike: ₹10 per hour.
 * - Car: ₹30 for the first hour, plus ₹20 for each additional hour.
 * - Truck: ₹50 per hour, with a minimum charge of ₹100.
 *
 * Constraints:
 * - 1 <= N <= 500
 * - Hours is a whole number, 1 <= Hours <= 24
 *
 * OOP Design:
 * - Abstract base class `Vehicle` defines common state (type, hours) and the abstract
 *   contract `calculateCharge()`.
 * - Derived classes `Bike`, `Car`, and `Truck` override `calculateCharge()` to enforce
 *   their distinct business formulas.
 * - Vehicles are stored in a polymorphic `List<Vehicle>` and processed via dynamic method
 *   dispatch, eliminating explicit type inspection.
 */
public class CampusParkingChargeCalculator {

    // ==========================================================
    // Abstract Base Class
    // ==========================================================
    public static abstract class Vehicle {
        private final String vehicleType;
        private final int hours;

        public Vehicle(String vehicleType, int hours) {
            this.vehicleType = vehicleType;
            this.hours = hours;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public int getHours() {
            return hours;
        }

        /**
         * Computes the parking fee based on the vehicle-specific pricing rules.
         *
         * @return parking charge
         */
        public abstract double calculateCharge();
    }

    // ==========================================================
    // Concrete Subclasses
    // ==========================================================

    /**
     * Bike: Flat ₹10 per hour.
     */
    public static class Bike extends Vehicle {
        private static final double HOURLY_RATE = 10.00;

        public Bike(int hours) {
            super("BIKE", hours);
        }

        @Override
        public double calculateCharge() {
            return getHours() * HOURLY_RATE;
        }
    }

    /**
     * Car: ₹30 for the first hour, plus ₹20 for each additional hour.
     */
    public static class Car extends Vehicle {
        private static final double FIRST_HOUR_RATE = 30.00;
        private static final double ADDITIONAL_HOUR_RATE = 20.00;

        public Car(int hours) {
            super("CAR", hours);
        }

        @Override
        public double calculateCharge() {
            if (getHours() <= 1) {
                return FIRST_HOUR_RATE;
            }
            return FIRST_HOUR_RATE + (getHours() - 1) * ADDITIONAL_HOUR_RATE;
        }
    }

    /**
     * Truck: ₹50 per hour, with a minimum charge of ₹100.
     */
    public static class Truck extends Vehicle {
        private static final double HOURLY_RATE = 50.00;
        private static final double MINIMUM_CHARGE = 100.00;

        public Truck(int hours) {
            super("TRUCK", hours);
        }

        @Override
        public double calculateCharge() {
            double rawCharge = getHours() * HOURLY_RATE;
            return Math.max(MINIMUM_CHARGE, rawCharge);
        }
    }

    // ==========================================================
    // Factory Method for Polymorphic Object Creation
    // ==========================================================
    public static Vehicle createVehicle(String type, int hours) {
        if ("BIKE".equalsIgnoreCase(type)) {
            return new Bike(hours);
        } else if ("CAR".equalsIgnoreCase(type)) {
            return new Car(hours);
        } else if ("TRUCK".equalsIgnoreCase(type)) {
            return new Truck(hours);
        } else {
            throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }

    // ==========================================================
    // Main Processing Flow
    // ==========================================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        List<Vehicle> vehicles = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int hours = scanner.nextInt();
            vehicles.add(createVehicle(type, hours));
        }

        double grandTotal = 0.0;
        // Polymorphic processing: dynamic dispatch executes appropriate calculateCharge()
        for (Vehicle vehicle : vehicles) {
            double charge = vehicle.calculateCharge();
            System.out.printf(Locale.US, "%s: %.2f%n", vehicle.getVehicleType(), charge);
            grandTotal += charge;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
        scanner.close();
    }
}
