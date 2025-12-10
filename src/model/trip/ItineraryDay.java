package model.trip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItineraryDay {
    private LocalDate date;
    private List<Activity> activities;

    public ItineraryDay(LocalDate date) {
        this.date = date;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public double calculateDayCost() {
        double total = 0;
        for (Activity act : activities) {
            total += act.calculateCost();
        }
        return total;
    }

    public LocalDate getDate() { return date; }
    public List<Activity> getActivities() { return activities; }
}