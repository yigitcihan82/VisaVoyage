package model.transport;

public class FlightOption extends TransportOption {
    private double baggageFee;
    private double tax;

    public FlightOption(double basePrice, double baggageFee, double tax) {
        super(basePrice);
        this.baggageFee = baggageFee;
        this.tax = tax;
    }

    @Override
    public double calculateCost() {
        // Uçak: Taban fiyat + Bagaj + Vergi
        return basePrice + baggageFee + tax;
    }
}