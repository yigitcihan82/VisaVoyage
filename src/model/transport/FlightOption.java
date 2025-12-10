package model.transport;

public class FlightOption extends TransportOption {
    private double baggageFee;
    private double tax;

    public FlightOption(String from, String to, double basePrice, double baggageFee, double tax) {
        // Üst sınıfa from ve to bilgisini gönderiyoruz
        super(from, to, basePrice);
        this.baggageFee = baggageFee;
        this.tax = tax;
    }

    @Override
    public double calculateTotalCost() {
        return basePrice + baggageFee + tax;
    }
}