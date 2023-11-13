package util;

public enum OutputMessage {

    EVENT_PREVIEW_MESSAGE("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_ORDER_PRICE_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    PROMOTION_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인: "),
    WEEKDAY_DISCOUNT("평일 할인 : "),
    SPECIAL_DISCOUNT("특별 할인 : "),
    PROMOTION_EVENT("증정 이벤트: "),
    TOTAL_BENEFIT_PRICE("<총혜택 금액>"),
    AFTER_DISCOUNT_PAYMENT_PRICE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>"),
    EMPTY("없음");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
