package model.transport;

public abstract class TransportOption {
    protected String from;
    protected String to;
    protected double basePrice;

    public TransportOption(String from, String to, double basePrice) {
        this.from = from;
        this.to = to;
        this.basePrice = basePrice;
    }

    public abstract double calculateTotalCost();

    public String getRouteInfo() {
        return from + " -> " + to;
    }
}
