package util;

public enum Constants {
    DECEMBER_START(1),
    DECEMBER_END(31),
    INITIAL_DISCOUNT_AMOUNT(1000),

    DAILY_DISCOUNT_INCREASE(100),
    MAX_DISCOUNT_AMOUNT(3400),
    ZERO(0),
    CHRISTMAS(25),
    DECEMBER(12),
    YEAR(2023),
    ONE(1),
    TWENTY(20),
    FREE_CHAMPAGNE_THRESHOLD(120000);

    private final int value;

    Constants(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
