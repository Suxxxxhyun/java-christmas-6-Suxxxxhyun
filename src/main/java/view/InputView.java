package view;

import camp.nextstep.edu.missionutils.Console;

/**
 * packageName    : view
 * fileName       : InputView
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-09        qkrtn_ulqpbq2       최초 생성
 */
public class InputView {

    public static final String VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static String readDate() {
        System.out.println(VISIT_DATE_MESSAGE);
        String input = Console.readLine();
        return input;
    }
}
