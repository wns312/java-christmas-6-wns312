package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.constant.DiscountEvent;
import christmas.domain.constant.EventWeek;
import christmas.domain.constant.MenuType;
import christmas.domain.constant.MenuCategory;
import java.time.LocalDate;
import java.util.List;

public class WeekDayDiscount extends OrderDiscount {
    private static final MenuCategory DISCOUNT_CATEGORY = MenuCategory.DESSERT;
    private static final int DISCOUNT_AMOUNT_PER_MENU = 2023;

    private boolean isMenuDiscountable(MenuType menuType) {
        return menuType.getCategory() == DISCOUNT_CATEGORY;
    }

    @Override
    protected boolean isApplicableDayOfWeek(LocalDate date) {
        return EventWeek.WEEKDAY.contains(date.getDayOfWeek());
    }

    @Override
    public DiscountEvent getDiscountEventType() { return DiscountEvent.WEEKDAY; }

    @Override
    int calculatePaymentDiscount(LocalDate date) { return NO_DISCOUNT; }

    @Override
    int calculateMenuDiscount(MenuType menuType, int count) {
        if (isMenuDiscountable(menuType)) {
            return count * DISCOUNT_AMOUNT_PER_MENU;
        }

        return NO_DISCOUNT;
    }

    @Override
    List<Menu> calculateGifts() { return NO_GIFT; }
}
