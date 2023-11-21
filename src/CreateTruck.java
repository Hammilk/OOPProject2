import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class CreateTruck extends Help {
    Truck createTruck(Map<Integer, Object> terminalMap, int id) {

        Scanner input = new Scanner(System.in);

        System.out.println("Create Truck.");

        System.out.println("Enter which initial terminal location of the truck " + ": ");
        System.out.println("Type \"Help\" to see list of Terminal IDs");
        if (Objects.equals(input.next(), "Help")) {
            help(terminalMap);
            System.out.println("Enter terminal ID");
        }
        Terminal initialTerminal = (Terminal) terminalMap.get(input.nextInt());

        System.out.println("Enter total load capacity of truck " + ": ");
        int loadCapacity = input.nextInt();

        System.out.println("Enter truck's base fuel consumption (G/KM): ");
        double fuelConsumptionPerKM = input.nextDouble();

        System.out.println("Enter truck's initial fuel: ");
        double fuel = input.nextDouble();

        return new Truck(id, initialTerminal, loadCapacity, fuelConsumptionPerKM, fuel);
    }



}


