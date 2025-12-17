package model.accommodation;

import java.util.concurrent.ThreadLocalRandom;

public class Hotel extends Accommodation {
    private double serviceFee; // Hizmet bedeli otomatik atanacak

    public Hotel(int days) {
        // 5000 TL ile 15000 TL arası rastgele gecelik ücret
        super(ThreadLocalRandom.current().nextDouble(5000, 15001), days);
        this.serviceFee = 200.0; // Otomatik hizmet bedeli
    }

    @Override
    public double calculatePrice() {
        return (nightlyRate * days) + serviceFee;
    }

    public double getServiceFee() {
        return serviceFee;
    }
}
//zmvskdvsklvslkdvs