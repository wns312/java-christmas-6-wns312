package christmas.view;

import christmas.domain.constant.EventBadge;
import christmas.domain.constant.DecemberEvent;
import christmas.domain.discount.dto.DiscountDto;
import christmas.domain.order.dto.MenuDto;
import java.util.List;

public class OutputView {

    private String convertNumberToCostExpression(int number) { return OutputViewConstants.DECIMAL_FORMAT.format(number); }

    public void printIntroducingPlannerMessage() { System.out.println(OutputViewConstants.INTRODUCING_PLANNER_MESSAGE); }

    public void printIntroducingBenefitMessage(int date) {
        System.out.printf(OutputViewConstants.INTRODUCING_BENEFIT_EXPRESSION, date);
    }

    private void printNothingToPrintMessage() {
        System.out.println(OutputViewConstants.NOTHING_TO_PRINT_MESSAGE);
    }

    private void printMenu(MenuDto menu) {
        System.out.printf(OutputViewConstants.MENU_EXPRESSION, menu.name(), menu.count());
    }

    public void printOrderMenus(List<MenuDto> menus) {
        System.out.println(OutputViewConstants.ORDERED_MENU_MESSAGE);
        menus.forEach(this::printMenu);
    }
    public void printTotalPayment(int totalPayment) {
        System.out.println(OutputViewConstants.TOTAL_PAYMENT_MESSAGE);
        System.out.printf(OutputViewConstants.COST_EXPRESSION, convertNumberToCostExpression(totalPayment));
    }

    public void printGifts(List<MenuDto> gifts) {
        System.out.println(OutputViewConstants.GIFT_MESSAGE);

        if (gifts.isEmpty()) {
            printNothingToPrintMessage();
            return;
        }

        gifts.forEach(this::printMenu);
    }

    private void printDiscount(DiscountDto discount) {
        DecemberEvent decemberEvent = discount.decemberEvent();
        String discountAmountExpression = convertNumberToCostExpression(discount.discountAmount());

        System.out.printf(OutputViewConstants.EVENT_BENEFIT_EXPRESSION, decemberEvent.getEventName(), discountAmountExpression);
    }

    public void printDiscounts(List<DiscountDto> discounts) {
        System.out.println(OutputViewConstants.EVENT_BENEFITS_MESSAGE);

        if (discounts.isEmpty()) {
            printNothingToPrintMessage();
            return;
        }

        discounts.forEach(this::printDiscount);
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        String totalBenefitsAmountExpression = convertNumberToCostExpression(totalBenefitAmount);
        System.out.println(OutputViewConstants.EVENT_TOTAL_BENEFIT_AMOUNT_MESSAGE);
        System.out.printf(OutputViewConstants.COST_EXPRESSION, totalBenefitsAmountExpression);
    }

    public void printTotalPaymentAfterDiscount(int totalPayment) {
        String discountedTotalPaymentExpression = convertNumberToCostExpression(totalPayment);
        System.out.println(OutputViewConstants.DISCOUNTED_TOTAL_PAYMENT_MESSAGE);
        System.out.printf(OutputViewConstants.COST_EXPRESSION, discountedTotalPaymentExpression);
    }

    public void printBadge(EventBadge eventBadge) {
        System.out.println(OutputViewConstants.DECEMBER_EVENT_BADGE_MESSAGE);

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
