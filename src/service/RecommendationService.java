package service;

import model.user.Preference;
import model.user.User;

public class RecommendationService {

    public void suggestDestination(User user, double budgetAmount) {
        System.out.println("\n--- ÖNERİLER ---");

        // Basit bir mantık: Bütçe ve İlgi alanına göre öneri
        boolean likesHistory = false;
        for (Preference p : user.getPreferences()) {
            if (p.getTag().equalsIgnoreCase("Tarih")) likesHistory = true;
        }

        if (budgetAmount > 50000 && likesHistory) {
            System.out.println("Öneri: Roma, İtalya (Tarih ve Lüks)");
        } else if (budgetAmount > 30000) {
            System.out.println("Öneri: Berlin, Almanya (Kültür)");
        } else {
            System.out.println("Öneri: Sofya, Bulgaristan (Bütçe Dostu)");
        }
    }
}