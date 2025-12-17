package model.accommodation;

import java.util.concurrent.ThreadLocalRandom;

public class Apartment extends Accommodation {
    private double cleaningFee; // Temizlik ücreti otomatik atanacak

    public Apartment(int days) {
        // 2000 TL ile 7000 TL arası rastgele gecelik ücret
        super(ThreadLocalRandom.current().nextDouble(2000, 7001), days);
        this.cleaningFee = 300.0; // Otomatik temizlik ücreti
    }

    @Override
    public double calculatePrice() {
        return (nightlyRate * days) + cleaningFee;
    }

    public double getCleaningFee() {
        return cleaningFee;
    }
}
//dvmkdnskdnskvdm