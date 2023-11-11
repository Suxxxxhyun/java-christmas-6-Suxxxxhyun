package domain;

/**
 * packageName    : domain
 * fileName       : Category
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-11        qkrtn_ulqpbq2       최초 생성
 */
public enum Category {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");

    private final String category;

    Category(String categoryName) {
        this.category = categoryName;
    }

    public String getCategory() {
        return category;
    }
}
