package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.constant.DiscountEvent;
import christmas.domain.constant.MenuType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SpecialStarDiscount extends OrderDiscount {
    private static final int CHRISTMAS_DATE = 25;
    private static final int FIXED_DISCOUNT = 1000;

    private static boolean isSunday(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SUNDAY;
    }

    private static boolean isChristMas(int dateOfMonth) {
        return dateOfMonth == CHRISTMAS_DATE;
    }

    private static boolean isStarDate(LocalDate date) {
        return isSunday(date.getDayOfWeek()) || isChristMas(date.getDayOfMonth());
    }

    private static boolean isNotStarDate(LocalDate date) {
        return !isStarDate(date);
    }

    @Override
    public DiscountEvent getDiscountEventType() { return DiscountEvent.SPECIAL; }

    @Override
    int calculatePaymentDiscount(LocalDate date) {
        if (isNotStarDate(date)) {
            return NO_DISCOUNT;
        }

        return FIXED_DISCOUNT;
    }

    @Override
    int calculateMenuDiscount(MenuType menuType, int count) { return NO_DISCOUNT; }

    @Override
    List<Menu> calculateGifts() { return NO_GIFT; }
}
