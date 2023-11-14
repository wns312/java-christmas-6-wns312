package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.constant.MenuType;
import christmas.exception.IllegalArgumentExceptionType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenusTest {

    @DisplayName("메뉴 개수 성공 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void createOrderMenusSuccessTest(int count) {
        OrderMenu barbecuedRibs = new OrderMenu(MenuType.BARBECUED_RIBS, count);
        OrderMenu caesarSalad = new OrderMenu(MenuType.CAESAR_SALAD, count);
        assertThatCode(() -> new OrderMenus(List.of(barbecuedRibs, caesarSalad)))
                .doesNotThrowAnyException();

    }

    @DisplayName("메뉴 개수 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {11})
    void createOrderMenusFailTest(int count) {
        OrderMenu barbecuedRibs = new OrderMenu(MenuType.BARBECUED_RIBS, count);
        OrderMenu caesarSalad = new OrderMenu(MenuType.CAESAR_SALAD, count);
        assertThatThrownBy(() -> new OrderMenus(List.of(barbecuedRibs, caesarSalad)))
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