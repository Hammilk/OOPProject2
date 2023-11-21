import java.util.ArrayList;
import java.util.Map;

public class Terminal implements ITerminal {
    private int ID;
    private double xCoordinate;
    private double yCoordinate;

    Map<Integer, Object> containerMap;
    ArrayList<Truck> history = new ArrayList<>();
    ArrayList<Truck> current = new ArrayList<>();

    Terminal(int ID, double xCoordinate, double yCoordinate){
        this.ID = ID;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public void addContainer(Container T) {
        containerMap.put(T.id,T);
    }

    @Override
    public void removeContainer(Container T) {
        containerMap.remove(T.id, T);
    }

    @Override
    public void incomingTrucks(Truck T) {
        history.remove(T);
        current.add(T);
    }

    @Override
    public void outgoingTruck(Truck T) {
        current.remove(T);
        history.add(T);
    }

    double getDistance(Terminal other){
        return (yCoordinate - other.getyCoordinate())/(xCoordinate - other.getxCoordinate());
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }
}
