public class Tanker extends Heavy{
    Tanker(int id, int weight) {
        super(id, weight);
        this.type = "Tanker";
    }

    @Override
    public double consumption() {
        return super.weight*4;
    }
}
