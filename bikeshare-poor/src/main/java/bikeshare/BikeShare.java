package bikeshare;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BikeShare {
    public static final String[] STATIONS = {"Library", "Canteen", "Dorm"};
    public static final int STUDENT = 1, STAFF = 2;
    public static final int[] FEES = {0, 2, 3};
    public static final List<String[]> BIKES = new ArrayList<>();
    public static final Path FILE = Path.of("rides.csv");

    public static void main(String[] args) {
        for (int s = 0; s < STATIONS.length; s++) {
            for (int b = 1; b <= 2; b++) {
                BIKES.add(new String[]{"B" + (s * 2 + b), STATIONS[s], ""});
            }
        }

        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nBikeShare: rent | return | quit");
                System.out.print("> ");
                String command = in.nextLine().trim().toLowerCase();

                if (command.equals("quit")) {
                    break;
                }

                try {
                    if (command.equals("rent")) {
                        ride(in, true);
                    } else if (command.equals("return")) {
                        ride(in, false);
                    } else {
                        System.out.println("Unknown command.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("File error: " + e.getMessage());
                }
            }
        }
    }

    public static void ride(Scanner in, boolean renting) throws IOException {
        if (renting) {
            System.out.print("Member ID: ");
            String member = in.nextLine().trim();
            if (member.isEmpty() || member.contains(",")) {
                throw new IllegalArgumentException("Invalid member ID.");
            }

            System.out.print("Member type (1=student, 2=staff): ");
            int type;
            try {
                type = Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Member type must be 1 or 2.");
            }
            if (type != STUDENT && type != STAFF) {
                throw new IllegalArgumentException("Invalid member type.");
            }

            System.out.print("Station (Library, Canteen, Dorm): ");
            String station = station(in.nextLine());

            for (String[] bike : BIKES) {
                if (bike[1].equals(station) && bike[2].isEmpty()) {
                    bike[2] = member + "," + type;
                    System.out.println("Bike " + bike[0] + " rented successfully.");
                    return;
                }
            }

            throw new IllegalArgumentException("No bikes available at " + station + ".");
        }

        System.out.print("Bike ID: ");
        String id = in.nextLine().trim();

        for (String[] bike : BIKES) {
            if (bike[0].equalsIgnoreCase(id) && !bike[2].isEmpty()) {
                String[] rental = bike[2].split(",", -1);

                System.out.print("Return station (Library, Canteen, Dorm): ");
                String destination = station(in.nextLine());

                System.out.print("Ride time in minutes: ");
                int minutes;
                try {
                    minutes = Integer.parseInt(in.nextLine().trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Minutes must be a number.");
                }
                if (minutes <= 0) {
                    throw new IllegalArgumentException("Minutes must be positive.");
                }

                int fee = minutes * FEES[Integer.parseInt(rental[1])];
                String member = rental[0];

                try (BufferedWriter out = Files.newBufferedWriter(
                        FILE, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                    if (Files.size(FILE) == 0) {
                        out.write("member_id,bike_id,from_station,to_station,minutes,fee");
                        out.newLine();
                    }
                    out.write(csv(member) + "," + bike[0] + "," + bike[1] + ","
                            + destination + "," + minutes + "," + fee);
                    out.newLine();
                }

                System.out.println("\n--- BikeShare Receipt ---");
                System.out.println("Member: " + member);
                System.out.println("Bike: " + bike[0]);
                System.out.println("From: " + bike[1]);
                System.out.println("To: " + destination);
                System.out.println("Minutes: " + minutes);
                System.out.println("Fee: " + fee + " baht");
                System.out.println("-------------------------");

                bike[1] = destination;
                bike[2] = "";
                return;
            }
        }

        throw new IllegalArgumentException("Bike not found or not rented.");
    }

    public static String station(String input) {
        for (String s : STATIONS) {
            if (s.equalsIgnoreCase(input.trim())) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid station.");
    }

    public static String csv(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}