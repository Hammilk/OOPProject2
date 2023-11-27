public class Basic extends Container{

    Basic( int id, int weight) {
        super(id, weight);
        this.type = "Basic";
    }

    @Override
    public double consumption() {
        return super.weight * 2.5;
    }
}
