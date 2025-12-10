package model.trip;

import model.location.Attraction;
import java.time.LocalDateTime;

public class SightseeingActivity extends Activity {
    private Attraction attraction;

    public SightseeingActivity(LocalDateTime start, LocalDateTime end, Attraction attraction) {
        super("Gezi: " + attraction.getName(), start, end);
        this.attraction = attraction;
    }

    @Override
    public double calculateCost() {
        return attraction.getEntryFee();
    }
}