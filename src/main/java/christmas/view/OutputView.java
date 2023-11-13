package christmas.view;

import christmas.domain.dto.MenuDto;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
    private static final String INTRODUCING_PLANNER_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final static String INTRODUCING_BENEFIT_EXPRESSION = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n";
    private final static String ORDERED_MENU_MESSAGE = "\n<주문 메뉴>";
    private final static String TOTAL_PAYMENT_MESSAGE = "\n<할인 전 총주문 금액>";
    private final static String MENU_EXPRESSION = "%s %d개%n";
    private final static String COST_EXPRESSION = "%s원%n";

    private String convertNumberToCostExpression(int number) { return DECIMAL_FORMAT.format(number); }

    public void printIntroducingPlannerMessage() { System.out.println(INTRODUCING_PLANNER_MESSAGE); }

    public void printIntroducingBenefitMessage(int date) {
        System.out.printf(INTRODUCING_BENEFIT_EXPRESSION, date);
    }


    private void printMenu(MenuDto menu) {
        System.out.printf(MENU_EXPRESSION, menu.name(), menu.count());
    }

    public void printOrderMenus(List<MenuDto> menus) {
        System.out.println(ORDERED_MENU_MESSAGE);
        menus.forEach(this::printMenu);
    }
    public void printTotalPayment(int totalPayment) {
        System.out.println(TOTAL_PAYMENT_MESSAGE);
        System.out.printf(COST_EXPRESSION, convertNumberToCostExpression(totalPayment));
    }


    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
