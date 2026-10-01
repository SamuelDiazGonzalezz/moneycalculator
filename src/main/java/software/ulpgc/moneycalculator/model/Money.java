package software.ulpgc.moneycalculator.model;

public record Money(double amount, Currency currency) {
    public Money {
        if (amount < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", amount, currency.code());
    }
}
