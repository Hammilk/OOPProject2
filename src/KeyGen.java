import java.util.Random;

public class KeyGen {

    String keyGenerator(String type){
        StringBuilder key = new StringBuilder();
        Random rand = new Random();
        int upperBound = 4; //Enter number of digits for key here

        int rand_int = rand.nextInt((int) Math.pow(10, upperBound));
        key.append(rand_int);
        while(key.length()<upperBound){
            key.insert(0, "0"); //Inserts 0's to have a fixed key length
        }
        if(type.equals("Truck")){
            key.insert(0, "TR");
        } else if (type.equals("Terminal")) {
            key.insert(0, "TL");
        } else{
            key.insert(0, "CR");
        }
         //Inserts Terminal or Truck depending on string passed to constructor
        return key.toString();
    }
}
