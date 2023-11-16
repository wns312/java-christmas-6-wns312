package christmas.domain.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.IllegalStateExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventBadgeTest {
    @DisplayName("음수 혜택 금액으로 배지 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -100000, -10000, -1000, -100, -10, -1})
    void getWithInvalidBenefitAmount(int invalidBenefitAmount) {
        assertThatThrownBy(() -> EventBadge.getBadgeByBenefitAmount(invalidBenefitAmount))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(IllegalStateExceptionType.INVALID_BENEFIT_AMOUNT.getMessage());
    }

    @DisplayName("혜택 금액에 따른 배지 조회 테스트 - 없음")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 100, 1000, 2000, 300, 4000, 4500, 4900, 4990, 4999})
    void getNoBadgeWithBenefitAmount(int benefitAmount) {
        EventBadge badgeByBenefitAmount = EventBadge.getBadgeByBenefitAmount(benefitAmount);

        assertThat(badgeByBenefitAmount.getExpression())
                .isEqualTo(EventBadge.NO_BADGE.getExpression());
    }

    @DisplayName("혜택 금액에 따른 배지 조회 테스트 - 별")
    @ParameterizedTest
    @ValueSource(ints = {5000, 9999})
    void getStarBadgeWithBenefitAmount(int benefitAmount) {
        EventBadge badgeByBenefitAmount = EventBadge.getBadgeByBenefitAmount(benefitAmount);

        assertThat(badgeByBenefitAmount.getExpression())
                .isEqualTo(EventBadge.STAR.getExpression());
    }

    @DisplayName("혜택 금액에 따른 배지 조회 테스트 - 트리")
    @ParameterizedTest
    @ValueSource(ints = {10000, 19999})
    void getTreeBadgeWithBenefitAmount(int benefitAmount) {
        EventBadge badgeByBenefitAmount = EventBadge.getBadgeByBenefitAmount(benefitAmount);

        assertThat(badgeByBenefitAmount.getExpression())
                .isEqualTo(EventBadge.TREE.getExpression());
    }

    @DisplayName("혜택 금액에 따른 배지 조회 테스트 - 산타")
    @ParameterizedTest
    @ValueSource(ints = {20000, 50000, 100000, 1000000, Integer.MAX_VALUE})
    void getSantaBadgeWithBenefitAmount(int benefitAmount) {
        EventBadge badgeByBenefitAmount = EventBadge.getBadgeByBenefitAmount(benefitAmount);

        assertThat(badgeByBenefitAmount.getExpression())
                .isEqualTo(EventBadge.SANTA.getExpression());
    }
}
