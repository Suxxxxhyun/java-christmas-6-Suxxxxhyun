package domain;

import exception.ExceptionMessage;

import java.util.*;
import java.util.stream.Collectors;


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
public class OrderInfo {

    private static final int ZERO = 0;

    private Map<MenuItem, Integer> orderInfo;

    private int totalCount;
    private int totalOrderPrice;

    public OrderInfo(String readOrder){
        validate(readOrder);
        init();
        processOrder(readOrder);
        checkExceedQuantity();
        checkOnlyBeverage();
    }

    public Map<MenuItem, Integer> getOrderInfo() {
        return orderInfo;
    }

    public int getTotalCount(){
        return totalCount;
    }

    public int getTotalOrderPrice(){
        return totalOrderPrice;
    }

    private void validate(String readOrder){
        checkNotEmpty(readOrder);
        checkValidFormat(readOrder);
    }

    private void init() {
        totalCount = ZERO;
        totalOrderPrice = ZERO;
        orderInfo = new HashMap<>();
    }

    private void processOrder(String readOrder) {
        String[] orders = readOrder.split(",");
        for (String order : orders) {
            String[] parts = order.split("-");
            String menuName = parts[0].trim();
            int quantity = convertStringToInt(parts[1].trim());

            MenuItem menuItem = findMenuItem(menuName);
            updateOrderInfo(menuItem, quantity);
        }
    }

    private void checkOnlyBeverage() {
        long beverageCount = orderInfo.keySet().stream()
                .filter(menuItem -> menuItem.name().startsWith("BEVERAGE"))
                .count();

        if (beverageCount > 0 && orderInfo.size() == beverageCount) {
            ExceptionMessage.INVALID_ORDER.throwException();
        }
    }

    private void checkExceedQuantity() {
        if(totalCount > 20){
            ExceptionMessage.INVALID_ORDER.throwException();
        }
    }


    private void checkNotEmpty(String readOrder){
        if (readOrder == null || readOrder.isBlank()) {
            ExceptionMessage.INVALID_ORDER.throwException();
        }
    }

    private void checkValidFormat(String readOrder){
        String orderRegex = "([a-zA-Z가-힣]+-[1-9]+)";
        String[] orders = readOrder.split(",");

        for (String order : orders) {
            String trimmedOrder = order.trim();
            if (!trimmedOrder.matches(orderRegex)) {
                ExceptionMessage.INVALID_ORDER.throwException();
            }
        }
    }

    private MenuItem findMenuItem(String menuName) {
        for (MenuItem item : MenuItem.values()) {
            if (item.getName().equalsIgnoreCase(menuName)) {
                return item;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
    }

    private void updateOrderInfo(MenuItem menuItem, int quantity) {
        checkDuplicateMenu(menuItem);
        orderInfo.put(menuItem, quantity);
        totalCount += quantity;
        totalOrderPrice += (menuItem.getPrice() * quantity);
    }

    private void checkDuplicateMenu(MenuItem menuItem){
        if (orderInfo.containsKey(menuItem)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private int convertStringToInt(String quantityStr) {
        return Integer.parseInt(quantityStr);
    }

}
