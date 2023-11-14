package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.constant.MenuType;
import christmas.domain.order.OrderMenu;
import christmas.exception.IllegalArgumentExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @DisplayName("메뉴 객체 생성 성공 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20})
    void createOrderMenuSuccessTest(int count) {
        assertThatCode(() -> new OrderMenu(MenuType.BARBECUED_RIBS, count))
                .doesNotThrowAnyException();
    }

    @DisplayName("메뉴 객체 생성 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 21, Integer.MAX_VALUE})
    void createOrderMenuFailTest(int count) {
        assertThatThrownBy(() -> new OrderMenu(MenuType.BARBECUED_RIBS, count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.INVALID_ORDERING.getMessage());
    }

    @DisplayName("메뉴 이름 조회 테스트")
    @ParameterizedTest
    @EnumSource(MenuType.class)
    void checkOrderMenusName(MenuType menuType) {
        int count = 1;
        OrderMenu orderMenu = new OrderMenu(menuType, count);

        assertThat(orderMenu.getMenuType()).isEqualTo(menuType);
    }

    @DisplayName("메뉴 이름 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20})
    void checkOrderMenuCount(int count) {
        OrderMenu orderMenu = new OrderMenu(MenuType.BARBECUED_RIBS, count);

        assertThat(orderMenu.getCount()).isEqualTo(count);
    }

    @DisplayName("메뉴 가격 합 메소드 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20})
    void getTotalPaymentTest(int count) {
        MenuType barbecuedRibs = MenuType.BARBECUED_RIBS;
        OrderMenu orderMenu = new OrderMenu(barbecuedRibs, count);
        assertThat(orderMenu.getTotalPrice())
                .isEqualTo(barbecuedRibs.getPrice() * count);

    }

}