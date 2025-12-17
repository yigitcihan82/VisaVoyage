package model.transport;
import java.util.concurrent.ThreadLocalRandom;

public class FlightOption extends TransportOption {
    private double baggageFee = 300.0; // Sabit bagaj ücreti
    private double tax;

    public FlightOption(String from, String to) {
        // 2000 - 5000 arası rastgele fiyat
        super(from, to, ThreadLocalRandom.current().nextDouble(2000, 5001));
        this.tax = this.basePrice * 0.18; // %18 KDV gibi düşünebilirsin
    }

    @Override
    public double calculateTotalCost() {
        double total = basePrice + baggageFee + tax;
        return Math.round(total * 100.0) / 100.0;
    }
}