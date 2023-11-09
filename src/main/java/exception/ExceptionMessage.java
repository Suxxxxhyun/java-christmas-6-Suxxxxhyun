package exception;

/**
 * packageName    : exception
 * fileName       : ExceptionMessage
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-09        qkrtn_ulqpbq2       최초 생성
 */
public enum ExceptionMessage {

    VISITED_DATE_NOT_NUMBER("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

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
