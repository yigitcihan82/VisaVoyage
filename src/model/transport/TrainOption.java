package model.transport;

public class TrainOption extends TransportOption {

    public TrainOption(String from, String to, double basePrice) {
        super(from, to, basePrice);
    }

    @Override
    public double calculateCost() {
        return basePrice;
    }
}