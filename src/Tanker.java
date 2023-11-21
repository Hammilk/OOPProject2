public class Tanker extends Heavy{
    Tanker(String id, int weight) {
        super(id, weight);
    }

    @Override
    public double consumption() {
        return super.weight*4;
    }
}
