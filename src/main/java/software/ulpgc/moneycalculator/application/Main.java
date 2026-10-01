package software.ulpgc.moneycalculator.application;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.control.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.swing.MainFrame;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Currency> currencies = new WebService.CurrencyLoader().loadAll();
        if (currencies.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se han podido cargar las monedas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MainFrame frame = new MainFrame(currencies);
        frame.getMoneyDialog().select(find(currencies, "EUR"));
        frame.getCurrencyDialog().select(find(currencies, "USD"));

        Command command = new ExchangeMoneyCommand(
                frame.getMoneyDialog(),
                frame.getCurrencyDialog(),
                new WebService.ExchangeRateLoader(),
                frame.getMoneyDisplay()
        );
        frame.onExchange(command);
        frame.setVisible(true);
    }

    private static Currency find(List<Currency> currencies, String code) {
        return currencies.stream()
                .filter(c -> c.code().equals(code))
                .findFirst()
                .orElse(currencies.get(0));
    }
}
