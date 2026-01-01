package model.trip;

import model.accommodation.Accommodation;
import java.time.LocalDateTime;

public class AccommodationActivity extends Activity {
    private Accommodation accommodation;

    public AccommodationActivity(LocalDateTime checkInTime, LocalDateTime checkOutTime, Accommodation accommodation) {
        super("Konaklama", checkInTime, checkOutTime);
        this.accommodation = accommodation;
    }

    @Override
    public double calculateCost() {

        return accommodation.calculatePrice();
    }
}