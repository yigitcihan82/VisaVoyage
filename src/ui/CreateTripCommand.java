package ui;

import model.accommodation.Accommodation;
import model.accommodation.Apartment;
import model.accommodation.Hostel;
import model.accommodation.Hotel;
import model.location.Attraction;
import model.transport.BusOption;
import model.transport.FlightOption;
import model.transport.TrainOption;
import model.transport.TransportOption;
import model.trip.Trip;
import model.user.User;
import service.TripPlannerService;

import java.time.LocalDateTime;

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
        System.out.println("   DETAILED TRIP PLANNER");
        InputHelper.printSeparator();

        String tripName = InputHelper.readString("Enter a name for your trip");
        double budget = InputHelper.readDouble("Total Budget Limit (TL)");

        // --- 1. TRANSPORTATION ---
        System.out.println("\n--- TRANSPORTATION PREFERENCE ---");
        System.out.println("1. Flight\n2. Bus\n3. Train");
        int transChoice = InputHelper.readInt("Your choice (1-3)");

        String from = InputHelper.readString("Departure City");
        String to = InputHelper.readString("Destination City");

        TransportOption transportOption = switch (transChoice) {
            case 1 -> new FlightOption(from, to);
            case 2 -> new BusOption(from, to);
            default -> new TrainOption(from, to);
        };

        // --- 2. ACCOMMODATION ---
        System.out.println("\n--- ACCOMMODATION TYPE ---");
        System.out.println("1. Hotel\n2. Apartment\n3. Hostel");
        int accChoice = InputHelper.readInt("Your choice (1-3)");
        int days = 7; // Default 1 week

        Accommodation accommodation;
        /**
         * DÜZELTME: Doğrudan casting (zorunlu dönüştürme) yerine instanceof kontrolü eklendi.
         * Bu sayede yanlış tip eşleşmelerinde programın çökmesi (ClassCastException) engellendi.
         */
        switch (accChoice) {
            case 1 -> {
                accommodation = new Hotel(days);
                double serviceFee = (accommodation instanceof Hotel h) ? h.getServiceFee() : 0;
                showAccInfo("HOTEL", accommodation.getNightlyRate(), serviceFee, accommodation.calculatePrice());
            }
            case 2 -> {
                accommodation = new Apartment(days);
                double cleaningFee = (accommodation instanceof Apartment a) ? a.getCleaningFee() : 0;
                showAccInfo("APARTMENT", accommodation.getNightlyRate(), cleaningFee, accommodation.calculatePrice());
            }
            default -> {
                accommodation = new Hostel(days);
                showAccInfo("HOSTEL", accommodation.getNightlyRate(), 0, accommodation.calculatePrice());
            }
        }

        // --- 3. CREATE BASE TRIP ---
        System.out.println("\n>> Finalizing base trip plan...");
        plannerService.planCustomTrip(currentUser, tripName, budget, transportOption, accommodation);

        // --- 4. OPTIONAL: ADD SIGHTSEEING ACTIVITY ---
        addOptionalActivities();

        InputHelper.printSeparator();
    }

    private void addOptionalActivities() {
        String choice = InputHelper.readString("\nWould you like to add a Sightseeing Activity? (Y/N)");
        if (choice.equalsIgnoreCase("Y")) {
            String placeName = InputHelper.readString("Name of the attraction (e.g., Eiffel Tower)");
            String description = InputHelper.readString("Brief description");
            double fee = InputHelper.readDouble("Entry Fee (TL)");

            Attraction attraction = new Attraction(placeName, description, fee);

            if (!currentUser.getTrips().isEmpty()) {
                Trip lastTrip = currentUser.getTrips().get(currentUser.getTrips().size() - 1);
                plannerService.addSightseeingToTrip(lastTrip, attraction, LocalDateTime.now().plusDays(6), 4);
            }
        }
    }

    private void showAccInfo(String type, double nightly, double extra, double total) {
        System.out.println("\n==========================================");
        System.out.println("ACCOMMODATION DETAILS (" + type + ")");
        System.out.println("Nightly Rate: " + Math.round(nightly) + " TL");
        if (extra > 0) System.out.println("Extra Fees: " + extra + " TL");
        System.out.println("TOTAL COST: " + total + " TL");
        System.out.println("==========================================");
    }
}