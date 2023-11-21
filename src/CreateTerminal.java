import java.util.Scanner;

public class CreateTerminal {
    Terminal createTerminal(){

        Scanner input = new Scanner(System.in);
        KeyGen keyGen = new KeyGen();
        int x;
        int y;

        System.out.println("Create Terminal.");
        String id = keyGen.keyGenerator("Terminal");

        System.out.println("Enter X-Coordinate of Terminal " + ": ");
        x = input.nextInt();
        System.out.println("Enter Y-Coordinate of Terminal " + ": ");
        y = input.nextInt();


        return new Terminal(id, x, y);
    }
}
