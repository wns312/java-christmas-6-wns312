package christmas.domain.discount;

import christmas.domain.order.Menu;
import christmas.domain.order.Reservation;
import christmas.domain.constant.DecemberEvent;
import java.util.List;

public class WeekEndDiscountBuilder extends DiscountBuilder {
    private static final DecemberEvent WEEKEND_EVENT = DecemberEvent.WEEKEND;
    private static final int DISCOUNT_AMOUNT_PER_MENU = -2023;

    public WeekEndDiscountBuilder(Reservation reservation) {
        super(WEEKEND_EVENT, reservation);
    }

    @Override
    boolean isAvailableDate() {
        return reservation.isWeekEnd();
    }

    @Override
    int getDiscount() {
        validate();

        return reservation.getMainMenuCount() * DISCOUNT_AMOUNT_PER_MENU;
    }
}
