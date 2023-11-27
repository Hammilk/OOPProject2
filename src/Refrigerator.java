public class Refrigerator extends Heavy{
    Refrigerator(int id, int weight) {
        super(id, weight);
        this.type="Refrigerator";
    }

    @Override
    public double consumption() {
        return 5*super.weight;
    }
}
