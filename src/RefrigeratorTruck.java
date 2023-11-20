public class RefrigeratorTruck extends Heavy{
    RefrigeratorTruck(int id, int weight) {
        super(id, weight);
    }

    @Override
    public double consumption() {
        return 5*super.weight;
    }
}
