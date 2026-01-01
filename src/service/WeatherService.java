package service;

import java.util.concurrent.ThreadLocalRandom;

public class WeatherService {

    public String getWeatherRecommendation(String category) {
        int temperature;
        String condition;
        String advice;

        // Weather assignment based on the categories you defined
        switch (category.toUpperCase()) {
            case "ELIT":
                temperature = ThreadLocalRandom.current().nextInt(30, 40);
                condition = "Sunny and Very Hot ☀️";
                advice = "Don't forget to pack sunscreen and light clothing!";
                break;
            case "KULTUR":
                temperature = ThreadLocalRandom.current().nextInt(15, 22);
                condition = "Partly Cloudy and Mild ☁️";
                advice = "Ideal weather for a city tour, a light jacket will suffice.";
                break;
            case "TROPIKAL":
                temperature = ThreadLocalRandom.current().nextInt(28, 35);
                condition = "Hot but Monsoon Rainy 🌧️🌡️";
                advice = "It's hot but it could rain at any moment, keep a light raincoat with you.";
                break;
            case "EKONOMIK":
                temperature = ThreadLocalRandom.current().nextInt(-5, 10);
                condition = "Quite Cold and Windy ❄️";
                advice = "Dress warmly, don't forget your scarf and beanie!";
                break;
            default:
                temperature = 20;
                condition = "Sunny";
                advice = "Have a pleasant journey!";
        }

        return "\n--- REGIONAL WEATHER ---" +
                "\nEstimated Temperature: " + temperature + "°C" +
                "\nCondition: " + condition +
                "\nAssistant Advice: " + advice;
    }
}