package domain;

import util.Constants;

public class Promotion {
    private static MenuItem champagne;

    public Promotion(OrderInfo orderInfo){
        champagne = freeChampagnePromotion(orderInfo);
    }

    public MenuItem getChampagne(){
        return champagne;
    }

    private MenuItem freeChampagnePromotion(OrderInfo orderInfo){
        int totalOrderAmount = orderInfo.getTotalOrderPrice();
        if(totalOrderAmount >= Constants.FREE_CHAMPAGNE_THRESHOLD.getValue()){
            return MenuItem.BEVERAGE_3;
        }
        return null;
    }
}
