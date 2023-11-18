package domain;

import exception.ExceptionMessage;
import util.Constants;

public class DateInfo {

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
        if(visitedDate < Constants.DECEMBER_START.getValue() || visitedDate > Constants.DECEMBER_END.getValue()){
            ExceptionMessage.VISITED_DATE_NOT_NUMBER.throwException();
        }
    }

    private int convertStringToInt(String readDate) {
        return Integer.parseInt(readDate);
    }
}
