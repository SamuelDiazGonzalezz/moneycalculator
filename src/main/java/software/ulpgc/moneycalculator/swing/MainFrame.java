package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final SwingMoneyDialog moneyDialog;
    private final SwingCurrencyDialog currencyDialog;
    private final SwingMoneyDisplay moneyDisplay = new SwingMoneyDisplay();
    private final JButton exchangeButton = new JButton("Convertir");

    public MainFrame(List<Currency> currencies) {
        this.moneyDialog = new SwingMoneyDialog(currencies);
        this.currencyDialog = new SwingCurrencyDialog(currencies);
        this.setTitle("Money Calculator");
        this.setSize(550, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1));
        this.add(moneyDialog);
        this.add(createToPanel());
        this.add(createButtonPanel());
        this.add(moneyDisplay);
    }

    public SwingMoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public SwingCurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    public SwingMoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public void onExchange(Command command) {
        exchangeButton.addActionListener(e -> {
            try {
                command.execute();
            } catch (NumberFormatException ex) {
                showError("La cantidad no es un número válido");
            } catch (IllegalArgumentException ex) {
                showError(ex.getMessage());
            } catch (RuntimeException ex) {
                showError("No se ha podido obtener el tipo de cambio");
            }
        });
    }

    private Component createToPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("A:"));
        panel.add(currencyDialog);
        return panel;
    }

    private Component createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(exchangeButton);
        return panel;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
