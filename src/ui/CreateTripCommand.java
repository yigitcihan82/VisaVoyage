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
    private final TripPlannerService plannerService;
    private final User currentUser;

    public CreateTripCommand(TripPlannerService plannerService, User currentUser) {
        this.plannerService = plannerService;
        this.currentUser = currentUser;
    }

    @Override
    public void execute() {
        InputHelper.printSeparator();
        System.out.println("   DETAYLI SEYAHAT PLANLAYICI");
        InputHelper.printSeparator();

        String tripName = InputHelper.readString("Geziye bir isim verin");
        double budget = InputHelper.readDouble("Toplam Bütçe Limiti (TL)");

        // --- 1. ULAŞIM SEÇİMİ ---
        System.out.println("\n--- ULAŞIM TERCİHİ ---");
        System.out.println("1. Uçak\n2. Otobüs\n3. Tren");
        int transChoice = InputHelper.readInt("Seçiminiz (1-3)");

        String from = InputHelper.readString("Nereden");
        String to = InputHelper.readString("Nereye");

        TransportOption transportOption;

        // Ulaşım sınıfları artık sadece 2 parametre (from, to) alıyor
        switch (transChoice) {
            case 1 -> transportOption = new FlightOption(from, to);
            case 3 -> transportOption = new TrainOption(from, to);
            default -> transportOption = new BusOption(from, to);
        }

        System.out.println("\n------------------------------------------");
        System.out.println("SİSTEMİN BELİRLEDİĞİ BİLET FİYATI: " + transportOption.calculateTotalCost() + " TL");
        System.out.println("------------------------------------------");

        // --- 2. KONAKLAMA SEÇİMİ ---
        System.out.println("\n--- KONAKLAMA TERCİHİ ---");
        System.out.println("1. Otel\n2. Apart\n3. Hostel");
        int accChoice = InputHelper.readInt("Seçiminiz (1-3)");

        int days = InputHelper.readInt("Kaç gün kalacaksınız?");

        Accommodation accommodation;

        // BURASI ÖNEMLİ: Yeni sınıfların sadece 'days' bekliyor
        switch (accChoice) {
            case 1 -> {
                accommodation = new Hotel(days);
                Hotel h = (Hotel) accommodation;
                showAccInfo("OTEL", h.getNightlyRate(), h.getServiceFee(), h.calculatePrice());
            }
            case 2 -> {
                accommodation = new Apartment(days);
                Apartment a = (Apartment) accommodation;
                showAccInfo("APART", a.getNightlyRate(), a.getCleaningFee(), a.calculatePrice());
            }
            default -> {
                accommodation = new Hostel(days);
                showAccInfo("HOSTEL", accommodation.getNightlyRate(), 0, accommodation.calculatePrice());
            }
        }

        System.out.println("\n>> Planlama tamamlanıyor...");
        plannerService.planCustomTrip(currentUser, tripName, budget, transportOption, accommodation);
        InputHelper.printSeparator();
    }

    private void showAccInfo(String tip, double gunluk, double ek, double toplam) {
        System.out.println("\n==========================================");
        System.out.println("KONAKLAMA DETAYLARI (" + tip + ")");
        System.out.println("Gecelik Ücret (Sistem Belirledi): " + Math.round(gunluk) + " TL");
        if (ek > 0) System.out.println("Otomatik Ek Ücret: " + ek + " TL");
        System.out.println("TOPLAM KONAKLAMA MALİYETİ: " + toplam + " TL");
        System.out.println("==========================================\n");
    }
}
//lnsdknslknskdvns