package christmas.domain.builder;

import christmas.domain.Menu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.constant.DiscountEvent;
import java.util.List;

public class WeekDayDiscountBuilder extends DiscountBuilder {
    private static final DiscountEvent WEEKDAY_EVENT = DiscountEvent.WEEKDAY;

    private static final int DISCOUNT_AMOUNT_PER_MENU = -2023;

    public WeekDayDiscountBuilder(VisitDate visitDate, OrderMenus orderMenus) {
        super(WEEKDAY_EVENT, visitDate, orderMenus);
    }

    @Override
    boolean isAvailableDate() {
        return visitDate.isWeekDay();
    }

    @Override
    int getDiscount() {
        List<Menu> elements = orderMenus.getElements();

        return elements.stream()
                .filter(Menu::isDessert)
                .mapToInt(Menu::getCount)
                .map(count -> count * DISCOUNT_AMOUNT_PER_MENU)
                .sum();
    }
}
