import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Simulation extends Help {

    Map<Integer, Object> terminalMap;
    Map<Integer, Object> truckMap;
    Map<Integer, Object> containerMap;

    Scanner input = new Scanner(System.in);
    Container container = null;
    Terminal terminal = null;
    Truck truck = null;

    void sim(){

        boolean cont = true;
        int terminalCount = 0;
        int truckCount = 0;
        int containerCount = 0;

        //Create Terminals

        while(cont){
            terminalMap.put(terminalCount++, new CreateTerminal().createTerminal(terminalCount));
            System.out.println("Continue making new terminals? (Y/N)");
            if(input.next().charAt(0)=='N'){
                cont=false;
            }
        }
        cont = true;

        //Create Truck or use existing truck phase

        while(cont){
            truckMap.put(truckCount++, new CreateTruck().createTruck(terminalMap, terminalCount));
            System.out.println("Continue making new trucks? (Y/N)");
            if(input.next().charAt(0)=='N'){
                cont=false;
            }
        }
        cont = true;

        //Create Container Phase or use existing container

        while(cont){
            terminal.addContainer(new CreateContainer().createContainer(containerCount));
            System.out.println("Continue making new containers? (Y/N)");
            if(input.next().charAt(0)=='N'){
                cont=false;
            }
        }
        cont = true;

        //Loading/Unloading Phase
        while(cont){
            System.out.println("Choose a truck for loading/unloading: ");
            truck = (Truck) help(truckMap);
            while(cont){
                System.out.println("Load truck ID " + truck.ID + "? (Y/N)");
                if(input.next().charAt(0)=='Y'){
                    System.out.println("Select container to load");
                    container = (Container) help(containerMap, terminal);
                    truck.load(container);
                    System.out.println("Load more containers into truck ID" + truck.ID + "? (Y/N)");
                    if(input.next().charAt(0)=='N') {
                        cont = false;
                    }
                }
            }
            cont = true;
            while(cont){
                System.out.println("Unload truck ID " + truck.ID + "? (Y/N)");
                if(input.next().charAt(0)=='Y'){
                    while(cont){
                        System.out.println("Select container to unload");
                        container = (Container) help(containerMap, terminal);
                        truck.load(container);
                        System.out.println("Load more containers into truck ID" + truck.ID + "? (Y/N)");
                        if(input.next().charAt(0)=='N') {
                            cont = false;
                        }
                    }
                }
                cont = true;
                System.out.println("Load more trucks?");
                if(input.next().charAt(0)=='N'){
                    cont = false;
                }

            }

        }
        cont = true;
        //Travel Phase

        while(cont){
            Terminal destinationTerminal;
            System.out.println("Choose a terminal: ");
            terminal = (Terminal) help(terminalMap);
            System.out.println("Choose a truck to travel");
            truck = (Truck) help(terminalMap, terminal);
            System.out.println("Choose a destination point");
            destinationTerminal = (Terminal) help(terminalMap);




        }

        //Truck Refuel Phase


        //Truck Picking Phase


        //Create container phase


    }








}


/*
System.out.println("Type \"Help\" to see list of Terminal IDs");
            if(Objects.equals(input.next(), "Help")){
                help(terminalMap);
                System.out.println("Enter Terminal ID");
            }


 */