package domain;

import exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DateInfoTest {
    @DisplayName("방문날짜를 올바르게 입력했는지 검증합니다. - 정상으로 날짜를 입력한 경우를 검증합니다.")
    @Test
    void enterNormalAmount() {
        String readDate = "12";
        DateInfo dateInfo = new DateInfo(readDate);
        assertThat(dateInfo.getVisitedDate()).isEqualTo(12);
    }

    @DisplayName("방문날짜를 올바르게 입력했는지 검증합니다. - 문자만 입력한 경우, 예외를 발생시킵니다.")
    @Test
    void enterString() {
        String readDate = "abcde";

        assertThatThrownBy(() -> new DateInfo(readDate))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new DateInfo(readDate))
                .hasMessage(ExceptionMessage.VISITED_DATE_NOT_NUMBER.getMessage());
    }

    @DisplayName("방문날짜를 올바르게 입력했는지 검증합니다. - 숫자와 문자를 함께 입력한 경우, 예외를 발생시킵니다.")
    @Test
    void enterNumberWithString() {
        String readDate = "123abc";

        assertThatThrownBy(() -> new DateInfo(readDate))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new DateInfo(readDate))
                .hasMessage(ExceptionMessage.VISITED_DATE_NOT_NUMBER.getMessage());
    }

    @DisplayName("방문날짜를 올바르게 입력했는지 검증합니다. - 띄어쓰기만 입력한 경우, 예외를 발생시킵니다.")
    @Test
    void enterSpace() {
        String readDate = "   ";

        assertThatThrownBy(() -> new DateInfo(readDate))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new DateInfo(readDate))
                .hasMessage(ExceptionMessage.VISITED_DATE_NOT_NUMBER.getMessage());
    }

    @DisplayName("방문날짜를 올바르게 입력했는지 검증합니다. - \n 입력한 경우, 예외를 발생시킵니다.")
    @Test
    void enterNewLine() {
        String readDate = "\n";

        assertThatThrownBy(() -> new DateInfo(readDate))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new DateInfo(readDate))
                .hasMessage(ExceptionMessage.VISITED_DATE_NOT_NUMBER.getMessage());
    }

    @DisplayName("방문날짜를 올바르게 입력했는지 검증합니다. - 어떠한 값도 입력하지 않은 경우, 예외를 발생시킵니다.")
    @Test
    void enterNothing() {
        String readDate = "";

        assertThatThrownBy(() -> new DateInfo(readDate))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new DateInfo(readDate))
                .hasMessage(ExceptionMessage.VISITED_DATE_NOT_NUMBER.getMessage());
    }

    @DisplayName("방문날짜를 올바르게 입력했는지 검증합니다. - 0을 입력할 경우, 예외를 발생시킵니다.")
    @Test
    void enterZero() {
        String inputAmount = "0";

        assertThatThrownBy(() -> new DateInfo(inputAmount))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new DateInfo(inputAmount))
                .hasMessage(ExceptionMessage.VISITED_DATE_NOT_NUMBER.getMessage());
    }
}
