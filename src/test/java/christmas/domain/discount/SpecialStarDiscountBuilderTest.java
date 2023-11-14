package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.discount.SpecialStarDiscountBuilder;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import christmas.domain.constant.MenuType;
import christmas.exception.IllegalStateExceptionType;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialStarDiscountBuilderTest {
    private static final int FIX_DISCOUNT_AMOUNT = -1000;
    private static final int NO_DISCOUNT = 0;

    @DisplayName("할인 가능 방문 날짜 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void checkDiscountableVisitDateTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenus orderMenus = new OrderMenus(List.of(
                new OrderMenu(MenuType.BARBECUED_RIBS, 2),
                new OrderMenu(MenuType.RED_WINE, 2)
        ));
        Reservation reservation = new Reservation(visitDate, orderMenus);

        SpecialStarDiscountBuilder specialStarDiscountBuilder = new SpecialStarDiscountBuilder(reservation);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(specialStarDiscountBuilder.isAvailableDate())
                .isTrue();
        softAssertions.assertThat(specialStarDiscountBuilder.discount())
                .isEqualTo(FIX_DISCOUNT_AMOUNT);

        softAssertions.assertAll();
    }

    @DisplayName("할인 불가능 방문 날짜 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30})
    void checkNonDiscountableVisitDateTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenus orderMenus = new OrderMenus(List.of(
                new OrderMenu(MenuType.BARBECUED_RIBS, 2),
                new OrderMenu(MenuType.RED_WINE, 2)
        ));
        Reservation reservation = new Reservation(visitDate, orderMenus);

        SpecialStarDiscountBuilder specialStarDiscountBuilder = new SpecialStarDiscountBuilder(reservation);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(specialStarDiscountBuilder.isAvailableDate())
                .isFalse();
        softAssertions.assertThat(specialStarDiscountBuilder.discount())
                .isEqualTo(NO_DISCOUNT);

        softAssertions.assertAll();
    }

    @DisplayName("할인 불가능 날짜 할인 호출 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30})
    void invalidGettingDiscountCall(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenus orderMenus = new OrderMenus(List.of(
                new OrderMenu(MenuType.BARBECUED_RIBS, 2),
                new OrderMenu(MenuType.RED_WINE, 2)
        ));
        Reservation reservation = new Reservation(visitDate, orderMenus);

        SpecialStarDiscountBuilder specialStarDiscountBuilder = new SpecialStarDiscountBuilder(reservation);

        assertThatThrownBy(specialStarDiscountBuilder::getDiscount)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(IllegalStateExceptionType.CANNOT_GET_DISCOUNT.getMessage());
    }
}
