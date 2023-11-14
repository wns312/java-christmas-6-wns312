package christmas.domain.builder;

import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.constant.DiscountEvent;

public class SpecialStarDiscountBuilder extends DiscountBuilder {
    private static final int FIX_DISCOUNT_AMOUNT = -1000;

    public SpecialStarDiscountBuilder(DiscountEvent discountEvent, VisitDate visitDate, OrderMenus orderMenus) {
        super(discountEvent, visitDate, orderMenus);
    }

    @Override
    public boolean isAvailableDate() {
        return visitDate.isSunday() || visitDate.isChristmas();
    }

    @Override
    public int getDiscount() {
        return FIX_DISCOUNT_AMOUNT;
    }
}
