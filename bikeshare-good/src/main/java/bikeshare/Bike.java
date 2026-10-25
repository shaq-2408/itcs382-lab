package bikeshare;

public class Bike {
    private final String id;
    private boolean available;

    public Bike(String id) {
        this.id = id;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        if (!available) {
            throw new IllegalStateException("Bike is not available.");
        }
        available = false;
    }

    public void returnBike() {
        if (available) {
            throw new IllegalStateException("Bike is already available.");
        }
        available = true;
    }
}