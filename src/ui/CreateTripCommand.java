package ui;

import service.TripPlannerService;
import model.user.User;
import java.util.Scanner;

public class CreateTripCommand implements Command {
    private TripPlannerService plannerService;
    private User currentUser;
    private Scanner scanner;

    public CreateTripCommand(TripPlannerService plannerService, User currentUser) {
        this.plannerService = plannerService;
        this.currentUser = currentUser;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.print("Planlanacak bütçe limiti nedir? (TL): ");
        double limit = scanner.nextDouble();

        // Servisi çağırarak işi yaptır
        plannerService.createDemoTrip(currentUser, limit);
    }
}