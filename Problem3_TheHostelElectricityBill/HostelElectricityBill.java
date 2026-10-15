import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 3: The Hostel Electricity Bill (Alias class)
 */
public class HostelElectricityBill {

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

        public abstract double calculateBill();
    }

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
        for (Room room : rooms) {
            double bill = room.calculateBill();
            System.out.printf(Locale.US, "%s: %.2f%n", room.getRoomType(), bill);
            grandTotal += bill;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
        scanner.close();
    }
}
