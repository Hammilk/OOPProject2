import java.util.ArrayList;

public class Terminal implements ITerminal {
    private int ID;
    private double xCoordinate;
    private double yCoordinate;

    ArrayList<Container> containers = new ArrayList<>();
    ArrayList<Truck> history = new ArrayList<>();
    ArrayList<Truck> current = new ArrayList<>();

    Terminal(int ID, double xCoordinate, double yCoordinate){
        this.ID = ID;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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
