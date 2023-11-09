package domain;

import exception.ExceptionMessage;

/**
 * packageName    : domain
 * fileName       : OrderInfo
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-09        qkrtn_ulqpbq2       최초 생성
 */
public class DateInfo {
    private static final int DECEMBER_START = 1;
    private static final int DECEMBER_END = 31;

    private final int visitedDate;
    public DateInfo(String readDate){
        validate(readDate);
        this.visitedDate = convertStringToInt(readDate);
    }

    public int getVisitedDate(){
        return visitedDate;
    }

    private void validate(String readDate){
        isStringEmpty(readDate);
        isStringDigit(readDate);
        isInRange(readDate);
    }

    private void isStringEmpty(String readDate){
        if(readDate == null || readDate.isBlank()){
            ExceptionMessage.VISITED_DATE_NOT_NUMBER.throwException();
        }
    }

    private void isStringDigit(String readDate){
        if(!readDate.chars().allMatch(Character::isDigit)){
            ExceptionMessage.VISITED_DATE_NOT_NUMBER.throwException();
        }
    }

    private void isInRange(String readDate){
        int visitedDate = convertStringToInt(readDate);
        if(visitedDate < DECEMBER_START || visitedDate > DECEMBER_END){
            ExceptionMessage.VISITED_DATE_NOT_NUMBER.throwException();
        }
    }

    private int convertStringToInt(String readDate) {
        return Integer.parseInt(readDate);
    }
}
