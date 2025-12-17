package model.accommodation;

public abstract class Accommodation {
    protected double nightlyRate;
    protected int days;

    public Accommodation(double nightlyRate, int days) {
        this.nightlyRate = nightlyRate;
        this.days = days;
    }

    // Alt sınıflar (Hotel, Apartment, Hostel) bu metodu kendi kurgusuna göre dolduracak
    public abstract double calculatePrice();

    // EKLEMEN GEREKEN METOT:
    // Sistem tarafından rastgele belirlenen gecelik ücreti dışarıdan okumamızı sağlar.
    public double getNightlyRate() {
        return nightlyRate;
    }
}
//sdnsdklnskdgnsk