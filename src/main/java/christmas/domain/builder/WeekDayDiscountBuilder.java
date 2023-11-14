package christmas.domain.builder;

import christmas.domain.Menu;
import christmas.domain.Reservation;
import christmas.domain.constant.DiscountEvent;
import java.util.List;

public class WeekDayDiscountBuilder extends DiscountBuilder {
    private static final DiscountEvent WEEKDAY_EVENT = DiscountEvent.WEEKDAY;

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
