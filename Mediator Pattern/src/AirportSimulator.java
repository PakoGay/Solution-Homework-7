import java.util.*;
import java.util.concurrent.*;

public class AirportSimulator {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        List<Aircraft> aircrafts = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int fuel = random.nextInt(100); // 0–99
            Aircraft a;
            switch (random.nextInt(3)) {
                case 0 -> a = new PassengerPlane("P" + i, fuel);
                case 1 -> a = new CargoPlane("C" + i, fuel);
                default -> a = new Helicopter("H" + i, fuel);
            }
            aircrafts.add(a);
            tower.registerAircraft(a);
        }
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            Aircraft a = aircrafts.get(random.nextInt(aircrafts.size()));
            if (a.isEmergency()) {
                a.send("MAYDAY", tower);
            } else {
                if (random.nextBoolean()) {
                    a.send("REQUEST_LAND", tower);
                } else {
                    a.send("REQUEST_TAKEOFF", tower);
                }
            }
            tower.processQueues();
        }, 0, 1, TimeUnit.SECONDS);
    }
}
