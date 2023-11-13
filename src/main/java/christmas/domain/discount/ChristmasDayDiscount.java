package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.constant.DiscountEvent;
import christmas.domain.constant.MenuType;
import java.time.LocalDate;
import java.util.List;

public class ChristmasDayDiscount extends OrderDiscount {
    private static final int CHRISTMAS_DATE = 25;
    private static final int BASE_CHRISTMAS_DISCOUNT = 900;
    private static final int ADDITIONAL_CHRISTMAS_DISCOUNT_AMOUNT = 100;

    private boolean isDateWithinChristmas(int date) {
        return date <= CHRISTMAS_DATE;
    }

    private int calculateDiscountAmountByDate(int date) {
        return BASE_CHRISTMAS_DISCOUNT + (ADDITIONAL_CHRISTMAS_DISCOUNT_AMOUNT * date);
    }

    @Override
    public DiscountEvent getDiscountEventType() { return DiscountEvent.CHRISTMAS_D_DAY; }

    @Override
    int calculatePaymentDiscount(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();

        if (isDateWithinChristmas(dayOfMonth)) {
            return calculateDiscountAmountByDate(dayOfMonth);

        }

        return NO_DISCOUNT;
    }

    @Override
    int calculateMenuDiscount(MenuType menuType, int count) {
        return NO_DISCOUNT;
    }

    @Override
    List<Menu> calculateGifts() {
        return NO_GIFT;
    }
}
