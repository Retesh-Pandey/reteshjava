import java.util.*;

class Medicine {
    String name;
    String dosage;
    String time; // e.g. "08:00 AM"

    Medicine(String name, String dosage, String time) {
        this.name = name;
        this.dosage = dosage;
        this.time = time;
    }
}

class InteractionChecker {
    // Simple database of harmful interactions
    private static Map<String, List<String>> interactions = new HashMap<>();

    static {
        interactions.put("Aspirin", Arrays.asList("Warfarin", "Ibuprofen"));
        interactions.put("Paracetamol", Arrays.asList("Alcohol"));
    }

    public static boolean checkInteraction(String med1, String med2) {
        if (interactions.containsKey(med1)) {
            return interactions.get(med1).contains(med2);
        }
        if (interactions.containsKey(med2)) {
            return interactions.get(med2).contains(med1);
        }
        return false;
    }
}

public class MedicineReminderApp {
    public static void main(String[] args) {
        List<Medicine> meds = new ArrayList<>();
        meds.add(new Medicine("Aspirin", "75mg", "08:00 AM"));
        meds.add(new Medicine("Warfarin", "5mg", "08:00 AM"));
        meds.add(new Medicine("Paracetamol", "500mg", "02:00 PM"));

        System.out.println("Your Medicine Schedule:");
        for (Medicine m : meds) {
            System.out.println(m.name + " - " + m.dosage + " at " + m.time);
        }

        System.out.println("\nChecking for harmful interactions...");
        for (int i = 0; i < meds.size(); i++) {
            for (int j = i + 1; j < meds.size(); j++) {
                if (InteractionChecker.checkInteraction(meds.get(i).name, meds.get(j).name)) {
                    System.out.println("⚠️ Warning: " + meds.get(i).name + " interacts with " + meds.get(j).name);
                }
            }
        }
    }
}
