package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.discount.ChampagneGiftGenerator;
import christmas.domain.order.Menu;
import christmas.domain.constant.MenuType;
import christmas.exception.IllegalArgumentExceptionType;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChampagneGiftGeneratorTest {
    private static final int MINIMUM_PAYMENT = 120000;

    @DisplayName("비정상 구매 총액 테스트")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1000000, -100000, -10000, -1000, -100, -10, -1})
    void invalidTotalAmountTest(int totalPayment) {
        assertThatThrownBy(() -> new ChampagneGiftGenerator(totalPayment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.INVALID_PAYMENT_AMOUNT.getMessage());
    }

    @DisplayName("선물 없음 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 100, 1000, 10000, 20000, 50000, 100000, 110000, 115000, 119999})
    void getEmptyGiftTest(int totalPayment) {
        ChampagneGiftGenerator champagneGiftGenerator = new ChampagneGiftGenerator(totalPayment);
        List<Menu> menus = champagneGiftGenerator.generateGift();

        assertThat(menus.isEmpty()).isTrue();
    }

    @DisplayName("샴페인 선물 테스트")
    @ParameterizedTest
    @ValueSource(ints = {MINIMUM_PAYMENT, 130000, 200000, 500000, 1000000, Integer.MAX_VALUE})
    void getChampagneGiftTest(int totalPayment) {
        ChampagneGiftGenerator champagneGiftGenerator = new ChampagneGiftGenerator(totalPayment);
        List<Menu> menus = champagneGiftGenerator.generateGift();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(menus.isEmpty()).isFalse();
        softAssertions.assertThat(menus.size()).isOne();
        menus.forEach(menu -> {
            MenuType menuType = menu.getMenuType();
            int count = menu.getCount();
            int totalPrice = menu.getTotalPrice();
            softAssertions.assertThat(menuType).isEqualTo(MenuType.CHAMPAGNE);
            softAssertions.assertThat(count).isOne();
            softAssertions.assertThat(totalPrice).isEqualTo(MenuType.CHAMPAGNE.getPrice());
        });

        softAssertions.assertAll();
    }
}