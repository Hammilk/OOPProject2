import java.util.Objects;

public abstract class Container {
    public int id;
    public int weight;
    public String type;

    Container(int id, int weight){
        this.id = id;
        this.weight = weight;
    }

    public abstract double consumption();

    public boolean equals(Container other){
        return id == other.id && weight == other.weight && Objects.equals(type, other.type);
    }

    @Override
    public String toString() {
        return "Container ID: " + id;
    }

    public int getId() {
        return id;
    }
}
