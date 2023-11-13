package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.constant.DiscountEvent;
import christmas.domain.constant.MenuType;
import java.time.LocalDate;
import java.util.List;

public class ChampagneGiftDiscount extends OrderDiscount {
    static final int MINIMUM_PAYMENT = 120000;
    static final List<Menu> CHAMPAGNE_GIFT = List.of(new Menu(MenuType.CHAMPAGNE, 1));

    @Override
    protected boolean isPaymentApplicable(int payment) {
        return payment >= MINIMUM_PAYMENT;
    }


    @Override
    public DiscountEvent getDiscountEventType() { return DiscountEvent.GIFT; }

    @Override
    int calculatePaymentDiscount(LocalDate date) { return NO_DISCOUNT; }

    @Override
    int calculateMenuDiscount(MenuType menuType, int count) { return NO_DISCOUNT; }

    @Override
    List<Menu> calculateGifts() { return CHAMPAGNE_GIFT; }
}
