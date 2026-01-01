package service;

import exception.BudgetExceededException;
import model.accommodation.Accommodation;
import model.finance.Budget;
import model.location.Attraction;
import model.transport.TransportOption;
import model.trip.*;
import model.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TripPlannerService {

    public void planCustomTrip(User user, String tripName, double budgetLimit,
                               TransportOption transportOption, Accommodation accommodation) {
        try {
            System.out.println(">> Creating custom trip plan...");
            Budget budget = new Budget(budgetLimit);
            LocalDateTime start = LocalDateTime.now().plusDays(5);
            LocalDateTime end = start.plusDays(7);

            Trip trip = new Trip(tripName, start, end, budget);

            // Transport
            TransportActivity transportActivity = new TransportActivity(start, transportOption);
            budget.addExpense(transportActivity.calculateCost(), "Transport: " + transportOption.getRouteInfo());

            // Accommodation
            AccommodationActivity hotelActivity = new AccommodationActivity(start, end, accommodation);
            budget.addExpense(hotelActivity.calculateCost(), "Accommodation");

            // Daily Plan
            ItineraryDay day1 = new ItineraryDay(LocalDate.from(start));
            day1.addActivity(transportActivity);
            day1.addActivity(hotelActivity);
            trip.addItineraryDay(day1);

            user.addTrip(trip);
            System.out.println("✅ Trip saved. Remaining: " + budget.getRemainingBudget() + " TL");

        } catch (BudgetExceededException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    public void addSightseeingToTrip(Trip trip, Attraction attraction, LocalDateTime startTime, int durationHours) {
        try {
            SightseeingActivity activity = new SightseeingActivity(startTime, durationHours, attraction);

            // Bu satır Trip sınıfındaki getBudget() metoduna ihtiyaç duyar:
            trip.getBudget().addExpense(activity.calculateCost(), "Visit: " + attraction.getName());

            // Doğru günü bul veya oluştur
            LocalDate date = startTime.toLocalDate();
            ItineraryDay targetDay = trip.getItineraryDays().stream()
                    .filter(d -> d.getDate().equals(date))
                    .findFirst()
                    .orElseGet(() -> {
                        ItineraryDay newDay = new ItineraryDay(date);
                        trip.addItineraryDay(newDay);
                        return newDay;
                    });

            targetDay.addActivity(activity);
            System.out.println("✅ Activity added to " + date);
        } catch (BudgetExceededException e) {
            System.err.println("❌ Budget Exceeded for " + attraction.getName());
        }
    }
}