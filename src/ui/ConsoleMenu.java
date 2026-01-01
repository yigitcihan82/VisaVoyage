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

        // 1. Initialize User Profile
        Profile profile = new Profile("John Doe", "john@example.com");
        User user = new User(profile);
        user.getPreferences().add(new Preference("History"));

        // 2. Initialize Services
        TripPlannerService tripService = new TripPlannerService();
        VisaService visaService = new VisaService();
        RecommendationService recService = new RecommendationService();

        // 3. Map Commands (Command Pattern)
        commands.put(1, new CreateTripCommand(tripService, user));
        commands.put(2, new PlanVisaCommand(visaService));
        commands.put(3, new ShowBudgetCommand(user));
        commands.put(4, new RecommendTripCommand(user)); // Integrated Recommendation & Weather Service
    }

    public void start() {
        System.out.println("\n=== WORLD TRAVEL PLANNER ===");
        while (true) {
            System.out.println("\n1. Plan New Trip (Transport & Accommodation)");
            System.out.println("2. Apply for Visa (Document Simulation)");
            System.out.println("3. Show My Trips and Budget Status");
            System.out.println("4. Get Destination Recommendations & Weather");
            System.out.println("0. Exit");
            System.out.print("Your Selection: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) {
                    System.out.println("Safe travels! Goodbye.");
                    break;
                }

                Command command = commands.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        new ConsoleMenu().start();
    }
}