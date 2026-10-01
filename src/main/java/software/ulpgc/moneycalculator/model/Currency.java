package software.ulpgc.moneycalculator.model;

public record Currency(String code, String country) {
    @Override
    public String toString() {
        return code + " - " + country;
    }
}
