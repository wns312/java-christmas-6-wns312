package christmas.domain.gift;

import christmas.domain.Menu;
import christmas.domain.constant.MenuType;
import java.util.List;

public class ChampagneGiftGenerator implements GiftGenerator {
    private static final int MINIMUM_PAYMENT = 120000;
    private final int totalPayment;

    public ChampagneGiftGenerator(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Override
    public List<Menu> generateGift() {
        if (totalPayment < MINIMUM_PAYMENT) {
            return List.of();
        }

        return List.of(new Gift(MenuType.CHAMPAGNE, 1));
    }
}
