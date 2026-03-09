public final class AirPort extends Location {

    private int nrTerminals;

    public AirPort(String name, int x, int y, int nrTerminals) {
        super(name,x,y);
        this.nrTerminals = nrTerminals;
    }

    public int getNrTerminals() {
        return nrTerminals;
    }

    @Override
    public String toString() {
        return "AirPort: " + super.toString() +
                ", nrTerminals=" + nrTerminals;
    }
}
