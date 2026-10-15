import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 1: The Canteen Billing Counter
 *
 * Task:
 * A college canteen serves three kinds of customers: students, staff, and guests.
 * Each kind of customer pays a different final amount for the same bill. For every bill,
 * calculate the final amount to be paid, display it, and then display the total amount collected.
 *
 * Input:
 * The first line contains an integer N, the number of bills. Each of the next N lines contains
 * a customer type and the bill amount:
 * - STUDENT Amount
 * - STAFF Amount
 * - GUEST Amount
 *
 * Output:
 * For each bill, display CustomerType: FinalAmount, formatted to two decimal places.
 * Finally, display Total: GrandTotal, also formatted to two decimal places.
 *
 * Business Rules:
 * - Students get a 10% discount.
 * - Staff get a 5% discount.
 * - Guests pay the full amount plus a ₹10 service charge.
 *
 * OOP Design:
 * - Abstract base class `Customer` establishes the contract `calculateFinalAmount()`.
 * - Subclasses `StudentCustomer`, `StaffCustomer`, and `GuestCustomer` implement their specific
 *   pricing rules through method overriding.
 * - Bills are stored in a polymorphic collection (`List<Customer>`), and processing occurs
 *   via dynamic method dispatch without any conditional type checking (`if-else` / `instanceof`).
 */
public class CanteenBillingCounter {

    // ==========================================================
    // Abstract Base Class
    // ==========================================================
    public static abstract class Customer {
        private final String customerType;
        private final double billAmount;

        public Customer(String customerType, double billAmount) {
            this.customerType = customerType;
            this.billAmount = billAmount;
        }

        public String getCustomerType() {
            return customerType;
        }

        public double getBillAmount() {
            return billAmount;
        }

        /**
         * Calculates the final amount payable by this customer based on type-specific pricing rules.
         *
         * @return final bill amount
         */
        public abstract double calculateFinalAmount();
    }

    // ==========================================================
    // Concrete Subclasses
    // ==========================================================

    /**
     * Student customer: receives a 10% discount on the bill amount.
     */
    public static class StudentCustomer extends Customer {
        private static final double DISCOUNT_RATE = 0.10;

        public StudentCustomer(double billAmount) {
            super("STUDENT", billAmount);
        }

        @Override
        public double calculateFinalAmount() {
            return getBillAmount() * (1.0 - DISCOUNT_RATE);
        }
    }

    /**
     * Staff customer: receives a 5% discount on the bill amount.
     */
    public static class StaffCustomer extends Customer {
        private static final double DISCOUNT_RATE = 0.05;

        public StaffCustomer(double billAmount) {
            super("STAFF", billAmount);
        }

        @Override
        public double calculateFinalAmount() {
            return getBillAmount() * (1.0 - DISCOUNT_RATE);
        }
    }

    /**
     * Guest customer: pays the full amount plus a flat ₹10 service charge.
     */
    public static class GuestCustomer extends Customer {
        private static final double SERVICE_CHARGE = 10.00;

        public GuestCustomer(double billAmount) {
            super("GUEST", billAmount);
        }

        @Override
        public double calculateFinalAmount() {
            return getBillAmount() + SERVICE_CHARGE;
        }
    }

    // ==========================================================
    // Factory Method for Polymorphic Object Creation
    // ==========================================================
    public static Customer createCustomer(String type, double amount) {
        if ("STUDENT".equalsIgnoreCase(type)) {
            return new StudentCustomer(amount);
        } else if ("STAFF".equalsIgnoreCase(type)) {
            return new StaffCustomer(amount);
        } else if ("GUEST".equalsIgnoreCase(type)) {
            return new GuestCustomer(amount);
        } else {
            throw new IllegalArgumentException("Unknown customer type: " + type);
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
        List<Customer> bills = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();
            bills.add(createCustomer(type, amount));
        }

        double grandTotal = 0.0;
        // Polymorphic processing: no if-else or explicit type checking used
        for (Customer customer : bills) {
            double finalAmount = customer.calculateFinalAmount();
            System.out.printf(Locale.US, "%s: %.2f%n", customer.getCustomerType(), finalAmount);
            grandTotal += finalAmount;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
        scanner.close();
    }
}
