public final class GasStation extends Location {

    private double gasPrice;

    public GasStation(String name, int x, int y, double gasPrice) {
        super(name,x,y);
        this.gasPrice = gasPrice;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    @Override
    public String toString() {
        return "City: " + super.toString() +
                ", gas price=" + gasPrice;
    }
}
