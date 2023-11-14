package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.constant.DecemberEvent;
import christmas.domain.discount.Discount;
import christmas.exception.IllegalArgumentExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountTest {
    private static final int NO_DISCOUNT = 0;

    @DisplayName("비정상 할인 가격 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 100, 200, 500, 1000, 2000, 2023, 4000, 4046, 10000, Integer.MAX_VALUE})

    void invalidDiscountAmountTest(int discountAmount) {
        assertThatThrownBy(() -> new Discount(DecemberEvent.SPECIAL, discountAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.INVALID_DISCOUNT.getMessage());
    }

    @DisplayName("할인 이벤트 종류 조회")
    @ParameterizedTest
    @EnumSource(DecemberEvent.class)
    void getDiscountEventTest(DecemberEvent decemberEvent) {
        Discount discount = new Discount(decemberEvent, NO_DISCOUNT);

        assertThat(discount.getDiscountEvent())
                .isEqualTo(decemberEvent);
    }

    @DisplayName("할인 금액 조회")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, -10, -100, -200, -500, -1000, -2000, -2023, -4000, -4046, -10000})    void getDiscountAmountTest(int discountAmount) {
        Discount discount = new Discount(DecemberEvent.SPECIAL, discountAmount);

        assertThat(discount.getDiscountAmount())
                .isEqualTo(discountAmount);
    }

    @DisplayName("할인 없음 테스트")
    @Test
    void hasDiscountSuccessTest() {
        Discount discount = new Discount(DecemberEvent.SPECIAL, NO_DISCOUNT);

        assertThat(discount.hasDiscount())
                .isFalse();
    }

    @DisplayName("할인 있음 테스트")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, -10, -100, -200, -500, -1000, -2000, -2023, -4000, -4046, -10000})
    void doesNotHaveDiscountTest(int discountAmount) {
        Discount discount = new Discount(DecemberEvent.SPECIAL, discountAmount);

        assertThat(discount.hasDiscount())
                .isTrue();
    }
}