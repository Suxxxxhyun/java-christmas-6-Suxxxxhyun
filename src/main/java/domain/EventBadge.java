package domain;

/**
 * packageName    : domain
 * fileName       : EventBadge
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-11        qkrtn_ulqpbq2       최초 생성
 */
public enum EventBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

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
