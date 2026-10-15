import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 3: The Hostel Electricity Bill
 *
 * Task:
 * A hostel has three kinds of rooms: single rooms, shared rooms, and AC rooms.
 * Each room type calculates its monthly electricity bill differently. For shared rooms,
 * the bill is split equally among the occupants, and the amount shown is what each occupant pays.
 * For every room, calculate the bill, display it, and then display the total of all bills shown.
 *
 * Input:
 * The first line contains an integer N, the number of rooms. Each of the next N lines contains
 * RoomType Units [AdditionalParam]:
 * - SINGLE Units
 * - SHARED Units Occupants
 * - AC Units
 *
 * Output:
 * For each room, display RoomType: BillAmount, formatted to two decimal places.
 * Finally, display Total: GrandTotal, also formatted to two decimal places.
 *
 * Business Rules:
 * - Single room: ₹8 per unit.
 * - Shared room: ₹6 per unit, divided equally by the number of occupants.
 * - AC room: ₹10 per unit, plus a fixed charge of ₹200.
 *
 * Constraints:
 * - 1 <= N <= 200
 * - Units is a positive whole number
 * - 2 <= Occupants <= 4
 *
 * OOP Design:
 * - Abstract base class `Room` defines shared attributes (roomType, units) and the abstract
 *   method `calculateBill()`.
 * - `SharedRoom` encapsulates the additional `occupants` property internally, shielding the
 *   polymorphic client from implementation differences.
 * - Processing loop treats all room instances uniformly via dynamic method dispatch without
 *   instanceof or type checks.
 */
public class HostelElectricityBillCalculator {

    // ==========================================================
    // Abstract Base Class
    // ==========================================================
    public static abstract class Room {
        private final String roomType;
        private final int units;

        public Room(String roomType, int units) {
            this.roomType = roomType;
            this.units = units;
        }

        public String getRoomType() {
            return roomType;
        }

        public int getUnits() {
            return units;
        }

        /**
         * Calculates the bill amount for this room according to its specific pricing formula.
         *
         * @return monthly electricity bill
         */
        public abstract double calculateBill();
    }

    // ==========================================================
    // Concrete Subclasses
    // ==========================================================

    /**
     * Single Room: ₹8 per unit.
     */
    public static class SingleRoom extends Room {
        private static final double RATE_PER_UNIT = 8.00;

        public SingleRoom(int units) {
            super("SINGLE", units);
        }

        @Override
        public double calculateBill() {
            return getUnits() * RATE_PER_UNIT;
        }
    }

    /**
     * Shared Room: ₹6 per unit, divided equally among occupants.
     * Encapsulates `occupants` so client code treats it like any other Room.
     */
    public static class SharedRoom extends Room {
        private static final double RATE_PER_UNIT = 6.00;
        private final int occupants;

        public SharedRoom(int units, int occupants) {
            super("SHARED", units);
            this.occupants = occupants;
        }

        public int getOccupants() {
            return occupants;
        }

        @Override
        public double calculateBill() {
            return (getUnits() * RATE_PER_UNIT) / occupants;
        }
    }

    /**
     * AC Room: ₹10 per unit plus a ₹200 fixed fee.
     */
    public static class AcRoom extends Room {
        private static final double RATE_PER_UNIT = 10.00;
        private static final double FIXED_CHARGE = 200.00;

        public AcRoom(int units) {
            super("AC", units);
        }

        @Override
        public double calculateBill() {
            return (getUnits() * RATE_PER_UNIT) + FIXED_CHARGE;
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
        List<Room> rooms = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int units = scanner.nextInt();

            if ("SHARED".equalsIgnoreCase(type)) {
                int occupants = scanner.nextInt();
                rooms.add(new SharedRoom(units, occupants));
            } else if ("SINGLE".equalsIgnoreCase(type)) {
                rooms.add(new SingleRoom(units));
            } else if ("AC".equalsIgnoreCase(type)) {
                rooms.add(new AcRoom(units));
            } else {
                throw new IllegalArgumentException("Unknown room type: " + type);
            }
        }

        double grandTotal = 0.0;
        // Polymorphic processing: all room types handled identically
        for (Room room : rooms) {
            double bill = room.calculateBill();
            System.out.printf(Locale.US, "%s: %.2f%n", room.getRoomType(), bill);
            grandTotal += bill;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
        scanner.close();
    }
}
