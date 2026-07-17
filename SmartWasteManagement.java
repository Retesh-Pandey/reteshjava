import java.util.*;

class Bin {
    private String location;
    private int fillLevel; // percentage (0–100)
    private String wasteType;

    public Bin(String location, int fillLevel, String wasteType) {
        this.location = location;
        this.fillLevel = fillLevel;
        this.wasteType = wasteType;
    }

    public String getLocation() {
        return location;
    }

    public int getFillLevel() {
        return fillLevel;
    }

    public String getWasteType() {
        return wasteType;
    }

    public boolean needsCollection() {
        return fillLevel >= 80; // threshold
    }

    @Override
    public String toString() {
        return "Bin at " + location + " | Fill: " + fillLevel + "% | Waste: " + wasteType;
    }
}

class WasteManagementSystem {
    private List<Bin> bins;

    public WasteManagementSystem() {
        bins = new ArrayList<>();
    }

    public void addBin(Bin bin) {
        bins.add(bin);
    }

    public void checkBins() {
        System.out.println("Checking bins...");
        for (Bin bin : bins) {
            System.out.println(bin);
            if (bin.needsCollection()) {
                System.out.println("⚠ ALERT: Bin at " + bin.getLocation() + " needs collection!");
                suggestRecycling(bin.getWasteType());
            }
        }
    }

    private void suggestRecycling(String wasteType) {
        switch (wasteType.toLowerCase()) {
            case "plastic":
                System.out.println("♻ Suggestion: Send to plastic recycling plant.");
                break;
            case "organic":
                System.out.println("🌱 Suggestion: Use for composting.");
                break;
            case "paper":
                System.out.println("📄 Suggestion: Recycle into new paper products.");
                break;
            default:
                System.out.println("🚮 Suggestion: Dispose safely.");
        }
    }
}

public class SmartWasteManagement {
    public static void main(String[] args) {
        WasteManagementSystem system = new WasteManagementSystem();

        // Sample bins
        system.addBin(new Bin("Market Street", 85, "Plastic"));
        system.addBin(new Bin("City Park", 40, "Organic"));
        system.addBin(new Bin("School Road", 90, "Paper"));

        // Run check
        system.checkBins();
    }
}
