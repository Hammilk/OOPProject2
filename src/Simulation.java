import java.util.*;

public class Simulation {

    private final Map<Integer, Object> terminalMap = new LinkedHashMap<>();
    private final Map<Integer, Object> truckMap = new LinkedHashMap<>();

    private final Scanner input = new Scanner(System.in);
    private Container container;
    private Terminal terminal;
    private Truck truck;
    private int terminalCount = 0;
    private int truckCount = 0;
    private int containerCount = 0;

    private boolean cont = true;

    void sim(){

        //TODO Final Output

        //Create Terminals
        createTerminalPhase();

        //Create Truck or use existing truck phase
        createTruckPhase();

        //Create Container Phase or use existing container
        createContainerPhase();

        //Loading/Unloading Phase
        loadingPhase();

        //TODO Exception Handling Weight Limits

        //Travel Phase
        travelPhase();

        //Truck Unloading Phase
        loadingPhase();

        //Truck Refuel Phase
        refuelPhase();
        //TODO Exception Handling Data validation
        //TODO Exception Handling Fuel Limits

    }
    public boolean inputYN(char c) throws IllegalArgumentException{
        if(c=='Y'||c=='N'){
            return c == 'Y';
        }
        else{
            throw new IllegalArgumentException("Must enter Y or N");
        }
    }

    public void createTerminalPhase(){
        while(cont){
            //Terminal Creation step
            terminal = createTerminal(terminalCount); //Terminal count used for ID
            terminalMap.put(terminalCount, terminal);
            System.out.println("Create additional terminals? (Y/N)");
            cont = inputYN(input.next().charAt(0)); //InputYN was just repetitive code I turned into a function.
            //All it does is valid Y/N
            terminalCount++; //Using a simple count to generate IDs
         }
    }

    public Terminal createTerminal(int id){
        int x = 0;
        int y = 0;

        while(cont){
            try{
                System.out.println("Create Terminal.");
                System.out.println("Enter X-Coordinate of Terminal " + ": "); //Any integer input valid
                x = input.nextInt();
                System.out.println("Enter Y-Coordinate of Terminal " + ": "); //Any integer input valid
                y = input.nextInt();
                cont=false;
            }
            catch(InputMismatchException ex){
                System.out.println("Enter an integer for input.");
                cont=true;
                input.nextLine();
            }
        }
        return new Terminal(id, x, y);
    }

    public void createTruckPhase(){
        boolean cont = true;
        while(cont){
            System.out.println("Create Truck.");
            truckMap.put(truckCount, createTruck(terminalMap, truckCount));
            truckCount++;
            System.out.println("Continue making new trucks? (Y/N)");
            cont = inputYN(input.next().charAt(0));
        }
    }

    public Truck createTruck(Map<Integer, Object> terminalMap, int id) {
        Object o;
        try{
            System.out.println("Enter which initial terminal location of the truck " + ": ");
            o = help(terminalMap);
            if(o instanceof IllegalArgumentException){
                throw new IllegalArgumentException();
            }
            Terminal initialTerminal = (Terminal) o;

            System.out.println("Enter total load capacity of truck " + ": ");
            int loadCapacity = input.nextInt();
            if(loadCapacity<0){
                throw new IllegalArgumentException();
            }

            System.out.println("Enter truck's base fuel consumption (G/KM): ");
            double fuelConsumptionPerKM = input.nextDouble();
            if(fuelConsumptionPerKM<0){
                throw new IllegalArgumentException();
            }

            System.out.println("Enter truck's total fuel capacity");
            double fuelCapacity = input.nextDouble();
            if(fuelCapacity<0){
                throw new IllegalArgumentException();
            }

            System.out.println("Enter truck's initial fuel: ");
            double fuel = input.nextDouble();
            if(fuel<0){
                throw new IllegalArgumentException();
            }
            if(fuel>fuelCapacity){
                System.out.println("Truck's initial fuel is more than the the total fuel capacity of truck");
                throw new IllegalArgumentException();

            }
            truck = new Truck(id, initialTerminal, loadCapacity, fuelConsumptionPerKM, fuel, fuelCapacity);
            initialTerminal.incomingTrucks(id, truck);
        }
        catch(InputMismatchException e){
            System.out.println("Input does not match datatype required.");
            truckCount--;
            input.nextLine();
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid Input");
            truckCount--;
            input.nextLine();
        }
        return truck;
    }

