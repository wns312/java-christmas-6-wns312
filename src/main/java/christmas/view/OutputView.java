package christmas.view;

import christmas.domain.constant.EventBadge;
import christmas.domain.constant.DecemberEvent;
import christmas.domain.dto.DiscountDto;
import christmas.domain.dto.MenuDto;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
    private static final String INTRODUCING_PLANNER_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final static String INTRODUCING_BENEFIT_EXPRESSION = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n";
    private final static String ORDERED_MENU_MESSAGE = "\n<주문 메뉴>";
    private final static String TOTAL_PAYMENT_MESSAGE = "\n<할인 전 총주문 금액>";
    private final static String GIFT_MESSAGE = "\n<증정 메뉴>";
    private final static String EVENT_BENEFITS_MESSAGE = "\n<혜택 내역>";
    private final static String EVENT_TOTAL_BENEFIT_AMOUNT_MESSAGE = "\n<총혜택 금액>";
    private final static String DISCOUNTED_TOTAL_PAYMENT_MESSAGE = "\n<할인 후 예상 결제 금액>";
    private final static String DECEMBER_EVENT_BADGE_MESSAGE = "\n<12월 이벤트 배지>";
    private final static String MENU_EXPRESSION = "%s %d개%n";
    private final static String COST_EXPRESSION = "%s원%n";
    private final static String EVENT_BENEFIT_EXPRESSION = "%s: %s원%n";
    private final static String NOTHING_TO_PRINT_MESSAGE = "없음";

    private String convertNumberToCostExpression(int number) { return DECIMAL_FORMAT.format(number); }

    public void printIntroducingPlannerMessage() { System.out.println(INTRODUCING_PLANNER_MESSAGE); }

    public void printIntroducingBenefitMessage(int date) {
        System.out.printf(INTRODUCING_BENEFIT_EXPRESSION, date);
    }

    private void printNothingToPrintMessage() {
        System.out.println(NOTHING_TO_PRINT_MESSAGE);
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

    public void printGifts(List<MenuDto> gifts) {
        System.out.println(GIFT_MESSAGE);

        if (gifts.isEmpty()) {
            printNothingToPrintMessage();
            return;
        }

        gifts.forEach(this::printMenu);
    }

    private void printDiscount(DiscountDto discount) {
        DecemberEvent decemberEvent = discount.decemberEvent();
        String discountAmountExpression = convertNumberToCostExpression(discount.discountAmount());

        System.out.printf(EVENT_BENEFIT_EXPRESSION, decemberEvent.getEventName(), discountAmountExpression);
    }

    public void printDiscounts(List<DiscountDto> discounts) {
        System.out.println(EVENT_BENEFITS_MESSAGE);

        if (discounts.isEmpty()) {
            printNothingToPrintMessage();
            return;
        }

        discounts.forEach(this::printDiscount);
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        String totalBenefitsAmountExpression = convertNumberToCostExpression(totalBenefitAmount);
        System.out.println(EVENT_TOTAL_BENEFIT_AMOUNT_MESSAGE);
        System.out.printf(COST_EXPRESSION, totalBenefitsAmountExpression);
    }

    public void printTotalPaymentAfterDiscount(int totalPayment) {
        String discountedTotalPaymentExpression = convertNumberToCostExpression(totalPayment);
        System.out.println(DISCOUNTED_TOTAL_PAYMENT_MESSAGE);
        System.out.printf(COST_EXPRESSION, discountedTotalPaymentExpression);
    }

    public void printBadge(EventBadge eventBadge) {
        System.out.println(DECEMBER_EVENT_BADGE_MESSAGE);

        if (eventBadge == EventBadge.NO_BADGE) {
            printNothingToPrintMessage();
            return;
        }
        System.out.println(eventBadge.getExpression());
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
