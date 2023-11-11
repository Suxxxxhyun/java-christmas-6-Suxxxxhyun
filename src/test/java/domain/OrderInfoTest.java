package domain;

import exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : domain
 * fileName       : OrderInfoTest
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-09        qkrtn_ulqpbq2       최초 생성
 */
public class OrderInfoTest {

    @DisplayName("주문메뉴를 올바르게 입력했는지 검증합니다. - 정상으로 주문메뉴를 입력한 경우를 검증합니다.")
    @Test
    void enterMenu() {
        String readOrder = "해산물파스타-2,레드와인-1,초코케이크-1";
        OrderInfo orderInfo = new OrderInfo(readOrder);
        Map<MenuItem, Integer> orderInfoMap = orderInfo.getOrderInfo();

        assertThat(orderInfoMap)
                .containsEntry(MenuItem.MAIN_3, 2)
                .containsEntry(MenuItem.BEVERAGE_2, 1)
                .containsEntry(MenuItem.DESSERT_1, 1);

        assertThat(orderInfo.getTotalCount()).isEqualTo(4);
        assertThat(orderInfo.getTotalOrderPrice()).isEqualTo(145000);
    }
    @DisplayName("주문메뉴와 개수를 올바르게 입력했는지 검증합니다. - 띄어쓰기만 입력한 경우, 예외를 발생시킵니다.")
    @Test
    void enterSpace() {
        String readOrder = "   ";

        assertThatThrownBy(() -> {
            new OrderInfo(readOrder);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("주문메뉴와 개수를 올바르게 입력했는지 검증합니다. - 올바르게 형식을 유지하지 않은 경우, 예외를 발생시킵니다.")
    @Test
    void enterNotFormat(){
        String readOrder = "해산물파스타-2,레드와인/1,초코케이크1";

        assertThatThrownBy(() -> {
            new OrderInfo(readOrder);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("주문메뉴와 개수를 올바르게 입력했는지 검증합니다. - 올바르게 형식을 유지하지 않은 경우, 예외를 발생시킵니다.")
    @Test
    void enterNotFormat2(){
        String readOrder = "해산물파스타-0,레드와인--1";

        assertThatThrownBy(() -> {
            new OrderInfo(readOrder);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("주문메뉴와 개수를 올바르게 입력했는지 검증합니다. - 메뉴에 없는 메뉴를 주문한 경우, 예외를 발생시킵니다.")
    @Test
    void enterNotExistMenu(){
        String readOrder = "제미제미-3,레드와인-1,초코케이크1";

        assertThatThrownBy(() -> {
            new OrderInfo(readOrder);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("주문메뉴와 개수를 올바르게 입력했는지 검증합니다. - 중복메뉴를 주문한 경우, 예외를 발생시킵니다.")
    @Test
    void enterDuplicateMenu(){
        String readOrder = "레드와인-1,레드와인-1,초코케이크1";

        assertThatThrownBy(() -> {
            new OrderInfo(readOrder);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("주문메뉴와 개수를 올바르게 입력했는지 검증합니다. 20개가 넘는 경우 예외를 발생시킵니다.")
    @Test
    void enterExceedQuantity(){
        String readOrder = "레드와인-100,레드와인-1,초코케이크1";

        assertThatThrownBy(() -> {
            new OrderInfo(readOrder);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("주문메뉴와 개수를 올바르게 입력했는지 검증합니다. - 음료만 입력한 경우, 예외를 발생시킵니다.")
    @Test
    void enterOnlyBeverage(){
        String readOrder = "제로콜라-1";

        assertThatThrownBy(() -> {
            new OrderInfo(readOrder);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_ORDER.getMessage());
    }
}
