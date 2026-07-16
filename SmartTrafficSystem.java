import java.util.Scanner;

class TrafficLight {
    private String color;
    private int greenTime;
    private int redTime;

    public TrafficLight(int green, int red) {
        this.greenTime = green;
        this.redTime = red;
        this.color = "RED";
    }

    public void changeLight(int trafficDensity) {
        if (trafficDensity > 10) {
            greenTime += 5; // extend green for heavy traffic
        } else {
            greenTime = Math.max(10, greenTime - 2);
        }
        color = "GREEN";
        System.out.println("Light is GREEN for " + greenTime + " seconds");
    }

    public void resetLight() {
        color = "RED";
        System.out.println("Light is RED for " + redTime + " seconds");
    }
}

class EmergencyVehicle {
    public void overrideSignal(TrafficLight light) {
        System.out.println("🚨 Emergency vehicle detected! Turning signal GREEN immediately.");
        light.changeLight(20); // force green
    }
}

public class SmartTrafficSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TrafficLight tl = new TrafficLight(15, 10);

        System.out.print("Enter traffic density (number of vehicles): ");
        int density = sc.nextInt();

        System.out.print("Is there an emergency vehicle? (yes/no): ");
        String emergency = sc.next();

        if (emergency.equalsIgnoreCase("yes")) {
            EmergencyVehicle ev = new EmergencyVehicle();
            ev.overrideSignal(tl);
        } else {
            tl.changeLight(density);
        }

        tl.resetLight();
        sc.close();
    }
}
