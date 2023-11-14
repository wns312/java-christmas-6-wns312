package christmas.domain.builder;

import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.constant.DiscountEvent;

public class ChristmasDiscountBuilder extends DiscountBuilder {
    private static final DiscountEvent CHRISTMAS_D_DAY_EVENT = DiscountEvent.CHRISTMAS_D_DAY;
    private static final int CHRISTMAS_DATE = 25;
    private static final int BASE_DISCOUNT_AMOUNT = -900;
    private static final int ADDITIONAL_DISCOUNT_AMOUNT = -100;

    public ChristmasDiscountBuilder(VisitDate visitDate, OrderMenus orderMenus) {
        super(CHRISTMAS_D_DAY_EVENT, visitDate, orderMenus);
    }

    @Override
    boolean isAvailableDate() {
        return visitDate.getDayOfMonth() <= CHRISTMAS_DATE;
    }

    @Override
    int getDiscount() {
        return BASE_DISCOUNT_AMOUNT + (visitDate.getDayOfMonth() * ADDITIONAL_DISCOUNT_AMOUNT);
    }
}
