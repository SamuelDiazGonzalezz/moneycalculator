package software.ulpgc.moneycalculator.application;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Currency> currencies = new WebService.CurrencyLoader().loadAll();
        ExchangeRate exchangeRate = new WebService.ExchangeRateLoader().load(currencies.get(0), currencies.get(1));
        System.out.println(exchangeRate);
    }
}
