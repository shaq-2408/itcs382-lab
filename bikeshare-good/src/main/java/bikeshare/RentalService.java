package bikeshare;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RentalService {
    public double calculateFee(String memberType, int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("Minutes must be positive.");
        }

        if ("student".equalsIgnoreCase(memberType)) {
            return minutes * 2.0;
        }

        if ("staff".equalsIgnoreCase(memberType)) {
            return minutes * 3.0;
        }

        throw new IllegalArgumentException("Member type must be student or staff.");
    }

    public void saveRide(String bikeId, String memberId, int minutes, double fee) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("rides.csv", true))) {
            writer.printf("%s,%s,%d,%.2f%n", bikeId, memberId, minutes, fee);
        } catch (IOException e) {
            throw new IllegalStateException("Could not save ride to rides.csv", e);
        }
    }
}