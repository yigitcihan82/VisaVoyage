package model.transport;

public abstract class TransportOption {
    protected String from;
    protected String to;
    protected double basePrice;

    // Constructor güncellendi: Artık kalkış ve varış yerini de istiyor
    public TransportOption(String from, String to, double basePrice) {
        this.from = from;
        this.to = to;
        this.basePrice = basePrice;
    }

    // Alt sınıflar bunu kendine göre dolduracak
    public abstract double calculateCost();

    // A Kişisinin ihtiyaç duyduğu metot
    public String getRouteInfo() {
        return from + " -> " + to;
    }
}
