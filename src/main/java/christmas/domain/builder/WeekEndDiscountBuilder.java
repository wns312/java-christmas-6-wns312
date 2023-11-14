package christmas.domain.builder;

import christmas.domain.Menu;
import christmas.domain.Reservation;
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
        return reservation.isVisitDayWeekEnd();
    }

    @Override
    int getDiscount() {
        validate();

        List<Menu> orderMenus = reservation.getOrderMenus();

        return orderMenus.stream()
                .filter(Menu::isMain)
                .mapToInt(Menu::getCount)
                .map(count -> count * DISCOUNT_AMOUNT_PER_MENU)
                .sum();
    }
}
