package domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class DiscountCalculator {
    private static final int INITIAL_DISCOUNT_AMOUNT = 1000;
    private static final int DAILY_DISCOUNT_INCREASE = 100;
    private static final int MAX_DISCOUNT_AMOUNT = 3400;
    private static final int ZERO = 0;
    private static final int DECEMBER_START = 1;
    private static final int CHRISTMAS = 25;
    private static final int DECEMBER = 12;
    private static final int YEAR = 2023;
    private static final int ONE = 1;
    private static int totalDiscountPrice;
    private static int christmasDiscount;
    private static int weekDayDiscount;
    private static int weekendDiscount;
    private static int specialDiscount;

    private static DayOfWeek customerVisitedDay;

    public DiscountCalculator(int visitedDate){
        totalDiscountPrice = ZERO;
        christmasDiscount = ZERO;
        weekDayDiscount = ZERO;
        weekendDiscount = ZERO;
        specialDiscount = ZERO;
        customerVisitedDay = calculateDayOfWeek(visitedDate);
    }

    public int getTotalDiscountPrice(){
        return totalDiscountPrice;
    }

    public int getChristmasDiscount(){
        return christmasDiscount;
    }

    public int getWeekDayDiscount(){
        return weekDayDiscount;
    }

    public int getWeekendDiscount(){
        return weekendDiscount;
    }

    public int getSpecialDiscount(){
        return specialDiscount;
    }

    public void calculateDiscount(int totalOrderPrice, int visitedDate, OrderInfo orderInfo) {
        totalDiscountPrice += christmasDiscount(visitedDate);
        System.out.println("크리스마스 데이 할인 = " + totalDiscountPrice);
        totalDiscountPrice += weekDayDiscount(visitedDate, orderInfo);
        System.out.println("평일 할인 = " + totalDiscountPrice);
        totalDiscountPrice += weekendDiscount(visitedDate, orderInfo);
        System.out.println("주말 할인 = " + totalDiscountPrice);
        totalDiscountPrice += specialDiscountDay(visitedDate);
        System.out.println("특별 할인 = " + totalDiscountPrice);
    }

    private int christmasDiscount(int visitedDate) {
        if(isChristmasDiscountDate(visitedDate)){
            int daysUntilChristmas = visitedDate - 1;
            int discountAmount = INITIAL_DISCOUNT_AMOUNT + daysUntilChristmas * DAILY_DISCOUNT_INCREASE;
            christmasDiscount = Math.min(discountAmount, MAX_DISCOUNT_AMOUNT);
        }
        return christmasDiscount;
    }

    private boolean isChristmasDiscountDate(int visitedDate){
        if(visitedDate >= DECEMBER_START && visitedDate <= CHRISTMAS){
            return true;
        }
        return false;
    }

    private int weekDayDiscount(int visitedDate, OrderInfo orderInfo){
        if (isWeekday() && hasDessertOrder(orderInfo)) {
            weekDayDiscount = getWeekdayDessertDiscount(orderInfo);
        }
        return weekDayDiscount;
    }

    private int weekendDiscount(int visitedDate, OrderInfo orderInfo){
        if (isWeekend() && hasMainOrder(orderInfo)) {
            weekendDiscount = getWeekendMainDiscount(orderInfo);
        }
        return weekendDiscount;
    }

    private int specialDiscountDay(int visitedDate){
        if(customerVisitedDay == DayOfWeek.SUNDAY || isChristmas(visitedDate)){
            specialDiscount = INITIAL_DISCOUNT_AMOUNT;
        };
        return specialDiscount;
    }

    private boolean isChristmas(int visitedDate){
        return visitedDate == 25;
    }

    private boolean isWeekday() {
        return customerVisitedDay != DayOfWeek.SATURDAY && customerVisitedDay != DayOfWeek.FRIDAY;
    }

    private boolean isWeekend(){
        return customerVisitedDay == DayOfWeek.SATURDAY || customerVisitedDay == DayOfWeek.FRIDAY;
    }

    private DayOfWeek calculateDayOfWeek(int visitedDate) {
        LocalDate eventStartDate = LocalDate.of(YEAR, DECEMBER, DECEMBER_START);
        LocalDate visitedDateLocal = eventStartDate.plusDays(visitedDate - ONE);
        return visitedDateLocal.getDayOfWeek();
    }

    private boolean hasDessertOrder(OrderInfo orderInfo) {
        Map<MenuItem, Integer> orderInfoMap = orderInfo.getOrderInfo();

        return orderInfoMap.keySet().stream()
                .anyMatch(menuItem -> menuItem.getCategory() == Category.DESSERT);
    }

    private boolean hasMainOrder(OrderInfo orderInfo){
        Map<MenuItem, Integer> orderInfoMap = orderInfo.getOrderInfo();

        return orderInfoMap.keySet().stream()
                .anyMatch(menuItem -> menuItem.getCategory() == Category.MAIN);
    }

    private int getWeekdayDessertDiscount(OrderInfo orderInfo) {
        Map<MenuItem, Integer> orderInfoMap = orderInfo.getOrderInfo();
        long dessertCount = orderInfoMap.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == Category.DESSERT)
                .mapToLong(Map.Entry::getValue)
                .sum();
        return (int) dessertCount * YEAR;
    }

    private int getWeekendMainDiscount(OrderInfo orderInfo) {
        Map<MenuItem, Integer> orderInfoMap = orderInfo.getOrderInfo();
        long dessertCount = orderInfoMap.keySet().stream()
                .filter(menuItem -> menuItem.getCategory() == Category.MAIN)
                .count();

        return (int) dessertCount * YEAR;
    }
}
