import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 5: The Streaming Plan Renewal Reminder
 *
 * Task:
 * A video streaming service offers three subscription plans: Basic, Standard, and Premium.
 * Each plan stays valid for a different number of days from its start date. For every subscriber,
 * calculate the date on which the plan must be renewed and display it.
 *
 * Input:
 * The first line contains an integer N, the number of subscribers. Each of the next N lines contains
 * PlanType Name StartDate, where StartDate is in the format YYYY-MM-DD:
 * - BASIC Name StartDate
 * - STANDARD Name StartDate
 * - PREMIUM Name StartDate
 *
 * Output:
 * For each subscriber, display Name: RenewalDate, where RenewalDate is formatted as YYYY-MM-DD.
 *
 * Business Rules:
 * - Basic plan: valid for 30 days.
 * - Standard plan: valid for 90 days.
 * - Premium plan: valid for 365 days.
 * - The renewal date is the start date plus the plan's validity in days.
 *
 * Constraints:
 * - 1 <= N <= 100
 * - Name is a single word
 * - StartDate is a valid date (YYYY-MM-DD)
 *
 * OOP Design:
 * - Abstract base class `Subscription` encapsulates common subscriber attributes (`planType`,
 *   `name`, `startDate`) and declares the abstract method `getValidityDays()`.
 * - Template method `calculateRenewalDate()` leverages `java.time.LocalDate.plusDays(getValidityDays())`,
 *   relying on dynamic method dispatch to bind to the concrete plan's validity period.
 * - Subclasses `BasicSubscription`, `StandardSubscription`, and `PremiumSubscription` encapsulate
 *   plan-specific validity periods (30, 90, 365 days) cleanly.
 */
public class StreamingPlanRenewalReminder {

    // ==========================================================
    // Abstract Base Class
    // ==========================================================
    public static abstract class Subscription {
        private final String planType;
        private final String name;
        private final LocalDate startDate;

        public Subscription(String planType, String name, LocalDate startDate) {
            this.planType = planType;
            this.name = name;
            this.startDate = startDate;
        }

        public String getPlanType() {
            return planType;
        }

        public String getName() {
            return name;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        /**
         * Returns the number of validity days for this specific subscription plan.
         *
         * @return validity in days
         */
        public abstract int getValidityDays();

        /**
         * Calculates the renewal date by adding the plan's validity period to the start date.
         * Correctly handles leap years and year-end rollovers via java.time.LocalDate.
         *
         * @return renewal date (YYYY-MM-DD)
         */
        public LocalDate calculateRenewalDate() {
            return startDate.plusDays(getValidityDays());
        }
    }

    // ==========================================================
    // Concrete Subclasses
    // ==========================================================

    /**
     * Basic Plan: valid for 30 days.
     */
    public static class BasicSubscription extends Subscription {
        private static final int VALIDITY_DAYS = 30;

        public BasicSubscription(String name, LocalDate startDate) {
            super("BASIC", name, startDate);
        }

        @Override
        public int getValidityDays() {
            return VALIDITY_DAYS;
        }
    }

    /**
     * Standard Plan: valid for 90 days.
     */
    public static class StandardSubscription extends Subscription {
        private static final int VALIDITY_DAYS = 90;

        public StandardSubscription(String name, LocalDate startDate) {
            super("STANDARD", name, startDate);
        }

        @Override
        public int getValidityDays() {
            return VALIDITY_DAYS;
        }
    }

    /**
     * Premium Plan: valid for 365 days.
     */
    public static class PremiumSubscription extends Subscription {
        private static final int VALIDITY_DAYS = 365;

        public PremiumSubscription(String name, LocalDate startDate) {
            super("PREMIUM", name, startDate);
        }

        @Override
        public int getValidityDays() {
            return VALIDITY_DAYS;
        }
    }

    // ==========================================================
    // Factory Method for Polymorphic Object Creation
    // ==========================================================
    public static Subscription createSubscription(String planType, String name, LocalDate startDate) {
        if ("BASIC".equalsIgnoreCase(planType)) {
            return new BasicSubscription(name, startDate);
        } else if ("STANDARD".equalsIgnoreCase(planType)) {
            return new StandardSubscription(name, startDate);
        } else if ("PREMIUM".equalsIgnoreCase(planType)) {
            return new PremiumSubscription(name, startDate);
        } else {
            throw new IllegalArgumentException("Unknown plan type: " + planType);
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
        List<Subscription> subscriptions = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String planType = scanner.next();
            String name = scanner.next();
            String dateStr = scanner.next();
            LocalDate startDate = LocalDate.parse(dateStr);
            subscriptions.add(createSubscription(planType, name, startDate));
        }

        // Polymorphic processing: calculateRenewalDate() dynamically evaluates validity
        for (Subscription sub : subscriptions) {
            LocalDate renewalDate = sub.calculateRenewalDate();
            System.out.printf("%s: %s%n", sub.getName(), renewalDate.toString());
        }

        scanner.close();
    }
}
