package ui;

import model.user.User;
import service.WeatherService; // 1. BU SATIRI EKLE

public class RecommendTripCommand implements Command {
    private final User currentUser;
    private final WeatherService weatherService; // 2. BU SATIRI EKLE

    public RecommendTripCommand(User currentUser) {
        this.currentUser = currentUser;
        this.weatherService = new WeatherService(); // 3. BU SATIRI EKLE
    }

    @Override
    public void execute() {
        InputHelper.printSeparator();
        System.out.println("   DÜNYA TURU DANIŞMANI (Bütçe & Hava Durumu)");
        InputHelper.printSeparator();

        double budget = InputHelper.readDouble("Tahmini toplam bütçeniz nedir? (TL)");
        int days = InputHelper.readInt("Kaç gün tatil planlıyorsunuz?");

        double dailyBudget = budget / days;

        System.out.println("\nAnaliz ediliyor...");
        System.out.println("Günlük Harcama Kapasitesi: " + Math.round(dailyBudget) + " TL");
        System.out.println("------------------------------------------");

        // ... (Sınıfın üst kısımları aynı kalacak) ...

        if (dailyBudget >= 13000) {
            System.out.println("ÖNERİ: ELİT DÜNYA ROTALARI");
            System.out.println("* Miami: Plajlar ve Amerikan hayatı");
            System.out.println("* DUBAİ: Lüks ve çöl safarisi. (Vize: GEREKLİ)");
            System.out.println("* MALDİVLER: Tropikal doğa ve lüks plajlar");
            // Kategori ismini gönderiyoruz
            System.out.println(weatherService.getWeatherRecommendation("ELIT"));

        } else if (dailyBudget >= 7000) {
            System.out.println("ÖNERİ: KÜLTÜR VE KONFOR ROTALARI");
            System.out.println("* JAPONYA: Teknoloji ve gelenek.");
            System.out.println("* İTALYA: Sanat ve gastronomi.");
            System.out.println(weatherService.getWeatherRecommendation("KULTUR"));

        } else if (dailyBudget >= 5000) {
            System.out.println("ÖNERİ: TROPİKAL SEYAHATLER");
            System.out.println("* AMAZONLAR: Macera ve doğa.");
            System.out.println("* BALİ: Tropikal huzur.");
            System.out.println(weatherService.getWeatherRecommendation("TROPIKAL"));

        } else if (dailyBudget >= 3000) {
            System.out.println("ÖNERİ: EGZOTİK VE DENGELİ ROTALAR");
            System.out.println("* FAS: Sahra çölünde tur.");
            System.out.println("* MISIR: Piramitler ve Nil.");
            System.out.println(weatherService.getWeatherRecommendation("ELIT")); // Çöl olduğu için Elit (Sıcak) kategorisini kullandık

        } else {
            System.out.println("ÖNERİ: EKONOMİK VE YEREL MACERALAR");
            System.out.println("* BALKANLAR: Doğa ve tarih.");
            System.out.println("* GÜNEY ASYA: Sırt çantalı gezgin rotaları.");
            System.out.println(weatherService.getWeatherRecommendation("EKONOMIK"));
        }
// ...

        InputHelper.printSeparator();
    }
}