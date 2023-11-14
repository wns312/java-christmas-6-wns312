package christmas.domain.gift;

import christmas.domain.Menu;
import christmas.domain.constant.MenuType;
import christmas.exception.IllegalArgumentExceptionType;
import java.util.List;

public class ChampagneGiftGenerator implements GiftGenerator {
    private static final int NO_PAYMENT = 0;
    private static final int MINIMUM_PAYMENT = 120000;
    private final int totalPayment;

    public ChampagneGiftGenerator(int totalPayment) {
        validate(totalPayment);
        this.totalPayment = totalPayment;
    }

    private void validate(int totalPayment) {
        if (totalPayment < NO_PAYMENT) {
            throw IllegalArgumentExceptionType.INVALID_PAYMENT_AMOUNT.getException();
        }
    }

    @Override
    public List<Menu> generateGift() {
        if (totalPayment < MINIMUM_PAYMENT) {
            return List.of();
        }

        return List.of(new Gift(MenuType.CHAMPAGNE, 1));
    }
}
