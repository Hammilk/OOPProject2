import java.util.Scanner;

public class CreateContainer {
    Container createContainer(int id){

        Scanner input = new Scanner(System.in);
        int weight;

        Container container = null;

        System.out.println("Create Container.");

        System.out.println("What type of container to create?");
        System.out.println("1. Basic Container");
        System.out.println("2. Heavy (Normal) Container");
        System.out.println("3. Truck Container");
        System.out.println("4. Tanker Container");

        switch(input.nextInt()){
            case 1:
                System.out.println("How heavy is the load in the container?");
                weight = input.nextInt();
                container = new Basic(id, weight);
                break;
            case 2:
                System.out.println("How heavy is the load in the container?");
                weight = input.nextInt();
                container = new Heavy(id, weight);
                break;
            case 3:
                System.out.println("How heavy is the load in the container?");
                weight = input.nextInt();
                container = new Refrigerator(id, weight);
                break;
            case 4:
                System.out.println("How heavy is the load in the container?");
                weight = input.nextInt();
                container = new Tanker(id, weight);
                break;
            default:
                System.out.println("ERROR");
        }
        return container;
    }
}
