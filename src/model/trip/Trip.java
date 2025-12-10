package model.trip;

import model.finance.Budget;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trip implements Schedulable, Exportable {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Budget budget; // B Kişisinden geliyor
    private List<ItineraryDay> itinerary;

    public Trip(String name, LocalDateTime startDate, LocalDateTime endDate, Budget budget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.itinerary = new ArrayList<>();
    }

    public void addItineraryDay(ItineraryDay day) {
        itinerary.add(day);
    }

    public double calculateTotalEstimatedCost() {
        double total = 0;
        for (ItineraryDay day : itinerary) {
            total += day.calculateDayCost();
        }
        return total;
    }

    @Override
    public LocalDateTime getStartTime() { return startDate; }

    @Override
    public LocalDateTime getEndTime() { return endDate; }

    @Override
    public String exportToText() {
        StringBuilder sb = new StringBuilder();
        sb.append("TRIP: ").append(name).append("\n");
        sb.append("Tarih: ").append(startDate).append(" - ").append(endDate).append("\n");
        sb.append("Toplam Tahmini Tutar: ").append(calculateTotalEstimatedCost()).append("\n");
        sb.append("--- GÜNLÜK PLAN ---\n");

        for (ItineraryDay day : itinerary) {
            sb.append(day.getDate()).append(":\n");
            for (Activity act : day.getActivities()) {
                sb.append(" - ").append(act.description)
                        .append(" (").append(act.calculateCost()).append(" TL)\n");
            }
        }
        return sb.toString();
    }
}