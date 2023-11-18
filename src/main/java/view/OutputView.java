package view;

import domain.DiscountCalculator;
import domain.EventBadge;
import domain.MenuItem;
import domain.OrderInfo;
import util.Constants;
import util.OutputMessage;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Optional;

public class OutputView {
    private static final String SPACE = " ";
    private static final String DASH = "-";
    private static final String NEW_LINE = "\n";
    private static final String QUANTITY = "개";
    private static final String WON = "원";
    private static final String BLANK = "";

    public static void printInfoAfterOrder(EventBadge eventBadge, OrderInfo orderInfo, MenuItem champagne, DiscountCalculator discountCalculator, int totalDiscountPrize){
        printEventPreviewMessage();
        printMenu(orderInfo);
        printTotalOrderPriceBeforeDiscount(orderInfo);
        printPromotionMenu(champagne);
        printBenefitDetails(discountCalculator, champagne);
        printTotalBenefitPrice(totalDiscountPrize);
        printAfterDiscountPaymentPrice(orderInfo, totalDiscountPrize, champagne);
        printEventBadge(eventBadge);
    }

    private static void printEventPreviewMessage(){
        System.out.println(OutputMessage.EVENT_PREVIEW_MESSAGE.getMessage() + NEW_LINE);
    }

    private static void printMenu(OrderInfo orderInfo){
        System.out.println(OutputMessage.ORDER_MENU.getMessage());
        for (Map.Entry<MenuItem, Integer> entry : orderInfo.getOrderInfo().entrySet()) {
            MenuItem menuItem = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(menuItem.getName() + SPACE + quantity + QUANTITY);
        }
        System.out.println();
    }

    private static void printTotalOrderPriceBeforeDiscount(OrderInfo orderInfo){
        System.out.println(OutputMessage.TOTAL_ORDER_PRICE_BEFORE_DISCOUNT.getMessage());
        System.out.println(numberFormat(orderInfo.getTotalOrderPrice()) + NEW_LINE);
    }

    private static String numberFormat(int price){
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String formattedAmount = decimalFormat.format(price);
        return formattedAmount + WON;
    }

    private static void printPromotionMenu(MenuItem champagne){
        System.out.println(OutputMessage.PROMOTION_MENU.getMessage());
        Optional.ofNullable(champagne)
                .map(item -> item.getName() + SPACE + Constants.ONE.getValue() + QUANTITY + NEW_LINE)
                .ifPresentOrElse(System.out::println, () -> System.out.println(OutputMessage.EMPTY.getMessage() + NEW_LINE));
    }

    private static void printBenefitDetails(DiscountCalculator discountCalculator, MenuItem champagne){
        System.out.println(OutputMessage.BENEFIT_DETAILS.getMessage());
        if(isDiscount(discountCalculator) && isPromotion(champagne)){
            System.out.print(OutputMessage.CHRISTMAS_DISCOUNT.getMessage());
            System.out.println(DASH + numberFormat(discountCalculator.getChristmasDiscount()));
            System.out.print(OutputMessage.WEEKDAY_DISCOUNT.getMessage());
            System.out.println(DASH + numberFormat(discountCalculator.getWeekDayDiscount()));
            System.out.print(OutputMessage.SPECIAL_DISCOUNT.getMessage());
            System.out.println(DASH + numberFormat(discountCalculator.getSpecialDiscount()));
            printPromotionEvent(champagne);
            return;
        }
        System.out.println(OutputMessage.EMPTY.getMessage() + NEW_LINE);
    }

    private static boolean isDiscount(DiscountCalculator discountCalculator){
        if(discountCalculator.getChristmasDiscount() == 0 && discountCalculator.getWeekDayDiscount() == 0 && discountCalculator.getSpecialDiscount() == 0){
            return false;
        }
        return true;
    }

    private static boolean isPromotion(MenuItem champagne){
        if(champagne == MenuItem.BEVERAGE_3){
            return true;
        }
        return false;
    }

    private static void printPromotionEvent(MenuItem champagne){
        if(champagne == MenuItem.BEVERAGE_3){
            System.out.print(OutputMessage.PROMOTION_EVENT.getMessage());
            System.out.println(DASH + numberFormat(champagne.getPrice()) + NEW_LINE);
        }
    }

    private static void printTotalBenefitPrice(int totalDiscountPrize){
        System.out.println(OutputMessage.TOTAL_BENEFIT_PRICE.getMessage());
        System.out.println(isTotalDiscountPrizeZero(totalDiscountPrize) + numberFormat(totalDiscountPrize) + NEW_LINE);
    }

    private static String isTotalDiscountPrizeZero(int totalDiscountPrize){
        if(totalDiscountPrize == 0){
            return BLANK;
        }
        return DASH;
    }

    private static void printAfterDiscountPaymentPrice(OrderInfo orderInfo, int totalDiscountPrize, MenuItem champagne){
        System.out.println(OutputMessage.AFTER_DISCOUNT_PAYMENT_PRICE.getMessage());
        int paymentPrice = orderInfo.getTotalOrderPrice() - totalDiscountPrize;
        isNotEmptyChampagne(champagne, paymentPrice);
        System.out.println(numberFormat(paymentPrice) + NEW_LINE);
    }

    private static int isNotEmptyChampagne(MenuItem champagne, int paymentPrice){
        if(champagne == MenuItem.BEVERAGE_3){
            paymentPrice -= champagne.getPrice();
            return paymentPrice;
        }
        return paymentPrice;
    }

    private static void printEventBadge(EventBadge eventBadge){
        System.out.println(OutputMessage.EVENT_BADGE.getMessage());
        Optional.ofNullable(eventBadge)
                .map(item -> item.getName())
                .ifPresentOrElse(System.out::println, () -> System.out.println(OutputMessage.EMPTY.getMessage() + NEW_LINE));
    }
}
