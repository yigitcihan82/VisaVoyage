package service;

import exception.BudgetExceededException;
import model.accommodation.Accommodation;
import model.finance.Budget;
import model.transport.TransportOption;
import model.trip.*;
import model.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TripPlannerService {

    // ESKİ DEMO METODU (İstersen durabilir, istersen silebilirsin)
    public void createDemoTrip(User user, double budgetLimit) {
        // ... Eski kodlar ...
    }

    // --- YENİ EKLENEN ESNEK METOT ---
    public void planCustomTrip(User user, String tripName, double budgetLimit,
                               TransportOption transportOption, Accommodation accommodation) {
        try {
            System.out.println(">> Özel gezi planı oluşturuluyor...");

            // 1. Bütçe Kurulumu
            Budget budget = new Budget(budgetLimit);

            // 2. Tarih Ayarları (Basitlik olsun diye bugünden başlatıyoruz)
            LocalDateTime start = LocalDateTime.now().plusDays(5);
            LocalDateTime end = start.plusDays(7); // 1 haftalık tatil

            Trip trip = new Trip(tripName, start, end, budget);

            // 3. Ulaşım Aktivitesini Ekle
            TransportActivity transportActivity = new TransportActivity(start, transportOption);
            // Harcamayı bütçeye yansıt
            budget.addExpense(transportActivity.calculateCost(), "Ulaşım: " + transportOption.getRouteInfo());

            // 4. Konaklama Aktivitesini Ekle
            // Not: Konaklama tüm gezi boyunca sürer
            AccommodationActivity hotelActivity = new AccommodationActivity(start, end, accommodation);
            // Harcamayı bütçeye yansıt
            budget.addExpense(hotelActivity.calculateCost(), "Konaklama Ücreti");

            // 5. Günlük Plana İşle (Basitçe ilk güne ekliyoruz)
            ItineraryDay day1 = new ItineraryDay(LocalDate.from(start));
            day1.addActivity(transportActivity);
            day1.addActivity(hotelActivity);

            trip.addItineraryDay(day1);

            // 6. Kullanıcıya Kaydet
            user.addTrip(trip);

            System.out.println("✅ Gezi başarıyla kaydedildi!");
            System.out.println("Kalan Bütçe: " + budget.getRemainingBudget() + " TL");

        } catch (BudgetExceededException e) {
            System.err.println("❌ HATA: Bütçe yetersiz kaldı! İşlem iptal edildi.");
            System.err.println("   Detay: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Beklenmedik bir hata: " + e.getMessage());
            e.printStackTrace();
        }
    }
}