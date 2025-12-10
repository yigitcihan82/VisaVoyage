package model.accommodation;

public class Hostel extends Accommodation {

    public Hostel(double nightlyRate, int days) {
        super(nightlyRate, days);
    }

    @Override
    public double calculateCost() {
        // Hostel: Direkt (Gün * Gecelik)
        return nightlyRate * days;
    }
}