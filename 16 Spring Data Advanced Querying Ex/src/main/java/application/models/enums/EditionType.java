package application.models.enums;

public enum EditionType {
    NORMAL(0), PROMO(1), GOLD(2);

    private int value;
    private EditionType(int i){value =i;}

    public int getValue() {
        return value;
    }
}
