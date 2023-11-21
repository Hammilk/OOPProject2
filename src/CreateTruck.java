import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class CreateTruck {
    Truck createTruck(Map<String, Object> terminalMap) {

        Scanner input = new Scanner(System.in);
        KeyGen keyGen = new KeyGen();

        System.out.println("Phase 2: Create Truck.");
        String id = keyGen.keyGenerator("Truck");
        System.out.println("Enter which initial terminal location of the truck " + ": ");
        System.out.println("Type \"Help\" to see list of Terminal IDs");
        if (Objects.equals(input.next(), "Help")) {
            help(terminalMap);
            System.out.println("Enter terminal ID");
        }
        Terminal initialTerminal = (Terminal) terminalMap.get(input.next());

        System.out.println("Enter total load capacity of truck " + ": ");
        int loadCapacity = input.nextInt();

        System.out.println("Enter truck's base fuel consumption (G/KM): ");
        double fuelConsumptionPerKM = input.nextDouble();

        System.out.println("Enter truck's initial fuel: ");
        double fuel = input.nextDouble();

        return new Truck(id, initialTerminal, loadCapacity, fuelConsumptionPerKM, fuel);
    }

    void help(Map<String, Object> m){
        for (String key : m.keySet()){
            System.out.println("ID: " + key + "\n");
        }
    }


}


