import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Simulation {

    Map<String, Object> terminalMap;
    Map<String, Object> truckMap;
    Map<String, Object> containerMap;

    Scanner input = new Scanner(System.in);
    Container container = null;
    Terminal terminal = null;
    Truck truck = null;

    void sim(){

        //Create Terminal or choose existing terminal phase

        System.out.println("Create new Terminal or choose exising Terminal?");
        System.out.println("1. New Terminal");
        System.out.println("2. Existing Terminal");
        if(input.nextInt()==1){
            terminal = new CreateTerminal().createTerminal();
        }
        else{
            System.out.println("Enter Terminal ID");
            System.out.println("Type \"Help\" to see list of Terminal IDs");
            if(Objects.equals(input.next(), "Help")){
                help(terminalMap);
                System.out.println("Enter Terminal ID");
            }
            terminal = (Terminal) terminalMap.get(input.next());
        }

        //Create Truck or use existing truck phase

        System.out.println("Create new Truck or choose exising Terminal?");
        System.out.println("1. New Truck");
        System.out.println("2. Existing Truck");
        if(input.nextInt()==1){
            truck = new CreateTruck().createTruck(terminalMap);
        }
        else{
            System.out.println("Enter Truck ID");
            System.out.println("Type \"Help\" to see list of Truck IDs");
            if(Objects.equals(input.next(), "Help")){
                help(truckMap, terminal);
                System.out.println("Enter Truck ID");
            }
            truck = (Truck) truckMap.get(input.next());
        }

        //Create Container Phase or use existing container

        System.out.println("Create new container or use existing container?");
        System.out.println("1. New Container");
        System.out.println("2. Existing Container");
        if(input.nextInt()==1){
            container = new CreateContainer().createContainer();
        }
        else{
            System.out.println("Enter container ID");
            System.out.println("Type \"Help\" to see list of Container IDs");
            if(Objects.equals(input.next(), "Help")){
                help(containerMap);
                System.out.println("Enter container ID");
            }
            container = (Container) containerMap.get(input.next());
        }

        //Loading/Unloading Phase



        //Travel Phase

        //Truck Refuel Phase


        //Truck Picking Phase
        pickTruck obj1 = new pickTruck();
        obj1.pTruck(truckMap);

        //Create container phase
    }

    void help(Map<String, Object> m){
        for (String key : m.keySet()){
            System.out.println("ID: " + key + "\n");
        }
    }

    void help(Map<String, Object> m, Terminal terminal){
        for (String key : m.keySet()){
            if(m.containsValue(terminal)){
                System.out.println("ID: " + key + "\n");
            }
        }
    }

}
