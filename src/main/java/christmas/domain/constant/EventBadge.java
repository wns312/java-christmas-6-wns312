package christmas.domain.constant;

import christmas.exception.IllegalStateExceptionType;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public enum EventBadge {
    NO_BADGE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String expression;
    private final int minimumPayment;

    EventBadge(String expression, int minimumPayment) {
        this.expression = expression;
        this.minimumPayment = minimumPayment;
    }

    public String getExpression() {
        return expression;
    }

    private static void validateBenefitAmount(int benefitAmount) {
        if (benefitAmount < NO_BADGE.minimumPayment) {
            throw IllegalStateExceptionType.INVALID_BENEFIT_AMOUNT.getException();
        }
    }

    private static List<EventBadge> filterBadgesByPayment(int payment) {
        return Stream.of(values()).filter(eventBadge -> eventBadge.minimumPayment <= payment).toList();
    }

    public static EventBadge getBadgeByBenefitAmount(int benefitAmount) {
        validateBenefitAmount(benefitAmount);

        List<EventBadge> badges = filterBadgesByPayment(benefitAmount);

        return Collections.max(badges, Comparator.comparing(eventBadge -> eventBadge.minimumPayment));
    }
}
