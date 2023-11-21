public class Heavy extends Container{
    Heavy(int id, int weight) {
        super(id, weight, "Heavy");
    }

    @Override
    public double consumption() {
        return super.weight*3;
    }
}
