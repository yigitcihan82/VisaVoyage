package model.trip;

import model.finance.Budget;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Budget budget;
    private List<ItineraryDay> itineraryDays;

    public Trip(String name, LocalDateTime startDate, LocalDateTime endDate, Budget budget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.itineraryDays = new ArrayList<>();
    }

    // --- GETTERS (Hataları çözen kısımlar) ---
    public Budget getBudget() {
        return budget;
    }

    public List<ItineraryDay> getItineraryDays() {
        return itineraryDays;
    }

    public void addItineraryDay(ItineraryDay day) {
        itineraryDays.add(day);
    }

    public String exportToText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trip Name: ").append(name)
                .append("\nDates: ").append(startDate.toLocalDate()).append(" / ").append(endDate.toLocalDate())
                .append("\nRemaining Budget: ").append(budget.getRemainingBudget()).append(" TL");
        return sb.toString();
    }
}