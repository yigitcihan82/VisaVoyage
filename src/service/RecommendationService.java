package service;

import model.user.Preference;
import model.user.User;

public class RecommendationService {

    public void suggestDestination(User user, double budgetAmount) {
        System.out.println("\n--- Suggestions---");

        boolean likesHistory = false;
        for (Preference p : user.getPreferences()) {
            /**
             * DÜZELTME: ConsoleMenu'de eklenen "History" etiketi ile uyumlu hale getirildi.
             * Daha önce "Date" arandığı için tarih odaklı öneriler yapılamıyordu.
             */
            if (p.getTag().equalsIgnoreCase("History")) {
                likesHistory = true;
                break;
            }
        }

        if (budgetAmount > 50000 && likesHistory) {
            System.out.println("Suggestion: Roma, Italy (For History and Luxury)");
        } else if (budgetAmount > 30000) {
            System.out.println("Suggestion: Berlin, Germany (For Culture)");
        } else {
            System.out.println("Suggestion: Sofya, Bulgarian (Budget Friendly)");
        }
    }
}