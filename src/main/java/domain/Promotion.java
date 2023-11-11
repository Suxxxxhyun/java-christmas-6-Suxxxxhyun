package domain;

/**
 * packageName    : domain
 * fileName       : Promotion
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-11        qkrtn_ulqpbq2       최초 생성
 */
public class Promotion {

    private static final int FREE_CHAMPAGNE_THRESHOLD = 120000;
    private static MenuItem champagne;

    public Promotion(OrderInfo orderInfo){
        champagne = freeChampagnePromotion(orderInfo);
    }

    public MenuItem getChampagne(){
        return champagne;
    }

    private MenuItem freeChampagnePromotion(OrderInfo orderInfo){
        int totalOrderAmount = orderInfo.getTotalOrderPrice();
        if(totalOrderAmount >= FREE_CHAMPAGNE_THRESHOLD){
            return MenuItem.BEVERAGE_3;
        }
        return null;
    }
}
