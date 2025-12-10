package model.finance;

public class StaticExchangeRateProvider implements ExchangeRateProvider {
    @Override
    public double convert(double amount, Currency from, Currency to) {
        if (from == to) return amount;

        // Örnek: Her şeyi önce TL'ye çevirip hesaplıyoruz
        double amountInTry = switch (from) {
            case TRY -> amount;
            case USD -> amount * 43.0;
            case EUR -> amount * 50.0;
        };

        return switch (to) {
            case TRY -> amountInTry;
            case USD -> amountInTry / 43.0;
            case EUR -> amountInTry / 50.0;
        };
    }
}