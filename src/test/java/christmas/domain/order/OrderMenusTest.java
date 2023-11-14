package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.constant.MenuType;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import christmas.exception.IllegalArgumentExceptionType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenusTest {

    @DisplayName("메뉴 개수 성공 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void createOrderMenusSuccessTest(int count) {
        OrderMenu barbecuedRibs = new OrderMenu(MenuType.BARBECUED_RIBS, count);
        OrderMenu caesarSalad = new OrderMenu(MenuType.CAESAR_SALAD, count);
        assertThatCode(() -> new OrderMenus(List.of(barbecuedRibs, caesarSalad)))
                .doesNotThrowAnyException();

    }

    @DisplayName("메뉴 최소 개수 실패 테스트")
    @Test
    void createZeroOrderMenuAmountTest() {
        assertThatThrownBy(() -> new OrderMenus(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.INVALID_ORDERING.getMessage());

    }

    @DisplayName("메뉴 최대 개수 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {11})
    void createExceededOrderMenuAmountTest(int count) {
        OrderMenu barbecuedRibs = new OrderMenu(MenuType.BARBECUED_RIBS, count);
        OrderMenu caesarSalad = new OrderMenu(MenuType.CAESAR_SALAD, count);
        assertThatThrownBy(() -> new OrderMenus(List.of(barbecuedRibs, caesarSalad)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.INVALID_ORDERING.getMessage());

    }

    @DisplayName("중복 메뉴 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void createDuplicatedOrderMenus(int count) {
        OrderMenu barbecuedRibs = new OrderMenu(MenuType.BARBECUED_RIBS, count);
        assertThatThrownBy(() -> new OrderMenus(List.of(barbecuedRibs, barbecuedRibs)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.INVALID_ORDERING.getMessage());

    }

    @DisplayName("음료 메뉴만 있는 주문 실패 테스트")
    @ParameterizedTest
    @EnumSource(value = MenuType.class, names = {"ZERO_COKE", "RED_WINE", "CHAMPAGNE"})
    void createOnlyBeverageFailTest(MenuType menuType) {
        int count = 1;
        OrderMenu beverage = new OrderMenu(menuType, count);

        assertThatThrownBy(() -> new OrderMenus(List.of(beverage)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.INVALID_ORDERING.getMessage());

    }

    @DisplayName("메뉴 가격 합 메소드 테스트")
    @Test
    void getTotalPaymentTest() {
        MenuType barbecuedRibs = MenuType.BARBECUED_RIBS;
        MenuType caesarSalad = MenuType.CAESAR_SALAD;
        MenuType seafoodPasta = MenuType.SEAFOOD_PASTA;
        int barbecuedRibsCount = 2;
        int caesarSaladCount = 5;
        int seafoodPastaCount = 7;

        OrderMenu barbecuedRibsOrder = new OrderMenu(barbecuedRibs, barbecuedRibsCount);
        OrderMenu caesarSaladOrder = new OrderMenu(caesarSalad, caesarSaladCount);
        OrderMenu seafoodPastaOrder = new OrderMenu(seafoodPasta, seafoodPastaCount);
        OrderMenus orderMenus = new OrderMenus(List.of(barbecuedRibsOrder, caesarSaladOrder, seafoodPastaOrder));
        assertThat(orderMenus.getTotalPayment())
                .isEqualTo(
                        (barbecuedRibs.getPrice() * barbecuedRibsCount)
                                + (caesarSalad.getPrice() * caesarSaladCount)
                                + (seafoodPasta.getPrice() * seafoodPastaCount)
                );

    }
}