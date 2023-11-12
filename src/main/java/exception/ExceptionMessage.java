package exception;

public enum ExceptionMessage {

    VISITED_DATE_NOT_NUMBER("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_LIMIT_EXCEEDED("주문은 한 번에 최대 20개까지 가능합니다."),
    DUPLICATE_MENU("중복된 메뉴가 있습니다. 다시 입력해 주세요.");

    private static final String ERROR_TAG = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message){
        this.message = ERROR_TAG + message;
    }

    public String getMessage(){
        return message;
    }

    public void throwException(){
        throw new IllegalArgumentException(message);
    }
}
