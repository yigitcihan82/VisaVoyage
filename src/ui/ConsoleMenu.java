package ui;

import model.user.Preference;
import model.user.Profile;
import model.user.User;
import service.RecommendationService;
import service.TripPlannerService;
import service.VisaService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleMenu {
    private Map<Integer, Command> commands;
    private Scanner scanner;

    public ConsoleMenu() {
        this.scanner = new Scanner(System.in);
        this.commands = new HashMap<>();

        // --- BAŞLANGIÇ KURULUMLARI ---

        // 1. Örnek Kullanıcı Yarat (A Kişisi)
        Profile profile = new Profile("Ali Veli", "ali@test.com");
        User user = new User(profile);
        user.getPreferences().add(new Preference("Tarih")); // İlgi alanı ekle

        // 2. Servisleri Başlat (D Kişisi)
        TripPlannerService tripService = new TripPlannerService();
        VisaService visaService = new VisaService();
        RecommendationService recService = new RecommendationService();

        // 3. Komutları Eşleştir (Command Pattern)
        commands.put(1, new CreateTripCommand(tripService, user));
        commands.put(2, new PlanVisaCommand(visaService));
        commands.put(3, new ShowBudgetCommand(user));

        // Başlangıçta bir öneri yapalım
        recService.suggestDestination(user, 40000);
    }

    public void start() {
        System.out.println("\n=== SEYAHAT PLANLAYICI ===");
        while (true) {
            System.out.println("\n1. Yeni Gezi Planla (Trip & Budget)");
            System.out.println("2. Vize Başvurusu Yap (Exception Test)");
            System.out.println("3. Bütçe ve Gezi Durumunu Göster");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Güle güle!");
                break;
            }

            Command command = commands.get(choice);
            if (command != null) {
                command.execute(); // Polimorfik Çağrı
            } else {
                System.out.println("Geçersiz seçim!");
            }
        }
    }

    public static void main(String[] args) {
        // Uygulamayı başlat
        ConsoleMenu app = new ConsoleMenu();
        app.start();
    }
}