public class Basic extends Container{

    Basic(int id, int weight) {
        super(id, weight, "Basic");
    }

    @Override
    public double consumption() {
        return super.weight * 2.5;
    }
}
