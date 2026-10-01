package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.ui.MoneyDialog;

import javax.swing.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final JTextField amountField = new JTextField("100", 10);
    private final SwingCurrencyDialog currencyDialog;

    public SwingMoneyDialog(List<Currency> currencies) {
        this.currencyDialog = new SwingCurrencyDialog(currencies);
        this.add(new JLabel("Cantidad:"));
        this.add(amountField);
        this.add(currencyDialog);
    }

    public void select(Currency currency) {
        currencyDialog.select(currency);
    }

    @Override
    public Money get() {
        double amount = Double.parseDouble(amountField.getText().trim().replace(",", "."));
        return new Money(amount, currencyDialog.get());
    }
}
