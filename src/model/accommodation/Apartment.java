package model.accommodation;

public class Apartment extends Accommodation {
    private double cleaningFee; // Temizlik ücreti

    public Apartment(double nightlyRate, int days, double cleaningFee) {
        super(nightlyRate, days);
        this.cleaningFee = cleaningFee;
    }

    @Override
    public double calculateCost() {
        // Apart: (Gün * Gecelik) + Temizlik ücreti
        return (nightlyRate * days) + cleaningFee;
    }
}