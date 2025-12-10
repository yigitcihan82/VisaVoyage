package model.accommodation;

public class Hotel extends Accommodation {
    private double serviceFee; // Otel hizmet bedeli

    public Hotel(double nightlyRate, int days, double serviceFee) {
        super(nightlyRate, days);
        this.serviceFee = serviceFee;
    }

    @Override
    public double calculateCost() {
        // Otel: (Gün * Gecelik) + Servis ücreti
        return (nightlyRate * days) + serviceFee;
    }
}