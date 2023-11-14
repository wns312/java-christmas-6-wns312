package christmas.domain.builder;

import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.constant.DiscountEvent;

public class SpecialStarDiscountBuilder extends DiscountBuilder {
    private static final DiscountEvent SPECIAL_EVENT = DiscountEvent.SPECIAL;
    private static final int FIX_DISCOUNT_AMOUNT = -1000;

    public SpecialStarDiscountBuilder(VisitDate visitDate, OrderMenus orderMenus) {
        super(SPECIAL_EVENT, visitDate, orderMenus);
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
