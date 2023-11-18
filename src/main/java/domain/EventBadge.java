package domain;

public enum EventBadge {

    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000);

    private final int benefitAmount;
    private final String name;

    EventBadge(String name, int benefitAmount) {
        this.name = name;
        this.benefitAmount = benefitAmount;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }

    public String getName(){
        return name;
    }

    public static EventBadge getBadgeByBenefitAmount(int benefitAmount) {
        for (EventBadge badge : EventBadge.values()) {
            if (benefitAmount >= badge.getBenefitAmount()) {
                return badge;
            }
        }
        return null;
    }
}
