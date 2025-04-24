public abstract class Aircraft {
    protected String id;
    protected int fuelLevel;
    protected boolean emergency;
    public Aircraft(String id, int fuelLevel) {
        this.id = id;
        this.fuelLevel = fuelLevel;
        this.emergency = fuelLevel < 20;
    }
    public String getId() {
        return id;
    }
    public boolean isEmergency() {
        return emergency;
    }
    public int getFuelLevel() {
        return fuelLevel;
    }
    public abstract void receive(String msg);
    public void send(String msg, TowerMediator tower) {
        tower.broadcast(msg, this);
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id + (emergency ? " [MAYDAY]" : "");
    }
}
