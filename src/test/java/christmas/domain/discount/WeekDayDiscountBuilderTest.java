package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

class WeekDayDiscountBuilderTest {
    private static final int DISCOUNT_AMOUNT_PER_MENU = -2023;
    private static final int NO_DISCOUNT = 0;

    @DisplayName("할인 가능 방문 날짜 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void checkDiscountableVisitDateTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenus orderMenus = new OrderMenus(List.of(
                new OrderMenu(MenuType.BARBECUED_RIBS, 2),
                new OrderMenu(MenuType.CHOCOLATE_CAKE, 2)
        ));

        WeekDayDiscountBuilder weekDayDiscountBuilder = new WeekDayDiscountBuilder(new Reservation(visitDate, orderMenus));

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(weekDayDiscountBuilder.isAvailableDate())
                .isTrue();
        softAssertions.assertThat(weekDayDiscountBuilder.discount())
                .isEqualTo(DISCOUNT_AMOUNT_PER_MENU * 2);

        softAssertions.assertAll();
    }

    @DisplayName("할인 불가능 방문 날짜 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void checkNonDiscountableVisitDateTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenus orderMenus = new OrderMenus(List.of(
                new OrderMenu(MenuType.BARBECUED_RIBS, 2),
                new OrderMenu(MenuType.CHOCOLATE_CAKE, 2)
        ));
        Reservation reservation = new Reservation(visitDate, orderMenus);

        WeekDayDiscountBuilder weekDayDiscountBuilder = new WeekDayDiscountBuilder(reservation);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(weekDayDiscountBuilder.isAvailableDate())
                .isFalse();
        softAssertions.assertThat(weekDayDiscountBuilder.discount())
                .isEqualTo(NO_DISCOUNT);

        softAssertions.assertAll();
    }

    @DisplayName("할인 불가능 날짜 할인 호출 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void invalidGettingDiscountCall(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenus orderMenus = new OrderMenus(List.of(
                new OrderMenu(MenuType.BARBECUED_RIBS, 2),
                new OrderMenu(MenuType.CHOCOLATE_CAKE, 2)
        ));
        Reservation reservation = new Reservation(visitDate, orderMenus);

        WeekDayDiscountBuilder weekDayDiscountBuilder = new WeekDayDiscountBuilder(reservation);
        assertThatThrownBy(weekDayDiscountBuilder::getDiscount)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(IllegalStateExceptionType.CANNOT_GET_DISCOUNT.getMessage());
    }
}
