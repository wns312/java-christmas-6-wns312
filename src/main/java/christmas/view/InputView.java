package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.dto.OrderMenuDto;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String ASKING_DATE_OF_VISIT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASKING_ORDER_MENUS_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String MENUS_DELIMITER = ",";
    private static final String MENU_COUNT_DELIMITER = "-";

    public int readDateOfVisit() {
        System.out.println(ASKING_DATE_OF_VISIT_MESSAGE);
        String input = Console.readLine();

        InputValidator.validateDateOfVisit(input);

        return Integer.parseInt(input);
    }

    private int parseMenuCount(String input) {
        InputValidator.validateMenuCount(input);

        return Integer.parseInt(input);
    }

    private OrderMenuDto convertOrderMenu(String inputMenu) {
        List<String> menuInfo = Arrays.stream(inputMenu.split(MENU_COUNT_DELIMITER))
                .map(String::trim)
                .toList();

        InputValidator.validateMenuInfoSize(menuInfo);

        String name = menuInfo.get(0);
        String rawCount = menuInfo.get(1);

        return new OrderMenuDto(name, parseMenuCount(rawCount));
    }

    private List<OrderMenuDto> convertOrderMenus(String input) {
        List<String> menus = Arrays.stream(input.split(MENUS_DELIMITER)).toList();

        return menus.stream()
                .map(String::trim)
                .map(this::convertOrderMenu)
                .toList();
    }

    public List<OrderMenuDto> readOrderMenus() {
        System.out.println(ASKING_ORDER_MENUS_MESSAGE);
        String input = Console.readLine();

        return convertOrderMenus(input);
    }
}
