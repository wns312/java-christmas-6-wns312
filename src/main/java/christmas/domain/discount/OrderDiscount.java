package christmas.domain.discount;

import christmas.domain.Benefit;
import christmas.domain.EventBenefit;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountEvent;
import christmas.domain.constant.EventWeek;
import christmas.domain.constant.MenuType;
import java.time.LocalDate;
import java.util.List;

public abstract class OrderDiscount {
    static final int NO_DISCOUNT = 0;
    static final int DEFAULT_MINIMUM_PAYMENT = 10000;
    static final List<Menu> NO_GIFT = List.of();
    static final Benefit NO_BENEFIT = new Benefit(NO_DISCOUNT, NO_GIFT);

    abstract DiscountEvent getDiscountEventType();

    abstract int calculatePaymentDiscount(LocalDate date);

    abstract int calculateMenuDiscount(MenuType menuType, int count);

    abstract List<Menu> calculateGifts();

    private int calculateMenuDiscounts(List<Menu> menus) {
        return menus.stream().mapToInt(menu -> calculateMenuDiscount(menu.getMenu(), menu.getCount())).sum();
    }

    protected boolean isPaymentApplicable(int payment) {
        return payment >= DEFAULT_MINIMUM_PAYMENT;
    }

    protected boolean isApplicableDayOfWeek(LocalDate date) {
        return EventWeek.WHOLE_WEEK.contains(date.getDayOfWeek());
    }

    public final EventBenefit calculateEventBenefit(int payment, LocalDate date, List<Menu> menus) {
        if (!isPaymentApplicable(payment) || !isApplicableDayOfWeek(date)) {
            return new EventBenefit(getDiscountEventType(), NO_BENEFIT);
        }

        int paymentDiscount = calculatePaymentDiscount(date);
        int menuDiscounts = calculateMenuDiscounts(menus);
        List<Menu> gifts = calculateGifts();

        Benefit benefit = new Benefit(paymentDiscount + menuDiscounts, gifts);
        return new EventBenefit(getDiscountEventType(), benefit);
    }
}
