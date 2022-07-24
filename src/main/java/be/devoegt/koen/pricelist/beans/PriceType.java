package be.devoegt.koen.pricelist.beans;

public enum PriceType {
    REGULAR("Regular price"),
    MEMBER("Member price"),
    EVENT("Event price");

    public final String label;

    private PriceType(String label) {
        this.label = label;
    }

    public static PriceType valueOfLabel(String label) {
        for (PriceType priceType : values()) {
            if (priceType.label.equals(label)) {
                return priceType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
