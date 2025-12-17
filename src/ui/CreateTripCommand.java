package ui;

import model.accommodation.Accommodation;
import model.accommodation.Apartment;
import model.accommodation.Hostel;
import model.accommodation.Hotel;
import model.transport.BusOption;
import model.transport.FlightOption;
import model.transport.TrainOption;
import model.transport.TransportOption;
import model.user.User;
import service.TripPlannerService;

public class CreateTripCommand implements Command {
    private TripPlannerService plannerService;
    private User currentUser;

    public CreateTripCommand(TripPlannerService plannerService, User currentUser) {
        this.plannerService = plannerService;
        this.currentUser = currentUser;
    }

    @Override
    public void execute() {
        InputHelper.printSeparator();
        System.out.println("   DETAYLI SEYAHAT PLANLAYICI");
        InputHelper.printSeparator();

        String tripName = InputHelper.readString("Geziye bir isim verin (Örn: İtalya Yazı)");
        double budget = InputHelper.readDouble("Toplam Bütçe Limiti (TL)");

        // --- 1. ULAŞIM SEÇİMİ ---
        System.out.println("\n--- ULAŞIM TERCİHİ ---");
        System.out.println("1. Uçak (Hızlı ama pahalı)");
        System.out.println("2. Otobüs (Ekonomik)");
        System.out.println("3. Tren (Manzaralı)");
        int transChoice = InputHelper.readInt("Seçiminiz (1-3)");

        String from = InputHelper.readString("Nereden");
        String to = InputHelper.readString("Nereye");
        double basePrice = InputHelper.readDouble("Bilet Fiyatı");

        TransportOption transportOption = null;

        switch (transChoice) {
            case 1: // Uçak
                double bagFee = InputHelper.readDouble("Bagaj Ücreti");
                double tax = InputHelper.readDouble("Vergi Tutarı");
                transportOption = new FlightOption(from, to, basePrice, bagFee, tax);
                break;
            case 2: // Otobüs
                transportOption = new BusOption(from, to, basePrice);
                break;
            case 3: // Tren
                transportOption = new TrainOption(from, to, basePrice);
                break;
            default:
                System.out.println("Geçersiz seçim, varsayılan olarak Otobüs seçildi.");
                transportOption = new BusOption(from, to, basePrice);
        }

        // --- 2. KONAKLAMA SEÇİMİ ---
        System.out.println("\n--- KONAKLAMA TERCİHİ ---");
        System.out.println("1. Otel (Konforlu, Hizmet bedeli var)");
        System.out.println("2. Apart (Temizlik ücreti var)");
        System.out.println("3. Hostel (En ucuz)");
        int accChoice = InputHelper.readInt("Seçiminiz (1-3)");

        int days = InputHelper.readInt("Kaç gün kalacaksınız?");
        double nightlyRate = InputHelper.readDouble("Gecelik Ücret");

        Accommodation accommodation = null;

        switch (accChoice) {
            case 1: // Hotel
                double serviceFee = InputHelper.readDouble("Otel Hizmet Bedeli");
                accommodation = new Hotel(nightlyRate, days, serviceFee);
                break;
            case 2: // Apart
                double cleaningFee = InputHelper.readDouble("Temizlik Ücreti");
                accommodation = new Apartment(nightlyRate, days, cleaningFee);
                break;
            case 3: // Hostel
                accommodation = new Hostel(nightlyRate, days);
                break;
            default:
                System.out.println("Geçersiz seçim, varsayılan olarak Hostel seçildi.");
                accommodation = new Hostel(nightlyRate, days);
        }

        // --- 3. SERVİSİ ÇAĞIR ---
        System.out.println("\n>> Bilgiler alındı, hesaplama yapılıyor...");
        plannerService.planCustomTrip(currentUser, tripName, budget, transportOption, accommodation);

        InputHelper.printSeparator();
    }
}