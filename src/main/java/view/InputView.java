package view;

import camp.nextstep.edu.missionutils.Console;
import util.InputMessage;

public class InputView {
    public static String readDate() {
        System.out.println(InputMessage.VISIT_DATE_MESSAGE.getMessage());
        String input = Console.readLine();
        return input;
    }

    public static String readOrder(){
        System.out.println(InputMessage.ORDER_MENU_MESSAGE.getMessage());
        String input = Console.readLine();
        return input;
    }
}
