package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.EventBadge.SANTA;
import static domain.EventBadge.getBadgeByBenefitAmount;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : domain
 * fileName       : EventBadgeTest
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-11        qkrtn_ulqpbq2       최초 생성
 */
public class EventBadgeTest {
    @DisplayName("총 주문 금액과 방문날짜에 따라 뱃지가 올바르게 적용되는지 검증한다.")
    @Test
    void checkDiscount() {
        String readOrder = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderInfo orderInfo = new OrderInfo(readOrder);
        DiscountCalculator discountCalculator = new DiscountCalculator(3);
        discountCalculator.calculateDiscount(orderInfo.getTotalOrderPrice(), 3, orderInfo);
        Promotion promotion = new Promotion(orderInfo);
        int totalDiscountPrice = discountCalculator.getTotalDiscountPrice() + getPromotionBenefit(promotion.getChampagne());

        EventBadge eventBadge = getBadgeByBenefitAmount(totalDiscountPrice);
        assertThat(totalDiscountPrice).isEqualTo(31246);
        assertThat(eventBadge).isEqualTo(SANTA);
    }

    private int getPromotionBenefit(MenuItem menuItem){
        if(menuItem == MenuItem.BEVERAGE_3){
            return MenuItem.BEVERAGE_3.getPrice();
        }
        return 0;
    }
}
