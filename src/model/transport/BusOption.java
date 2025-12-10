package model.transport;

public class BusOption extends TransportOption {

    public BusOption(double basePrice) {
        super(basePrice);
    }

    @Override
    public double calculateCost() {
        // Otobüs: Sabit fiyat
        return basePrice;
    }
}
