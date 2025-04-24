public class PassengerPlane extends Aircraft {
    public PassengerPlane(String id, int fuelLevel) {
        super(id, fuelLevel);
    }
    @Override
    public void receive(String msg) {
        System.out.println("[PassengerPlane " + id + "]: " + msg);
    }
}
