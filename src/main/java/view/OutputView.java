package view;

import domain.DiscountCalculator;
import domain.EventBadge;
import domain.MenuItem;
import domain.OrderInfo;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Optional;

public class OutputView {
    private static final String EVENT_PREVIEW_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String TOTAL_ORDER_PRICE_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    private static final String PROMOTION_MENU = "<증정 메뉴>";
    private static final String BENEFIT_DETAILS = "<혜택 내역>";
    private static final String CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인: ";
    private static final String WEEKDAY_DISCOUNT = "평일 할인 : ";
    private static final String SPECIAL_DISCOUNT = "특별 할인 : ";
    private static final String PROMOTION_EVENT = "증정 이벤트: ";
    private static final String TOTAL_BENEFIT_PRICE = "<총혜택 금액>";
    private static final String AFTER_DISCOUNT_PAYMENT_PRICE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE = "<12월 이벤트 배지>";
    private static final String QUANTITY = "개";
    private static final String WON = "원";
    private static final String EMPTY = "없음";
    private static final int ONE = 1;
    private static final String SPACE = " ";
    private static final String DASH = "-";
    private static final char NEW_LINE = '\n';

    public static void printInfoAfterOrder(EventBadge eventBadge, OrderInfo orderInfo, MenuItem champagne, DiscountCalculator discountCalculator, int totalDiscountPrize){
        printEventPreviewMessage();
        printOrderMenu(orderInfo);
        printTotalOrderPriceBeforeDiscount(orderInfo);
        printPromotionMenu(champagne);
        printBenefitDetails(discountCalculator, champagne);
        printTotalBenefitPrice(totalDiscountPrize);
        printAfterDiscountPaymentPrice(orderInfo, totalDiscountPrize, champagne);
        printEventBadge(eventBadge);
    }

    private static void printEventPreviewMessage(){
        System.out.println(EVENT_PREVIEW_MESSAGE);
    }

    private static void printOrderMenu(OrderInfo orderInfo){
        System.out.println(ORDER_MENU);
        for (Map.Entry<MenuItem, Integer> entry : orderInfo.getOrderInfo().entrySet()) {
            MenuItem menuItem = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(menuItem.getName() + SPACE + quantity + QUANTITY);
        }
        System.out.println();
    }

    private static void printTotalOrderPriceBeforeDiscount(OrderInfo orderInfo){
        System.out.println(TOTAL_ORDER_PRICE_BEFORE_DISCOUNT);
        System.out.println(numberFormat(orderInfo.getTotalOrderPrice()) + NEW_LINE);
    }

    private static String numberFormat(int price){
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String formattedAmount = decimalFormat.format(price);
        return formattedAmount + WON;
    }

    private static void printPromotionMenu(MenuItem champagne){
        System.out.println(PROMOTION_MENU);
        Optional.ofNullable(champagne)
                .map(item -> item.getName() + SPACE + ONE + QUANTITY + NEW_LINE)
                .ifPresentOrElse(System.out::println, () -> System.out.println(EMPTY + NEW_LINE));
    }

    private static void printBenefitDetails(DiscountCalculator discountCalculator, MenuItem champagne){
        System.out.println(BENEFIT_DETAILS);
        if(isDiscount(discountCalculator) && isPromotion(champagne)){
            System.out.print(CHRISTMAS_DISCOUNT);
            System.out.println(DASH + numberFormat(discountCalculator.getChristmasDiscount()));
            System.out.print(WEEKDAY_DISCOUNT);
            System.out.println(DASH + numberFormat(discountCalculator.getWeekDayDiscount()));
            System.out.print(SPECIAL_DISCOUNT);
            System.out.println(DASH + numberFormat(discountCalculator.getSpecialDiscount()));
            printPromotionEvent(champagne);
            return;
        }
        System.out.println(EMPTY + NEW_LINE);
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
            System.out.print(PROMOTION_EVENT);
            System.out.println(DASH + numberFormat(champagne.getPrice()) + NEW_LINE);
        }
    }

    private static void printTotalBenefitPrice(int totalDiscountPrize){
        System.out.println(TOTAL_BENEFIT_PRICE);
        System.out.println(isTotalDiscountPrizeZero(totalDiscountPrize) + numberFormat(totalDiscountPrize) + NEW_LINE);
    }

    private static String isTotalDiscountPrizeZero(int totalDiscountPrize){
        if(totalDiscountPrize == 0){
            return "";
        }
        return DASH;
    }

    private static void printAfterDiscountPaymentPrice(OrderInfo orderInfo, int totalDiscountPrize, MenuItem champagne){
        System.out.println(AFTER_DISCOUNT_PAYMENT_PRICE);
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
        System.out.println(EVENT_BADGE);
        Optional.ofNullable(eventBadge)
                .map(item -> item.getName())
                .ifPresentOrElse(System.out::println, () -> System.out.println(EMPTY + NEW_LINE));
    }
}
