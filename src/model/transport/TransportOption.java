package model.transport;

public abstract class TransportOption {
    protected double basePrice;

    public TransportOption(double basePrice) {
        this.basePrice = basePrice;
    }

    // Alt sınıflar bunu kendine göre dolduracak
    public abstract double calculateCost();
}