package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String ASKING_DATE_OF_VISIT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public int readDateOfVisit() {
        System.out.println(ASKING_DATE_OF_VISIT_MESSAGE);
        String input = Console.readLine();

        InputValidator.validateDateOfVisit(input);

        return Integer.parseInt(input);
    }

}
