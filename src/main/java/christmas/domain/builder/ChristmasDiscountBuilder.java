package christmas.domain.builder;

import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.constant.DiscountEvent;

public class ChristmasDiscountBuilder extends DiscountBuilder {
    private static final int CHRISTMAS_DATE = 25;
    private static final int BASE_DISCOUNT_AMOUNT = -900;
    private static final int ADDITIONAL_DISCOUNT_AMOUNT = -100;

    public ChristmasDiscountBuilder(DiscountEvent discountEvent, VisitDate visitDate, OrderMenus orderMenus) {
        super(discountEvent, visitDate, orderMenus);
    }

    @Override
    public boolean isAvailableDate() {
        return visitDate.getDayOfMonth() <= CHRISTMAS_DATE;
    }

    @Override
    public int getDiscount() {
        return BASE_DISCOUNT_AMOUNT + (visitDate.getDayOfMonth() * ADDITIONAL_DISCOUNT_AMOUNT);
    }
}
