package service;

import java.util.concurrent.ThreadLocalRandom;

public class WeatherService {

    public String getWeatherRecommendation(String category) {
        int temperature;
        String condition;
        String advice;

        // Senin belirlediğin kategorilere göre hava durumu ataması
        switch (category.toUpperCase()) {
            case "ELIT":
                temperature = ThreadLocalRandom.current().nextInt(30, 40);
                condition = "Güneşli ve Çok Sıcak ☀️";
                advice = "Güneş kremi ve ince kıyafetler almayı unutmayın!";
                break;
            case "KULTUR":
                temperature = ThreadLocalRandom.current().nextInt(15, 22);
                condition = "Parçalı Bulutlu ve Ilıman ☁️";
                advice = "Şehir turu için ideal bir hava, hafif bir ceket yeterli.";
                break;
            case "TROPIKAL":
                temperature = ThreadLocalRandom.current().nextInt(28, 35);
                condition = "Sıcak ama Muson Yağmurlu 🌧️🌡️";
                advice = "Hava sıcak ama her an yağmur yağabilir, yanınızda hafif bir yağmurluk bulundurun.";
                break;
            case "EKONOMIK":
                temperature = ThreadLocalRandom.current().nextInt(-5, 10);
                condition = "Oldukça Soğuk ve Rüzgarlı ❄️";
                advice = "Sıkı giyinin, atkı ve bereyi unutmayın!";
                break;
            default:
                temperature = 20;
                condition = "Güneşli";
                advice = "Keyifli yolculuklar!";
        }

        return "\n--- BÖLGESEL HAVA DURUMU ---" +
                "\nTahmini Sıcaklık: " + temperature + "°C" +
                "\nHava Durumu: " + condition +
                "\nAsistan Tavsiyesi: " + advice;
    }
}
//SDVNSKDJPSVJPSDVJPSJVSPD