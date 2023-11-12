package domain;

public enum MenuItem {
    APPETIZER_1("양송이수프", 6000, Category.APPETIZER),
    APPETIZER_2("타파스", 5500, Category.APPETIZER),
    APPETIZER_3("시저샐러드", 8000, Category.APPETIZER),
    MAIN_1("티본스테이크", 55000, Category.MAIN),
    MAIN_2("바비큐립", 54000, Category.MAIN),
    MAIN_3("해산물파스타", 35000, Category.MAIN),
    MAIN_4("크리스마스파스타", 25000, Category.MAIN),
    DESSERT_1("초코케이크", 15000, Category.DESSERT),
    DESSERT_2("아이스크림", 5000, Category.DESSERT),
    BEVERAGE_1("제로콜라", 3000, Category.BEVERAGE),
    BEVERAGE_2("레드와인", 60000, Category.BEVERAGE),
    BEVERAGE_3("샴페인", 25000, Category.BEVERAGE);

    private final String name;
    private final int price;
    private final Category category;

    MenuItem(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
