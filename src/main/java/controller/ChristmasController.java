package controller;

import domain.*;
import exception.ExceptionMessage;
import view.InputView;

import java.time.LocalDate;
import java.util.Map;

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

    private static int visitedDate;
    private static int totalOrderPrice;

    private static OrderInfo customerOrderInfo;
    private static int totalDiscountPrize;
    private static MenuItem champagne;
    private static EventBadge eventBadge;

    public void playChristmas(){
        try {
            orderStart();
            calculateDiscount();
            givePromotion();
            giveEventBadge();
        } catch(NumberFormatException e){
            System.out.println(ExceptionMessage.VISITED_DATE_NOT_NUMBER);
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private void orderStart(){
        DateInfo dateInfo = new DateInfo(InputView.readDate());
        OrderInfo orderInfo = new OrderInfo(InputView.readOrder());
        visitedDate = dateInfo.getVisitedDate();
        totalOrderPrice = orderInfo.getTotalOrderPrice();
        customerOrderInfo = (OrderInfo) orderInfo.getOrderInfo();
    }

    private void calculateDiscount(){
        DiscountCalculator discountCalculator = new DiscountCalculator(visitedDate);
        discountCalculator.calculateDiscount(visitedDate, totalOrderPrice, customerOrderInfo);
        totalDiscountPrize = discountCalculator.getTotalDiscountPrice();
    }

    private void givePromotion(){
        Promotion promotion = new Promotion(customerOrderInfo);
        champagne = promotion.getChampagne();
    }

    private void giveEventBadge(){
        eventBadge = EventBadge.getBadgeByBenefitAmount(totalDiscountPrize);
    }


}
