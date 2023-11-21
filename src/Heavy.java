public class Heavy extends Container{
    Heavy(String id, int weight) {
        super(id, weight, "Heavy");
    }

    @Override
    public double consumption() {
        return super.weight*3;
    }
}
