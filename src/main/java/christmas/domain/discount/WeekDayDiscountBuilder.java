package christmas.domain.discount;

import christmas.domain.order.Menu;
import christmas.domain.order.Reservation;
import christmas.domain.constant.DecemberEvent;
import java.util.List;

public class WeekDayDiscountBuilder extends DiscountBuilder {
    private static final DecemberEvent WEEKDAY_EVENT = DecemberEvent.WEEKDAY;

    private static final int DISCOUNT_AMOUNT_PER_MENU = -2023;

    public WeekDayDiscountBuilder(Reservation reservation) {
        super(WEEKDAY_EVENT, reservation);
    }

    @Override
    boolean isAvailableDate() {
        return reservation.isVisitDayWeekDay();
    }

    @Override
    int getDiscount() {
        validate();

        List<Menu> orderMenus = reservation.getOrderMenus();

        return orderMenus.stream()
                .filter(Menu::isDessert)
                .mapToInt(Menu::getCount)
                .map(count -> count * DISCOUNT_AMOUNT_PER_MENU)
                .sum();
    }
}
