public class Refrigerator extends Heavy{
    Refrigerator(String id, int weight) {
        super(id, weight);
    }

    @Override
    public double consumption() {
        return 5*super.weight;
    }
}
