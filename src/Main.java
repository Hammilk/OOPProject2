import java.util.*;

public class Main {
    public static void main(String[] args) {


        Set<String> terminalSet = new HashSet<>();
        Set<String> truckSet = new HashSet<>();

        int terminalCount = 0;
        int truckCount = 0;
        boolean cont = true;

        ArrayList<Terminal> terminalList = new ArrayList<>();

        ArrayList<Truck> truckList = new ArrayList<>();


        Scanner input = new Scanner(System.in);
        System.out.println("Initialize Truck Simulation");
        System.out.println("1st Step: Initialize Terminals");

        while(cont){
            terminalCount++;

            System.out.println("Assign ID to Terminal " + terminalCount + ":");
            int ID = input.nextInt();
            System.out.println("Enter X-Coordinate of Terminal " + terminalCount + ":");
            int X = input.nextInt();
            System.out.println("Enter Y-Coordinate of Terminal " + terminalCount + ":");
            int Y = input.nextInt();

            terminalList.add(new Terminal(ID, X, Y));

            System.out.println("Continue adding more terminals?");
            cont = Objects.equals(input.next(), "Y");
        }

        System.out.println("1st Step: Initialize Trucks");
        cont=true;

        while(cont){
            truckCount++;
            System.out.println("Assign ID to Truck " + truckCount + ":");
            int ID = input.nextInt();
            System.out.println("Enter initial Terminal's ID of Truck " + truckCount + ":");
            int terminalID = input.nextInt();
            System.out.println("Enter load capacity of Truck " + truckCount + ":");
            int loadCapacity = input.nextInt();
            System.out.println("Enter base fuel consumption rate (KM/H) of Truck " + truckCount + ":");
            double fuelConsumption = input.nextDouble();
            System.out.println("Enter initial fuel of Truck " + truckCount + ":");
            double fuel = input.nextDouble();

            terminalID.

            truckList.add(new Truck(ID, X, Y));

            System.out.println("Continue adding more Trucks?");
            cont = Objects.equals(input.next(), "Y");
        }



    }
}