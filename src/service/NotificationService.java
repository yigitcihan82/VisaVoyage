package service;

public class NotificationService {
    public void sendNotification(String message) {
        // Gerçek hayatta SMS/Email atar, burada konsola basıyoruz
        System.out.println("[BİLDİRİM] 🔔 " + message);
    }
}