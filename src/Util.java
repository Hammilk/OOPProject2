import java.util.Map;
import java.util.Objects;

public class Util {
    Map<Integer, Object> containerMap;

    Util(Map<Integer, Object> containerMap){
        this.containerMap = containerMap;
    }


    public String displayContainers(String s) {
        StringBuilder sb = new StringBuilder();
        for (Integer key : containerMap.keySet()) {
            Container container = (Container) containerMap.get(key);
            if (Objects.equals(container.type, s)) {
                sb.append("  ");
                sb.append(container);
                sb.append("\n");
            }
        }
        if(sb.isEmpty()){
            return "Empty";
        }

        return sb.toString();
    }

    public String buildString(boolean b){
        StringBuilder sb = new StringBuilder();

        String s1 = displayContainers("Basic");
        String s2 = displayContainers("Heavy");
        String s3 = displayContainers("Refrigerator");
        String s4 = displayContainers("Tanker");

        if(b){
            if (!s1.equals("Empty")) {
                sb.append("\n  Basic Containers: \n");
                sb.append(s1);
            }
            if (!s2.equals("Empty")) {
                sb.append("\n  Heavy Containers: \n");
                sb.append(s2);
            }
            if (!s3.equals("Empty")) {
                sb.append("\n  Refrigerator Containers: \n");
                sb.append(s3);
            }
            if (!s4.equals("Empty")) {
                sb.append("\n  Tanker Containers: \n");
                sb.append(s4);
            }
        }
        else{
            if (!s1.equals("Empty")) {
                sb.append("\n    Basic Containers: \n");
                sb.append("  ");
                sb.append(s1);
            }
            if (!s2.equals("Empty")) {
                sb.append("\n    Heavy Containers: \n");
                sb.append("  ");
                sb.append(s2);
            }
            if (!s3.equals("Empty")) {
                sb.append("\n    Refrigerator Containers: \n");
                sb.append("  ");
                sb.append(s3);
            }
            if (!s4.equals("Empty")) {
                sb.append("\n    Tanker Containers: \n");
                sb.append("  ");
                sb.append(s4);
            }
        }


        return sb.toString();
    }

}
