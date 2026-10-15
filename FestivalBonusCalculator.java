import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 4: The Festival Bonus Calculator
 *
 * Task:
 * A company pays a festival bonus to its employees. Full-time employees, part-time employees,
 * and interns each receive the bonus in a different way. For every employee, calculate the bonus,
 * display it with the employee's name, and then display the total bonus paid.
 *
 * Input:
 * The first line contains an integer N, the number of employees. Each of the next N lines contains
 * EmployeeType Name MonthlySalary:
 * - FULLTIME Name MonthlySalary
 * - PARTTIME Name MonthlySalary
 * - INTERN Name MonthlySalary
 *
 * Output:
 * For each employee, display Name: Bonus, formatted to two decimal places.
 * Finally, display Total Bonus: GrandTotal, also formatted to two decimal places.
 *
 * Business Rules:
 * - Full-time employees get 10% of their monthly salary.
 * - Part-time employees get 5% of their monthly salary.
 * - Interns get a fixed bonus of ₹2,000, whatever their salary.
 *
 * Constraints:
 * - 1 <= N <= 1000
 * - Name is a single word
 * - 1000 <= MonthlySalary <= 500000
 *
 * OOP Design:
 * - Base class `Employee` encapsulates shared fields (`name`, `monthlySalary`) and declares
 *   the abstract contract `calculateBonus()`.
 * - Subclasses `FullTimeEmployee`, `PartTimeEmployee`, and `InternEmployee` override `calculateBonus()`.
 * - Processing loop iterates over `List<Employee>` without explicit type checking, invoking
 *   `calculateBonus()` polymorphically.
 */
public class FestivalBonusCalculator {

    // ==========================================================
    // Abstract Base Class
    // ==========================================================
    public static abstract class Employee {
        private final String employeeType;
        private final String name;
        private final double monthlySalary;

        public Employee(String employeeType, String name, double monthlySalary) {
            this.employeeType = employeeType;
            this.name = name;
            this.monthlySalary = monthlySalary;
        }

        public String getEmployeeType() {
            return employeeType;
        }

        public String getName() {
            return name;
        }

        public double getMonthlySalary() {
            return monthlySalary;
        }

        /**
         * Calculates the festival bonus for this employee based on their employment type.
         *
         * @return bonus amount
         */
        public abstract double calculateBonus();
    }

    // ==========================================================
    // Concrete Subclasses
    // ==========================================================

    /**
     * Full-time employee: 10% of monthly salary.
     */
    public static class FullTimeEmployee extends Employee {
        private static final double BONUS_RATE = 0.10;

        public FullTimeEmployee(String name, double monthlySalary) {
            super("FULLTIME", name, monthlySalary);
        }

        @Override
        public double calculateBonus() {
            return getMonthlySalary() * BONUS_RATE;
        }
    }

    /**
     * Part-time employee: 5% of monthly salary.
     */
    public static class PartTimeEmployee extends Employee {
        private static final double BONUS_RATE = 0.05;

        public PartTimeEmployee(String name, double monthlySalary) {
            super("PARTTIME", name, monthlySalary);
        }

        @Override
        public double calculateBonus() {
            return getMonthlySalary() * BONUS_RATE;
        }
    }

    /**
     * Intern: Flat ₹2,000 bonus regardless of salary.
     */
    public static class InternEmployee extends Employee {
        private static final double FLAT_BONUS = 2000.00;

        public InternEmployee(String name, double monthlySalary) {
            super("INTERN", name, monthlySalary);
        }

        @Override
        public double calculateBonus() {
            return FLAT_BONUS;
        }
    }

    // ==========================================================
    // Factory Method for Polymorphic Object Creation
    // ==========================================================
    public static Employee createEmployee(String type, String name, double salary) {
        if ("FULLTIME".equalsIgnoreCase(type)) {
            return new FullTimeEmployee(name, salary);
        } else if ("PARTTIME".equalsIgnoreCase(type)) {
            return new PartTimeEmployee(name, salary);
        } else if ("INTERN".equalsIgnoreCase(type)) {
            return new InternEmployee(name, salary);
        } else {
            throw new IllegalArgumentException("Unknown employee type: " + type);
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
        List<Employee> employees = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            double salary = scanner.nextDouble();
            employees.add(createEmployee(type, name, salary));
        }

        double totalBonus = 0.0;
        // Polymorphic processing: no if-else or instanceof in computation
        for (Employee emp : employees) {
            double bonus = emp.calculateBonus();
            System.out.printf(Locale.US, "%s: %.2f%n", emp.getName(), bonus);
            totalBonus += bonus;
        }

        System.out.printf(Locale.US, "Total Bonus: %.2f%n", totalBonus);
        scanner.close();
    }
}
