package domain;

import util.Constants;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class DiscountCalculator {
    private static int totalDiscountPrice;
    private static int christmasDiscount;
    private static int weekDayDiscount;
    private static int weekendDiscount;
    private static int specialDiscount;

    private static DayOfWeek customerVisitedDay;

    public DiscountCalculator(int visitedDate){
        totalDiscountPrice = Constants.ZERO.getValue();
        christmasDiscount = Constants.ZERO.getValue();
        weekDayDiscount = Constants.ZERO.getValue();
        weekendDiscount = Constants.ZERO.getValue();
        specialDiscount = Constants.ZERO.getValue();
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
        totalDiscountPrice += weekDayDiscount(orderInfo);
        totalDiscountPrice += weekendDiscount(orderInfo);
        totalDiscountPrice += specialDiscountDay(visitedDate);
    }

    private int christmasDiscount(int visitedDate) {
        if(isChristmasDiscountDate(visitedDate)){
            int daysUntilChristmas = visitedDate - 1;
            int discountAmount = Constants.INITIAL_DISCOUNT_AMOUNT.getValue() + daysUntilChristmas * Constants.DAILY_DISCOUNT_INCREASE.getValue();
            christmasDiscount = Math.min(discountAmount, Constants.MAX_DISCOUNT_AMOUNT.getValue());
        }
        return christmasDiscount;
    }

    private boolean isChristmasDiscountDate(int visitedDate){
        if(visitedDate >= Constants.DECEMBER_START.getValue() && visitedDate <= Constants.CHRISTMAS.getValue()){
            return true;
        }
        return false;
    }

    private int weekDayDiscount(OrderInfo orderInfo){
        if (isWeekday() && hasDessertOrder(orderInfo)) {
            weekDayDiscount = getWeekdayDessertDiscount(orderInfo);
        }
        return weekDayDiscount;
    }

    private int weekendDiscount(OrderInfo orderInfo){
        if (isWeekend() && hasMainOrder(orderInfo)) {
            weekendDiscount = getWeekendMainDiscount(orderInfo);
        }
        return weekendDiscount;
    }

    private int specialDiscountDay(int visitedDate){
        if(customerVisitedDay == DayOfWeek.SUNDAY || isChristmas(visitedDate)){
            specialDiscount = Constants.INITIAL_DISCOUNT_AMOUNT.getValue();
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
        LocalDate eventStartDate = LocalDate.of(Constants.YEAR.getValue(), Constants.DECEMBER.getValue(), Constants.DECEMBER_START.getValue());
        LocalDate visitedDateLocal = eventStartDate.plusDays(visitedDate - Constants.ONE.getValue());
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
        return (int) dessertCount * Constants.YEAR.getValue();
    }

    private int getWeekendMainDiscount(OrderInfo orderInfo) {
        Map<MenuItem, Integer> orderInfoMap = orderInfo.getOrderInfo();
        long dessertCount = orderInfoMap.keySet().stream()
                .filter(menuItem -> menuItem.getCategory() == Category.MAIN)
                .count();

        return (int) dessertCount * Constants.YEAR.getValue();
    }
}
