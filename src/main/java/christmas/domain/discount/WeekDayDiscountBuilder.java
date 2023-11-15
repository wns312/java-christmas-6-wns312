package christmas.domain.discount;

import christmas.domain.order.Reservation;
import christmas.domain.constant.DecemberEvent;

public class WeekDayDiscountBuilder extends DiscountBuilder {
    private static final DecemberEvent WEEKDAY_EVENT = DecemberEvent.WEEKDAY;

    private static final int DISCOUNT_AMOUNT_PER_MENU = -2023;

    public WeekDayDiscountBuilder(Reservation reservation) {
        super(WEEKDAY_EVENT, reservation);
    }

    @Override
    protected boolean isAvailableDate() {
        return reservation.isWeekDay();
    }

    @Override
    protected int getDiscount() {
        validate();

        return reservation.getDessertMenuCount() * DISCOUNT_AMOUNT_PER_MENU;
    }
}
