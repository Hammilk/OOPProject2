import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Truck implements ITruck{
    private int ID;
    private double fuel;
    private int totalLoadCapacity;
    private double fuelConsumptionPerKm;

    private int currentLoadCapacity = 0;

    Object currentTerminal;

    Map<Integer, Object> containerMap = new HashMap<>();


    public Truck(int ID, Object terminal, int totalLoadCapacity, double fuelConsumptionPerKm,
                 double fuel){
        this.ID = ID;
        this.currentTerminal = terminal;
        this.totalLoadCapacity = totalLoadCapacity;
        this.fuelConsumptionPerKm = fuelConsumptionPerKm;
        this.fuel = fuel;
    }

    @Override
    public boolean goTo(Terminal p) {
        double fuelUse = p.getDistance((Terminal) currentTerminal)*fuelConsumptionPerKm;
        if(fuelUse<fuel){
            currentTerminal = p;
            fuel-=fuelUse;
            return true;
        }
        else{
            System.out.println("Not enough fuel!");
            return false;
        }

    }

    @Override
    public void reFuel(double newFuel) {
        fuel += newFuel;
    }

    @Override
    public boolean load(Container cont) {
        containerMap.put(cont.id, cont);
        fuelConsumptionPerKm += cont.consumption();
        currentLoadCapacity+=cont.weight;
        return false;
    }

    @Override
    public boolean unLoad(Container cont) {
        containerMap.remove(cont.id, cont);
        fuelConsumptionPerKm -= cont.consumption();
        currentLoadCapacity-=cont.weight;
        return false;
    }

    public int getID() {
        return ID;
    }

    public int getCurrentLoadCapacity() {
        return currentLoadCapacity;
    }

    public int getTotalLoadCapacity() {
        return totalLoadCapacity;
    }

    public Map<Integer, Object> getContainerMap() {
        return containerMap;
    }

    @Override
    public String toString() {
        Util obj1 = new Util(containerMap);

        return "\n  Truck ID: " + ID
                +"\n  Fuel Left: " + fuel
                +"\n  " + obj1.buildString();
    }
}
