package ui;

import model.trip.Trip;
import model.user.User;

public class ShowBudgetCommand implements Command {
    private User user;

    public ShowBudgetCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        InputHelper.printSeparator();
        System.out.println("       MEVCUT GEZİ VE BÜTÇE DURUMU");
        InputHelper.printSeparator();

        if (user.getTrips().isEmpty()) {
            System.out.println("⚠️ Henüz planlanmış bir gezi bulunmamaktadır.");
            System.out.println("   Lütfen önce '1' numaralı seçenek ile gezi oluşturun.");
            return;
        }

        // Kullanıcının tüm gezilerini listeleyelim
        int count = 1;
        for (Trip trip : user.getTrips()) {
            System.out.println("GEZİ #" + count++);
            System.out.println(trip.exportToText());
            System.out.println("------------------------------------------");
        }
    }
}