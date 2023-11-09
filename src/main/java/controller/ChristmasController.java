package controller;

import domain.DateInfo;
import exception.ExceptionMessage;
import view.InputView;

/**
 * packageName    : controller
 * fileName       : ChristmasController
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-09        qkrtn_ulqpbq2       최초 생성
 */
public class ChristmasController {

    public void playLotto(){
        try {
            orderStart();
        } catch(NumberFormatException e){
            System.out.println(ExceptionMessage.VISITED_DATE_NOT_NUMBER);
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void orderStart(){
        DateInfo dateInfo = new DateInfo(InputView.readDate());
    }
}
