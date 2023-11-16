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

class ChristmasDiscountBuilderTest {
    private static final int BASE_DISCOUNT_AMOUNT = -900;
    private static final int ADDITIONAL_DISCOUNT_AMOUNT = -100;

    private ChristmasDiscountBuilder getChristmasDiscountBuilder(VisitDate visitDate) {
        OrderMenus orderMenus = new OrderMenus(List.of(
                new OrderMenu(MenuType.BARBECUED_RIBS, 2),
                new OrderMenu(MenuType.RED_WINE, 2)
        ));
        return new ChristmasDiscountBuilder(new Reservation(visitDate, orderMenus));
    }

    @DisplayName("할인 가능 방문 날짜 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25})
    void checkDiscountableVisitDateTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        ChristmasDiscountBuilder christmasDiscountBuilder = getChristmasDiscountBuilder(visitDate);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(christmasDiscountBuilder.isAvailableDate()).isTrue();
        softAssertions.assertThat(christmasDiscountBuilder.discount())
                .isEqualTo(BASE_DISCOUNT_AMOUNT + (date * ADDITIONAL_DISCOUNT_AMOUNT));

        softAssertions.assertAll();
    }

    @DisplayName("할인 불가능 방문 날짜 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void checkNonDiscountableVisitDateTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        ChristmasDiscountBuilder christmasDiscountBuilder = getChristmasDiscountBuilder(visitDate);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(christmasDiscountBuilder.isAvailableDate()).isFalse();
        softAssertions.assertThat(christmasDiscountBuilder.discount()).isEqualTo(0);

        softAssertions.assertAll();
    }


    @DisplayName("할인 불가능 날짜 할인 호출 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void invalidGettingDiscountCall(int date) {
        VisitDate visitDate = new VisitDate(date);
        ChristmasDiscountBuilder christmasDiscountBuilder = getChristmasDiscountBuilder(visitDate);

        assertThatThrownBy(christmasDiscountBuilder::getDiscount)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(IllegalStateExceptionType.CANNOT_GET_DISCOUNT.getMessage());
    }
}
