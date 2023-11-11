package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : domain
 * fileName       : MenuItem
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-11        qkrtn_ulqpbq2       최초 생성
 */
public enum MenuItem {
    APPETIZER_1("양송이수프", 6000),
    APPETIZER_2("타파스", 5500),
    APPETIZER_3("시저샐러드", 8000),
    MAIN_1("티본스테이크", 55000),
    MAIN_2("바비큐립", 54000),
    MAIN_3("해산물파스타", 35000),
    MAIN_4("크리스마스파스타", 25000),
    DESSERT_1("초코케이크", 15000),
    DESSERT_2("아이스크림", 5000),
    BEVERAGE_1("제로콜라", 3000),
    BEVERAGE_2("레드와인", 60000),
    BEVERAGE_3("샴페인", 25000);

    private final String name;
    private final int price;

    MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
