import java.util.*;

public class Terminal implements ITerminal {
    private final int ID;
    private final double xCoordinate;
    private final double yCoordinate;

    private final Map<Integer, Object> containerMap = new LinkedHashMap<>();
    private final Map<Integer, Object> history = new LinkedHashMap<>();
    private final Map<Integer, Object> current = new LinkedHashMap<>();

    Terminal(int ID, double xCoordinate, double yCoordinate){
        this.ID = ID;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getID() {
        return ID;
    }

    @Override
    public void addContainer(Container T) {
        containerMap.put(T.id, T);
    }

    @Override
    public void removeContainer(Container T) {
        containerMap.remove(T.id, T);
    }

    @Override
    public void incomingTrucks(Integer id, Truck T) {
        history.remove(id, T);
        current.put(id, T);
    }

    @Override
    public void outgoingTruck(Integer id, Truck T) {
        current.remove(id, T);
        history.put(id ,T);
    }

    double getDistance(Terminal other) {
        return Math.sqrt(Math.pow(xCoordinate - other.getXCoordinate(), 2) + Math.pow(yCoordinate - other.getYCoordinate(), 2));
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    @Override
    public String toString() {
        Util obj1 = new Util(containerMap);

        return "Terminal ID: " +
                getID() +
                " (x=" +
                getXCoordinate() +
                ", y=" +
                getYCoordinate() +
                ")" +
                obj1.buildString(true) +
                displayTrucks();
    }

    String displayTrucks(){
        StringBuilder sb = new StringBuilder();
        for(Integer key : current.keySet()){
            Truck truck = (Truck) current.get(key);
            sb.append(truck.toString());
        }
        return sb.toString();
    }

    public Map<Integer, Object> getContainerMap() {
        return containerMap;
    }

    public Map<Integer, Object> getCurrent() {
        return current;
    }

    public Map<Integer, Object> getHistory() {
        return history;
    }
}