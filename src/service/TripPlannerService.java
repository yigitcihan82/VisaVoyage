package service;

import exception.BudgetExceededException;
import model.finance.Budget;
import model.finance.Currency;
import model.finance.Expense;
import model.finance.ExpenseType;
import model.transport.FlightOption;
import model.trip.Trip;
import model.trip.ItineraryDay;
import model.trip.TransportActivity;
import model.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TripPlannerService {

    // Demo amaçlı hızlıca bir gezi planı oluşturur
    public void createDemoTrip(User user, double budgetLimit) {
        try {
            System.out.println("Otomatik gezi planlanıyor...");

            // 1. Bütçe Oluştur (B Kişisi)
            Budget budget = new Budget(budgetLimit);

            // 2. Trip Oluştur (A Kişisi)
            LocalDateTime start = LocalDateTime.now().plusDays(10);
            LocalDateTime end = start.plusDays(5);
            Trip trip = new Trip("Avrupa Turu", start, end, budget);

            // 3. Ulaşım Ekle (B'nin FlightOption'ı, A'nın Activity'si içinde)
            // Not: TransportOption constructor güncellemene uygun olarak (From, To, Fiyat, Bagaj, Vergi)
            FlightOption flight = new FlightOption("Istanbul", "Paris", 15000, 500, 200);
            TransportActivity flightActivity = new TransportActivity(start, flight);

            // Harcamayı Bütçeye İşle (B Kişisi - Overload kullanımı)
            budget.addExpense(flightActivity.calculateCost(), "Uçak Bileti");

            // 4. Günlük Plan (Itinerary) Ekle
            ItineraryDay day1 = new ItineraryDay(LocalDate.from(start));
            day1.addActivity(flightActivity); // Aktiviteyi güne ekle
            trip.addItineraryDay(day1);

            // 5. Trip'i User'a ekle
            user.addTrip(trip);

            System.out.println("Gezi başarıyla oluşturuldu: " + trip.exportToText());

        } catch (BudgetExceededException e) {
            // C Kişisinin yazdığı Exception'ı burada yakalıyoruz
            System.err.println("HATA: Bütçe yetersiz! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Beklenmedik bir hata: " + e.getMessage());
        }
    }
}