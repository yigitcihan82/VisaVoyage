package model.transport;

public class BusOption extends TransportOption {

    public BusOption(String from, String to, double basePrice) {
        super(from, to, basePrice);
    }

    @Override
    public double calculateTotalCost() {
        return basePrice;
    }
}