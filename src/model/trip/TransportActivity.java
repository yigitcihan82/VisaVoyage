package model.trip;

import model.transport.TransportOption;
import java.time.LocalDateTime;

public class TransportActivity extends Activity {
    private TransportOption transportOption;

    // Bitiş saati tahmini: Başlangıç + 2 saat (Örnek)
    public TransportActivity(LocalDateTime start, TransportOption transportOption) {
        super("Transportation: " + transportOption.getRouteInfo(), start, start.plusHours(2));
        this.transportOption = transportOption;
    }

    @Override
    public double calculateCost() {

        return transportOption.calculateTotalCost();
    }
}