    public void createContainerPhase(){
        boolean cont = true;
        while(cont){
            createContainer(containerCount);
            System.out.println("Continue making new containers? (Y/N)");
            cont = inputYN(input.next().charAt(0));
            containerCount++;
        }
    }

    public void createContainer(int id){
        Object o;
        Container container;
        try{
            int weight;

            Terminal terminal1;

            System.out.println("Create Container.");

            System.out.println("Which terminal should the container be placed?");
            o = help(terminalMap);
            if(o instanceof IllegalArgumentException){
                throw new IllegalArgumentException();
            }
            if(o instanceof InputMismatchException){
                throw new InputMismatchException();
            }
            terminal1 = (Terminal) o;

            System.out.println("How heavy is the load in the container?");
            weight = input.nextInt();
            if(weight < 0){
                throw new IllegalArgumentException();
            }
            else if(weight < 3000){
                container = new Basic(id, weight);
            }
            else{
                System.out.println("What type of heavy container?"); //Section creates container object depending on type
                System.out.println("1. Heavy (Normal)");
                System.out.println("2. Refrigerator");
                System.out.println("3. Tanker");
                switch (input.nextInt()){
                    case 1 -> container = new Heavy(id, weight);
                    case 2 -> container = new Refrigerator(id, weight);
                    case 3 -> container = new Tanker(id, weight);
                    default -> throw new IllegalArgumentException();
                }
            }
            terminal1.addContainer(container);
        }
        catch(InputMismatchException e){
            System.out.println("Input does not match datatype required.");
            containerCount--;
            input.nextLine();
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid Input");
            containerCount--;
            input.nextLine();
        }
    }

    public void loadingPhase(){
        try{
            boolean cont = true;
            Object o;
            while(cont){
                System.out.println("Choose a terminal for loading containers: ");
                o = help(terminalMap);
                if(o instanceof IllegalArgumentException){
                    throw new IllegalArgumentException();
                }
                if(o instanceof InputMismatchException){
                    throw new InputMismatchException();
                }
                terminal = (Terminal) o;
                System.out.println("Choose a truck for loading/unloading: ");
                o = help(truckMap, terminal);
                if(o instanceof IllegalArgumentException){
                    throw new IllegalArgumentException();
                }
                if(o instanceof InputMismatchException){
                    throw new InputMismatchException();
                }
                truck = (Truck) o;

                while(cont){
                    System.out.println("Load truck ID " + truck.getID() + "? (Y/N)");
                    if(inputYN(input.next().charAt(0))){
                        System.out.println("Select container to load");
                        container = (Container) help(terminal);

                        truck.load(container); //Loads container into truck data structure
                        terminal.getContainerMap().remove(container.id); //Removes container from terminal data structure

                        System.out.println("Load more containers into truck ID: " + truck.getID() + "? (Y/N)");
                        cont = inputYN(input.next().charAt(0));
                    }
                }
                cont = true;
                while(cont){
                    System.out.println("Unload truck ID: " + truck.getID() + "? (Y/N)");
                    if(input.next().charAt(0)=='Y'){
                        while(cont){
                            System.out.println("Select container to unload");
                            System.out.println(truck.getContainerMap());
                            System.out.println("Choose container to unload");
                            truck.unLoad(container);
                            System.out.println("Unload more containers into truck ID" + truck.getID() + "? (Y/N)");
                            cont = inputYN(input.next().charAt(0));
                        }
                    }
                    System.out.println("Load more trucks? (Y/N)");
                    cont = inputYN(input.next().charAt(0));
                }

            }
        }
        catch(InputMismatchException e){
            System.out.println("Input does not match datatype required.");
            input.nextLine();
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid Input");
            input.nextLine();
        }
    }

