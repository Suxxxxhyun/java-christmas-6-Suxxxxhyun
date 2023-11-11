package domain;

import java.util.HashMap;
import java.util.Map;

import static domain.Category.*;

/**
 * packageName    : domain
 * fileName       : Menu
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-11        qkrtn_ulqpbq2       최초 생성
 */
public class Menu {
    private static Map<Category, MenuItem> menuItems = new HashMap<>();

    static {
        menuItems.put(APPETIZER, MenuItem.APPETIZER_1);
        menuItems.put(APPETIZER, MenuItem.APPETIZER_2);
        menuItems.put(APPETIZER, MenuItem.APPETIZER_3);
        menuItems.put(MAIN, MenuItem.MAIN_1);
        menuItems.put(MAIN, MenuItem.MAIN_2);
        menuItems.put(MAIN, MenuItem.MAIN_3);
        menuItems.put(DESSERT, MenuItem.DESSERT_1);
        menuItems.put(DESSERT, MenuItem.DESSERT_2);
        menuItems.put(BEVERAGE, MenuItem.BEVERAGE_1);
        menuItems.put(BEVERAGE, MenuItem.BEVERAGE_2);
        menuItems.put(BEVERAGE, MenuItem.BEVERAGE_3);
    }

    public static MenuItem getMenuItem(Category category) {
        return menuItems.get(category);
    }
}
