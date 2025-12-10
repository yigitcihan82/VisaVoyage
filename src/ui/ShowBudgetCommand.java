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
        if (user.getTrips().isEmpty()) {
            System.out.println("Henüz bir gezi planlanmamış.");
            return;
        }

        // Son eklenen geziyi gösterelim
        Trip lastTrip = user.getTrips().get(user.getTrips().size() - 1);
        System.out.println(lastTrip.exportToText());
    }
}