package ui;

import model.user.Preference;
import model.user.Profile;
import model.user.User;
import service.RecommendationService;
import service.TripPlannerService;
import service.VisaService;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleMenu {
    // LinkedHashMap kullanarak ekleme sırasını koruyoruz
    private Map<Integer, Command> commands;
    private Map<Integer, String> menuLabels;

    public ConsoleMenu() {
        this.commands = new LinkedHashMap<>();
        this.menuLabels = new LinkedHashMap<>();

        initializeEnvironment();
    }

    private void initializeEnvironment() {
        // 1. Ortam ve Kullanıcı Hazırlığı
        Profile profile = new Profile("Ahmet Yılmaz", "ahmet@ornek.com");
        User user = new User(profile);
        user.getPreferences().add(new Preference("Tarih"));
        user.getPreferences().add(new Preference("Doğa"));

        // 2. Servislerin Başlatılması
        TripPlannerService tripService = new TripPlannerService();
        VisaService visaService = new VisaService();
        RecommendationService recService = new RecommendationService();

        // 3. Menü Komutlarının Tanımlanması
        registerCommand(1, "Otomatik Gezi Planla (Trip & Budget)", new CreateTripCommand(tripService, user));
        registerCommand(2, "Vize Başvuru Simülasyonu (Exception Test)", new PlanVisaCommand(visaService));
        registerCommand(3, "Gezilerimi ve Bütçeyi Görüntüle", new ShowBudgetCommand(user));

        // Açılışta küçük bir karşılama önerisi
        System.out.println("Hoşgeldiniz " + profile.getFullName() + "!");
        recService.suggestDestination(user, 45000);
    }

    private void registerCommand(int key, String label, Command command) {
        commands.put(key, command);
        menuLabels.put(key, label);
    }

    public void start() {
        while (true) {
            System.out.println("\n==========================================");
            System.out.println("          SEYAHAT PLANLAYICI v2.0         ");
            System.out.println("==========================================");

            // Menü seçeneklerini yazdır
            for (Map.Entry<Integer, String> entry : menuLabels.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
            System.out.println("0. Çıkış");

            int choice = InputHelper.readInt("Seçiminiz");

            if (choice == 0) {
                System.out.println("Çıkış yapılıyor... İyi günler!");
                break;
            }

            Command command = commands.get(choice);
            if (command != null) {
                command.execute(); // Polimorfizm
            } else {
                System.out.println(">> Geçersiz seçim, lütfen tekrar deneyin.");
            }
        }
    }

    public static void main(String[] args) {
        new ConsoleMenu().start();
    }
}