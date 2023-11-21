import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Help {
    Scanner input = new Scanner(System.in);
    Object help(Map<Integer, Object> m){
        String id;
        System.out.println("Type \"Help\" to see list of" + m.get(1).getClass().getSimpleName() + "IDs");
        id = input.next();
        if(Objects.equals(id, "Help")){
            for (Integer key : m.keySet()){
                System.out.println("ID: " + key + "\n");
                System.out.println("Enter "+ m.get(1).getClass().getSimpleName() + "ID: ");
                return m.get(input.nextInt());
            }
        }
        else{
            return m.get(Integer.parseInt(id));
        }
        return -1;
    }

    Object help(Map<Integer, Object> m, Terminal terminal){
        String id;
        System.out.println("Type \"Help\" to see list of" + m.get(1).getClass().getSimpleName() + "IDs");
        id = input.next();
        if(Objects.equals(id, "Help")){
            for (Integer key : m.keySet()){
                if(m.containsValue(terminal)){
                    System.out.println("ID: " + key + "\n");
                    System.out.println("Enter "+ m.get(1).getClass().getSimpleName() + "ID: ");
                    return m.get(input.nextInt());
                }
            }
        }
        else{
            return m.get(Integer.parseInt(id));
        }
        return null;
    }
}
