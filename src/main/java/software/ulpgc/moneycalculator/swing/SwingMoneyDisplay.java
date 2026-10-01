package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.ui.MoneyDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {

    public SwingMoneyDisplay() {
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setFont(new Font("SansSerif", Font.BOLD, 22));
    }

    @Override
    public void show(Money money) {
        this.setText(money.toString());
    }
}
