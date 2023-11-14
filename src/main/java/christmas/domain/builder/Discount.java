package christmas.domain.builder;

import christmas.domain.constant.DiscountEvent;
import christmas.exception.IllegalArgumentExceptionType;

public class Discount {
    private static final int NO_DISCOUNT = 0;

    private final DiscountEvent discountEvent;
    private final int discountAmount;

    public Discount(DiscountEvent discountEvent, int discountAmount) {
        validate(discountAmount);
        this.discountEvent = discountEvent;
        this.discountAmount = discountAmount;
    }

    private void validate(int discountAmount) {
        if (discountAmount > 0) {
            throw IllegalArgumentExceptionType.INVALID_DISCOUNT.getException();
        }
    }

    public DiscountEvent getDiscountEvent() {
        return discountEvent;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public boolean hasDiscount() {
        return discountAmount != NO_DISCOUNT;
    }
}
