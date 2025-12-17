package model.transport;
import java.util.concurrent.ThreadLocalRandom;

public class TrainOption extends TransportOption {
    public TrainOption(String from, String to) {
        // 500 - 1000 arası rastgele fiyat
        super(from, to, ThreadLocalRandom.current().nextDouble(500, 1001));
    }

    @Override
    public double calculateTotalCost() {
        return Math.round(basePrice * 100.0) / 100.0;
    }
}