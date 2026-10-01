package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.ui.CurrencyDialog;

import javax.swing.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private final JComboBox<Currency> comboBox;

    public SwingCurrencyDialog(List<Currency> currencies) {
        this.comboBox = new JComboBox<>(currencies.toArray(new Currency[0]));
        this.add(comboBox);
    }

    public void select(Currency currency) {
        comboBox.setSelectedItem(currency);
    }

    @Override
    public Currency get() {
        return (Currency) comboBox.getSelectedItem();
    }
}
