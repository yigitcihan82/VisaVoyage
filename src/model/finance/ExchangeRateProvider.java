package model.finance;

public interface ExchangeRateProvider {
    double convert(double amount, Currency from, Currency to);
}