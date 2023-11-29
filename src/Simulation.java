import java.util.*;

public class Simulation {

    private final Map<Integer, Object> terminalMap = new LinkedHashMap<>();
    private final Map<Integer, Object> truckMap = new LinkedHashMap<>();

    private final Scanner input = new Scanner(System.in);
    private Terminal terminal;
    private Truck truck;
    private int terminalCount = 0;
    private int truckCount = 0;
    private int containerCount = 0;

    private boolean cont = true;

    private boolean end = true;

    void sim(){

        try{
            System.out.println("Begin Simulation:");
            while(end){
                int choice = menu();
                switch (choice){
                    case 0 -> {
                        end = false;
                        finalPrint();
                    }
                    case 1 -> createTerminalPhase();
                    case 2 -> {
                        if (terminalCount > 0) {
                            createTruckPhase();
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    case 3 -> {
                        if (terminalCount > 0) {
                            createContainerPhase();
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    case 4 -> {
                        if (truckCount > 0) {
                            refuelPhase();
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    case 5 -> {
                        if (truckCount > 0){
                            loadingPhase();
                        }
                        else{
                            throw new IllegalArgumentException();
                        }

                    }
                    case 6 -> {
                        if (truckCount > 0){
                            travelPhase();
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    default -> throw new IllegalArgumentException();
                }
            }
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
    }

    void finalPrint(){
        for (Integer key : terminalMap.keySet()){ //Prints out key
            Object o = terminalMap.get(key);
            System.out.println(o.toString());
        }
    }

    public int menu(){
        StringBuilder sb = new StringBuilder();
        sb.append("0. End Simulation");
        sb.append("\n1. Create Terminals");
        if(terminalCount>0){
            sb.append("\n2. Create Truck");
            sb.append("\n3. Create Container");
            sb.append("\n4. Refuel Truck");
        }
        if(truckCount>0 && containerCount>0){
            sb.append("\n5. Load/Unload Truck");
        }
        if(truckCount>0 && containerCount>0 && terminalCount>1){
            sb.append("\n6. Route truck");
        }
        System.out.println(sb);
        return input.nextInt();
    }

    public boolean inputYN(char c) throws IllegalArgumentException{
        if(c=='Y'||c=='y'||c=='N'||c=='n'){
            return c == 'Y'||c == 'n';
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

            System.out.println("Enter truck's initial fuel: ");
            double fuel = input.nextDouble();
            if(fuel<0){
                throw new IllegalArgumentException();
            }

            truck = new Truck(id, initialTerminal, loadCapacity, fuelConsumptionPerKM, fuel);
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
            Object o;
            System.out.println("Choose a terminal for loading containers: ");
            o = help(terminalMap);
            if (o instanceof IllegalArgumentException) {
                throw new IllegalArgumentException();
            }
            if (o instanceof InputMismatchException) {
                throw new InputMismatchException();
            }
            terminal = (Terminal) o;
            System.out.println("Choose a truck for loading/unloading: ");
            o = help(truckMap, terminal);
            if (o instanceof IllegalArgumentException) {
                throw new IllegalArgumentException();
            }
            if (o instanceof InputMismatchException) {
                throw new InputMismatchException();
            }
            truck = (Truck) o;

            System.out.println("Unload or Load Trucks?");

            System.out.println("1. Load Truck");
            System.out.println("2. Unload Truck");
            System.out.println("3. End Loading Phase");
            Container container;
            switch(input.nextInt()){
                case 1: {
                    System.out.println("Select container to load");
                    o = help(terminal);
                    if(o instanceof IllegalArgumentException){
                        throw new IllegalArgumentException();
                    }
                    container = (Container) o;

                    if(container.weight + truck.getCurrentLoadCapacity()>truck.getTotalLoadCapacity()){
                        System.out.println("Container weight too heavy!");
                    }
                    else{
                        truck.load(container); //Loads container into truck data structure
                        terminal.getContainerMap().remove(container.id, container); //Removes container from terminal data structure
                    }
                    break;
                }
                case 2: {
                    System.out.println("Select container to unload");
                    o = helpUnload(truck);
                    if(o instanceof IllegalArgumentException){
                        throw new IllegalArgumentException();
                    }
                    container = (Container) o;
                    truck.unLoad(container);
                    terminal.getContainerMap().put(container.id, container);
                    break;
                }

                case 3: {
                    break;
                }
                default: throw new IllegalArgumentException();
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
            System.out.println("Choose origin terminal: ");
            terminal = (Terminal) help(terminalMap);

            System.out.println("Choose a truck to travel");
            truck = (Truck) help(truckMap, terminal);

            System.out.println("Choose destination terminal");
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
        try{
            double fuel;
            System.out.println("Choose Truck to refuel");
            truck = (Truck) help(truckMap);
            System.out.println("How much fuel to add?");
            fuel = input.nextDouble();
            if(fuel<0){
                throw new IllegalArgumentException();
            }
            else{
                truck.reFuel(fuel);
            }
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid Input");
            input.nextLine();
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
                System.out.println(m.toString());

                System.out.println("Enter ID: ");
                id = input.next();

                if(m.getContainerMap().containsKey(Integer.parseInt(id))){
                    return m.getContainerMap().get(Integer.parseInt(id));
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
            else{
                return m.getContainerMap().get(Integer.parseInt(id));
            }

        }
        catch(InputMismatchException | IllegalArgumentException e){
            return e;
        }
    }

    public Object helpUnload(Truck t){
        String id;
        try {
            System.out.println("Type \"Help\" to see list of IDs");
            id = input.next();
            if (Objects.equals(id, "Help")) {
                System.out.println(t);

                System.out.println("Enter ID: ");
                id = input.next();

                if (t.getContainerMap().containsKey(Integer.parseInt(id))) {
                    return t.getContainerMap().get(Integer.parseInt(id));
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
            return t.getContainerMap().get(Integer.parseInt(id));
        }
        catch(InputMismatchException | IllegalArgumentException e){
            return e;
        }
    }
}