package model.transport;

public class TrainOption extends TransportOption {

    public TrainOption(double basePrice) {
        super(basePrice);
    }

    @Override
    public double calculateCost() {
        // Tren: Sabit fiyat (İstersen buraya öğrenci indirimi vs eklersin)
        return basePrice;
    }
}