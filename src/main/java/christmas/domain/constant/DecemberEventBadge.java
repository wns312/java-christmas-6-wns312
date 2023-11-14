package christmas.domain.constant;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public enum DecemberEventBadge {
    NO_BADGE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String expression;
    private final int minimumPayment;

    DecemberEventBadge(String expression, int minimumPayment) {
        this.expression = expression;
        this.minimumPayment = minimumPayment;
    }

    public String getExpression() {
        return expression;
    }

    private static List<DecemberEventBadge> filterBadgesByPayment(int payment) {
        return Stream.of(values()).filter(decemberEventBadge -> decemberEventBadge.minimumPayment <= payment).toList();
    }

    public static DecemberEventBadge getBadgeByBenefitAmount(int benefitAmount) {
        List<DecemberEventBadge> badges = filterBadgesByPayment(benefitAmount);

        return Collections.max(badges, Comparator.comparing(decemberEventBadge -> decemberEventBadge.minimumPayment));
    }
}
