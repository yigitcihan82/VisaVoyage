package model.trip;

import model.location.Attraction;
import java.time.LocalDateTime;

public class SightseeingActivity extends Activity {
    private Attraction attraction;

    public SightseeingActivity(LocalDateTime start, int durationHours, Attraction attraction) {
        // We send the formatted name and calculated end time to the superclass (Activity)
        super("Visit: " + attraction.getName(), start, start.plusHours(durationHours));
        this.attraction = attraction;
    }

    @Override
    public double calculateCost() {
        // Returns the fee from the associated attraction
        return attraction.getEntryFee();
    }

}