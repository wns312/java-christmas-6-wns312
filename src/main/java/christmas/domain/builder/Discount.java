package christmas.domain.builder;

import christmas.domain.constant.DecemberEvent;
import christmas.exception.IllegalArgumentExceptionType;

public class Discount {
    private static final int NO_DISCOUNT = 0;

    private final DecemberEvent decemberEvent;
    private final int discountAmount;

    public Discount(DecemberEvent decemberEvent, int discountAmount) {
        validate(discountAmount);
        this.decemberEvent = decemberEvent;
        this.discountAmount = discountAmount;
    }

    private void validate(int discountAmount) {
        if (discountAmount > 0) {
            throw IllegalArgumentExceptionType.INVALID_DISCOUNT.getException();
        }
    }

    public DecemberEvent getDiscountEvent() {
        return decemberEvent;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public boolean hasDiscount() {
        return discountAmount != NO_DISCOUNT;
    }
}
