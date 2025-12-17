package model.transport;
import java.util.concurrent.ThreadLocalRandom;

public class BusOption extends TransportOption {
    public BusOption(String from, String to) {
        // 1000 - 2000 arası rastgele fiyat
        super(from, to, ThreadLocalRandom.current().nextDouble(1000, 2001));
    }

    @Override
    public double calculateTotalCost() {
        return Math.round(basePrice * 100.0) / 100.0;
    }
}