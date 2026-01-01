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
        System.out.println("       CURRENT TRIP AND BUDGET STATUS");
        InputHelper.printSeparator();

        if (user.getTrips().isEmpty()) {
            System.out.println("⚠️ No trips have been planned yet.");
            System.out.println("   Please create a trip using option '1' first.");
            return;
        }

        int count = 1;
        for (Trip trip : user.getTrips()) {
            System.out.println("TRIP #" + count++);
            System.out.println(trip.exportToText());
            System.out.println("------------------------------------------");
        }
    }
}