package model.accommodation;

import java.util.concurrent.ThreadLocalRandom;

public class Hostel extends Accommodation {

    public Hostel(int days) {
        // 500 TL ile 1500 TL arası rastgele gecelik ücret
        super(ThreadLocalRandom.current().nextDouble(500, 1501), days);
    }

    @Override
    public double calculatePrice() {
        return nightlyRate * days;
    }
}
//xdvndxklvsndklnsdlk