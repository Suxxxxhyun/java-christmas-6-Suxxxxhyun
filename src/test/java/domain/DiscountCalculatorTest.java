package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : domain
 * fileName       : DiscountCalculatorTest
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-11        qkrtn_ulqpbq2       최초 생성
 */
public class DiscountCalculatorTest {
    @DisplayName("총 주문 금액과 방문날짜에 따라 할인이 올바르게 적용되는지 검증한다.")
    @Test
    void checkDiscount() {
        String readOrder = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderInfo orderInfo = new OrderInfo(readOrder);
        DiscountCalculator discountCalculator = new DiscountCalculator(3);
        discountCalculator.calculateDiscount(orderInfo.getTotalOrderPrice(), 3, orderInfo);

        assertThat(discountCalculator.getTotalDiscountPrice()).isEqualTo(6246);
        assertThat(discountCalculator.getChristmasDiscount()).isEqualTo(1200);
        assertThat(discountCalculator.getWeekDayDiscount()).isEqualTo(4046);
        assertThat(discountCalculator.getWeekendDiscount()).isEqualTo(0);
        assertThat(discountCalculator.getSpecialDiscount()).isEqualTo(1000);
    }
}
