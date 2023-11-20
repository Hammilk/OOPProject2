public interface ITruck {
    boolean goTo(Terminal p);
    void reFuel(double newFuel);

    boolean load(Container cont);

    boolean unLoad(Container cont);

}
