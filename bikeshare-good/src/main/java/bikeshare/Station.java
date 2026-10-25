package bikeshare;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private final String name;
    private final List<Bike> bikes = new ArrayList<>();

    public Station(String name, int bikeCount) {
        this.name = name;

        for (int i = 1; i <= bikeCount; i++) {
            bikes.add(new Bike(name + "-" + i));
        }
    }

    public String getName() {
        return name;
    }

    public Bike rentBike() {
        for (Bike bike : bikes) {
            if (bike.isAvailable()) {
                bike.rent();
                return bike;
            }
        }

        return null;
    }

    public void returnBike(Bike bike) {
        bike.returnBike();
    }

    public int getAvailableCount() {
        int count = 0;

        for (Bike bike : bikes) {
            if (bike.isAvailable()) {
                count++;
            }
        }

        return count;
    }
}