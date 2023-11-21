import java.util.Map;
import java.util.Scanner;

public class pickTruck {
    Scanner input = new Scanner(System.in);
    Object pTruck(Map<String, Object> truckMap){
        System.out.println("Pick a truck by ID");
        System.out.println("Type \"Help\" to see list of Truck IDs");
        String truckId = input.next();
        if(truckId.equals("Help")){
            for (String truckKey : truckMap.keySet()){
                System.out.println("Truck ID: " + truckKey + "\n");
            }
            System.out.println("Pick a truck by ID");
            truckId = input.next();
        }

        return truckMap.get(truckId);
    }
}
