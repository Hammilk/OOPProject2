public interface ITerminal {
    void incomingTrucks(Integer id, Truck T);

    void outgoingTruck(Integer id, Truck T);

    void addContainer(Container T);

    void removeContainer(Container T);

}
