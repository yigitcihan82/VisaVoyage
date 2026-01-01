package ui;

import model.user.User;
import service.WeatherService;

public class RecommendTripCommand implements Command {
    private final User currentUser;
    private final WeatherService weatherService;

    public RecommendTripCommand(User currentUser) {
        this.currentUser = currentUser;
        this.weatherService = new WeatherService();
    }

    @Override
    public void execute() {
        InputHelper.printSeparator();
        System.out.println("   WORLD TOUR ADVISOR (Budget & Weather)");
        InputHelper.printSeparator();

        double budget = InputHelper.readDouble("What is your estimated total budget? (TL)");
        int days = InputHelper.readInt("How many days of vacation are you planning?");

        double dailyBudget = budget / days;

        System.out.println("\nAnalyzing...");
        System.out.println("Daily Spending Capacity: " + Math.round(dailyBudget) + " TL");
        System.out.println("------------------------------------------");

        if (dailyBudget >= 13000) {
            System.out.println("RECOMMENDATION: ELITE WORLD ROUTES");
            System.out.println("* Miami: Beaches and American life");
            System.out.println("* DUBAI: Luxury and desert safari. (Visa: REQUIRED)");
            System.out.println("* MALDIVES: Tropical nature and luxury beaches");
            System.out.println(weatherService.getWeatherRecommendation("ELIT"));

        } else if (dailyBudget >= 7000) {
            System.out.println("RECOMMENDATION: CULTURE AND COMFORT ROUTES");
            System.out.println("* JAPAN: Technology and tradition.");
            System.out.println("* ITALY: Art and gastronomy.");
            System.out.println(weatherService.getWeatherRecommendation("KULTUR"));

        } else if (dailyBudget >= 5000) {
            System.out.println("RECOMMENDATION: TROPICAL TRIPS");
            System.out.println("* AMAZONS: Adventure and nature.");
            System.out.println("* BALI: Tropical peace.");
            System.out.println(weatherService.getWeatherRecommendation("TROPIKAL"));

        } else if (dailyBudget >= 3000) {
            System.out.println("RECOMMENDATION: EXOTIC AND BALANCED ROUTES");
            System.out.println("* MOROCCO: Sahara desert tour.");
            System.out.println("* EGYPT: Pyramids and the Nile.");
            System.out.println(weatherService.getWeatherRecommendation("ELIT"));

        } else {
            System.out.println("RECOMMENDATION: ECONOMIC AND LOCAL ADVENTURES");
            System.out.println("* BALKANS: Nature and history.");
            System.out.println("* SOUTH ASIA: Backpacker routes.");
            System.out.println(weatherService.getWeatherRecommendation("EKONOMIK"));
        }

        InputHelper.printSeparator();
    }
}