    public void travelPhase(){
        boolean cont = true;
        while(cont){
            Terminal destinationTerminal;
            System.out.println("Choose a terminal: ");
            terminal = (Terminal) help(terminalMap);

            System.out.println("Choose a truck to travel");
            truck = (Truck) help(truckMap, terminal);

            System.out.println("Choose a destination point");
            destinationTerminal = (Terminal) help(terminalMap);

            if(truck.goTo(destinationTerminal)){
                terminal.outgoingTruck(truck.getID(), truck);
                destinationTerminal.incomingTrucks(truck.getID(), truck);
            }

            System.out.println("Route additional trucks? (Y/N)");
            cont = inputYN(input.next().charAt(0));
        }
    }

    public void refuelPhase(){
        boolean cont = true;
        try{
            while(cont){
                System.out.println("Refuel Truck?");

                if(inputYN(input.next().charAt(0))){

                    System.out.println("Choose Truck");
                    truck = (Truck) help(truckMap);

                    System.out.println("Refuel Truck? (Y/N)");
                    if(inputYN(input.next().charAt(0))){

                        System.out.println("How much fuel to add?");
                        truck.reFuel(input.nextDouble());
                    }

                    System.out.println("Refuel more trucks?");
                    if(inputYN(input.next().charAt(0))){
                        cont=false;
                    }
                }
                else cont = false;
            }
        }
        catch(InputMismatchException e){
            System.out.println("Input does not match datatype required.");
            input.nextLine();
        }
    }

    public Object help(Map<Integer, Object> m){
        String id;
        try{
            System.out.println("Type \"Help\" to see list of IDs");
            id = input.next();

            if(Objects.equals(id, "Help")){ //Activates display on "Help" Input
                for (Integer key : m.keySet()){ //Prints out key
                    Object o = m.get(key);
                    System.out.println(o.toString());
                }
                System.out.println("Enter ID: ");
                id = input.next();
                if(!m.containsKey(Integer.parseInt(id))){
                    throw new IllegalArgumentException();
                }

            }
            return m.get(Integer.parseInt(id));
        }
        catch(InputMismatchException | IllegalArgumentException e){
            return e;
        }
    }

    public Object help(Map<Integer, Object> m, Terminal terminal) {
        String id;
        try{
            System.out.println("Type \"Help\" to see list of IDs");
            id = input.next();
            if (Objects.equals(id, "Help")) {
                for (Integer key : m.keySet()) {
                    if (terminal.getCurrent().containsValue(m.get(key))) {
                        Object o = m.get(key);
                        System.out.println(o.toString());
                    }
                }
                System.out.println("Enter ID: ");
                id = input.next();
                if(!m.containsKey(Integer.parseInt(id))){
                    throw new IllegalArgumentException();
                }
            }
            return m.get(Integer.parseInt(id));
        }
        catch(InputMismatchException | IllegalArgumentException e){
            return e;
        }


    }
    public Object help(Terminal m){
        String id;
        try{
            System.out.println("Type \"Help\" to see list of IDs");
            id = input.next();
            if(Objects.equals(id, "Help")){
                for (Integer key : m.getContainerMap().keySet()){
                    System.out.println("ID: " + key + "\n");
                }
                System.out.println("Enter ID: ");
                id = input.next();
                if(!m.getContainerMap().containsKey(Integer.parseInt(id))){
                    throw new IllegalArgumentException();
                }
            }
            return m.getContainerMap().get(Integer.parseInt(id));
        }
        catch(InputMismatchException | IllegalArgumentException e){
            return e;
        }


    }
}