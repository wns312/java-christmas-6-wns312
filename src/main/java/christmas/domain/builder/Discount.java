package christmas.domain.builder;

import christmas.domain.constant.DiscountEvent;

public class Discount {
    private static final int NO_DISCOUNT = 0;

    private final DiscountEvent discountEvent;
    private final int discountAmount;

    public Discount(DiscountEvent discountEvent, int discountAmount) {
        this.discountEvent = discountEvent;
        this.discountAmount = discountAmount;
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
