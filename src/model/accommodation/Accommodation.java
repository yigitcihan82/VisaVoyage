package model.accommodation;

public abstract class Accommodation {
    protected double nightlyRate;
    protected int days;

    public Accommodation(double nightlyRate, int days) {
        this.nightlyRate = nightlyRate;
        this.days = days;
    }

    // Listede istediğin calculate metodu
    public abstract double calculateCost();
}
