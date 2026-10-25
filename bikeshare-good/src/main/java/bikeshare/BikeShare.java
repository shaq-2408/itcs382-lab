package bikeshare;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BikeShare {
    private final Map<String, Station> stations = new HashMap<>();
    private final RentalService rentalService = new RentalService();
    private final Map<String, Bike> activeRentals = new HashMap<>();

    public BikeShare() {
        stations.put("Library", new Station("Library", 2));
        stations.put("Canteen", new Station("Canteen", 2));
        stations.put("Dorm", new Station("Dorm", 2));
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Command (rent, return, quit): ");
                String command = scanner.nextLine().trim();

                if ("quit".equalsIgnoreCase(command)) {
                    break;
                }

                if ("rent".equalsIgnoreCase(command)) {
                    rentBike(scanner);
                } else if ("return".equalsIgnoreCase(command)) {
                    returnBike(scanner);
                } else {
                    System.out.println("Unknown command.");
                }
            }
        }
    }

    private void rentBike(Scanner scanner) {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();

        System.out.print("Member type (student/staff): ");
        String memberType = scanner.nextLine().trim();

        System.out.print("Station (Library/Canteen/Dorm): ");
        String stationName = scanner.nextLine().trim();

        Station station = stations.get(stationName);

        if (station == null) {
            System.out.println("Unknown station.");
            return;
        }

        if (!"student".equalsIgnoreCase(memberType)
                && !"staff".equalsIgnoreCase(memberType)) {
            System.out.println("Invalid member type.");
            return;
        }

        if (activeRentals.containsKey(memberId)) {
            System.out.println("Member already has a rental.");
            return;
        }

        Bike bike = station.rentBike();

        if (bike == null) {
            System.out.println("No bikes available.");
            return;
        }

        activeRentals.put(memberId, bike);
        System.out.println("Bike rented: " + bike.getId());
    }

    private void returnBike(Scanner scanner) {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();

        Bike bike = activeRentals.remove(memberId);

        if (bike == null) {
            System.out.println("No active rental for this member.");
            return;
        }

        System.out.print("Ride time in minutes: ");
        try {
            int minutes = Integer.parseInt(scanner.nextLine().trim());

            if (minutes <= 0) {
                System.out.println("Minutes must be positive.");
                activeRentals.put(memberId, bike);
                return;
            }

            System.out.print("Member type (student/staff): ");
            String memberType = scanner.nextLine().trim();

            double fee = rentalService.calculateFee(memberType, minutes);
            stations.get("Library").returnBike(bike);
            rentalService.saveRide(bike.getId(), memberId, minutes, fee);

            System.out.printf(
                    "Receipt: Bike=%s, Minutes=%d, Fee=%.2f baht%n",
                    bike.getId(), minutes, fee
            );
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            activeRentals.put(memberId, bike);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            activeRentals.put(memberId, bike);
        }
    }

    public static void main(String[] args) {
        new BikeShare().run();
    }
}
