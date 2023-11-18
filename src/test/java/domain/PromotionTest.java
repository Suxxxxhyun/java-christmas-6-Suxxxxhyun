package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionTest {
    @DisplayName("총 주문 금액과 방문날짜에 따라 증정이 올바르게 적용되는지 검증한다.")
    @Test
    void checkDiscount() {
        String readOrder = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderInfo orderInfo = new OrderInfo(readOrder);
        Promotion promotion = new Promotion(orderInfo);
        MenuItem champagne = promotion.getChampagne();

        assertThat(champagne).isEqualTo(MenuItem.BEVERAGE_3);
    }
}
