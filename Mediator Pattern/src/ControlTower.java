import java.util.*;

public class ControlTower implements TowerMediator {
    private Queue<Aircraft> landingQueue = new LinkedList<>();
    private Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private List<Aircraft> allAircraft = new ArrayList<>();
    private boolean runwayBusy = false;
    public void registerAircraft(Aircraft aircraft) {
        allAircraft.add(aircraft);
    }
    @Override
    public void broadcast(String msg, Aircraft sender) {
        if ("MAYDAY".equalsIgnoreCase(msg)) {
            handleEmergency(sender);
        } else if ("REQUEST_LAND".equalsIgnoreCase(msg)) {
            enqueueLanding(sender);
        } else if ("REQUEST_TAKEOFF".equalsIgnoreCase(msg)) {
            enqueueTakeoff(sender);
        }
    }
    @Override
    public boolean requestRunway(Aircraft a) {
        return false;
    }
    private void handleEmergency(Aircraft sender) {
        System.out.println("EMERGENCY from " + sender);
        clearRunway();
        notifyAll("Emergency! " + sender + " landing now. Hold position.");
        System.out.println(sender + " is landing immediately!");
        runwayBusy = true;
        delayThenRelease(sender, 3000);
    }
    private void enqueueLanding(Aircraft a) {
        if (a.isEmergency()) {
            System.out.println("Emergency landing queued for " + a);
            ((LinkedList<Aircraft>) landingQueue).addFirst(a);
        } else {
            System.out.println("Landing requested by " + a);
            landingQueue.offer(a);
        }
    }
    private void enqueueTakeoff(Aircraft a) {
        System.out.println("Takeoff requested by " + a);
        takeoffQueue.offer(a);
    }
    private void notifyAll(String msg) {
        for (Aircraft a : allAircraft) {
            a.receive(msg);
        }
    }
    public void processQueues() {
        if (runwayBusy) return;
        Aircraft next = null;
        if (!landingQueue.isEmpty()) {
            next = landingQueue.poll();
            System.out.println("✈️ " + next + " is landing...");
        } else if (!takeoffQueue.isEmpty()) {
            next = takeoffQueue.poll();
            System.out.println("🛫 " + next + " is taking off...");
        }

        if (next != null) {
            runwayBusy = true;
            delayThenRelease(next, 2000);
        }
    }

    private void clearRunway() {
        landingQueue.clear();
        takeoffQueue.clear();
        runwayBusy = false;
    }

    private void delayThenRelease(Aircraft a, int delay) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Runway is now free after " + a);
                runwayBusy = false;
            }
        }, delay);
    }
}
