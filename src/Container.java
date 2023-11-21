import java.util.Objects;

public abstract class Container {
    public int id;
    public int weight;
    public String type;

    Container(int id, int weight, String type){
        this.id = id;
        this.weight = weight;
        this.type = type;
    }

    public abstract double consumption();

    boolean equals(Container other){
        return id == other.id && weight == other.weight && Objects.equals(type, other.type);
    }
}
