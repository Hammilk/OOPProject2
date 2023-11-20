import java.util.ArrayList;

public class Truck implements ITruck{
    int ID;

    double fuel;

    int totalLoadCapacity;

    double fuelConsumptionPerKm;

    Terminal currentTerminal;

    ArrayList<Container> ContainerList = new ArrayList<>();


    public Truck(int ID, Terminal p, int totalLoadCapacity, double fuelConsumptionPerKm,
                 double fuel){
        this.ID = ID;
        this.currentTerminal = p;
        this.totalLoadCapacity = totalLoadCapacity;
        this.fuelConsumptionPerKm = fuelConsumptionPerKm;
        this.fuel = fuel;
    }


    ArrayList<Container> getCurrentContainers(){
        return ContainerList;
    }

    @Override
    public boolean goTo(Terminal p) {
        double fuelUse = p.getDistance(currentTerminal)*fuelConsumptionPerKm;
        if(fuelUse<fuel){
            currentTerminal = p;
            fuel-=fuelUse;
            return true;
        }
        else{
            fuel = 0;
            return false;
        }

    }

    @Override
    public void reFuel(double newFuel) {
        fuel += newFuel;
    }

    @Override
    public boolean load(Container cont) {
        ContainerList.add(cont);
        fuelConsumptionPerKm += cont.consumption();
        return false;
    }

    @Override
    public boolean unLoad(Container cont) {
        ContainerList.remove(cont);
        fuelConsumptionPerKm -= cont.consumption();
        return false;
    }
}
