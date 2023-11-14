package christmas.domain.builder;

import christmas.domain.Menu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.constant.DiscountEvent;
import java.util.List;

public class WeekEndDiscountBuilder extends DiscountBuilder {
    private static final DiscountEvent WEEKEND_EVENT = DiscountEvent.WEEKEND;
    private static final int DISCOUNT_AMOUNT_PER_MENU = -2023;

    public WeekEndDiscountBuilder(VisitDate visitDate, OrderMenus orderMenus) {
        super(WEEKEND_EVENT, visitDate, orderMenus);
    }

    @Override
    public boolean isAvailableDate() {
        return visitDate.isWeekEnd();
    }

    @Override
    public int getDiscount() {
        List<Menu> elements = orderMenus.getElements();

        return elements.stream()
                .filter(Menu::isMain)
                .mapToInt(Menu::getCount)
                .map(count -> count * DISCOUNT_AMOUNT_PER_MENU)
                .sum();
    }
}
