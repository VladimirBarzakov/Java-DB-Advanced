package application.models.enums;

public enum AgeRestriction {
    MINOR(0),TEEN(1),ADULT(2);

    private int value;
    private AgeRestriction(int i){value = i;}
    public int getValue() { return value; }
}
