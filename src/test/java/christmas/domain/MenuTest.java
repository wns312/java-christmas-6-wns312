package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.constant.MenuType;
import christmas.exception.IllegalArgumentExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @DisplayName("메뉴 객체 생성 성공 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 20})
    void createOrderMenuSuccessTest(int count) {
        assertThatCode(() -> new Menu(MenuType.BARBECUED_RIBS, count))
                .doesNotThrowAnyException();
    }

    @DisplayName("메뉴 객체 생성 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 21, Integer.MAX_VALUE})
    void createOrderMenuFailTest(int count) {
        assertThatThrownBy(() -> new Menu(MenuType.BARBECUED_RIBS, count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.INVALID_ORDERING.getMessage());
    }


    @DisplayName("메뉴 가격 합 메소드 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 8, 9, 10, 18, 19, 20})
    void getTotalPaymentTest(int count) {
        MenuType barbecuedRibs = MenuType.BARBECUED_RIBS;
        Menu menu = new Menu(barbecuedRibs, count);
        assertThat(menu.getTotalPayment())
                .isEqualTo(barbecuedRibs.getPrice() * count);

    }


    @DisplayName("메뉴 이름 조회 테스트")
    @ParameterizedTest
    @EnumSource(MenuType.class)
    void checkOrderMenusName(MenuType menuType) {
        int count = 1;
        Menu menu = new Menu(menuType, count);

        assertThat(menu.getMenu()).isEqualTo(menuType);
    }

    @DisplayName("메뉴 카테고리 일치 테스트")
    @ParameterizedTest
    @EnumSource(MenuType.class)
    void checkIsSameCategory(MenuType menuType) {
        int count = 1;
        Menu menu = new Menu(menuType, count);

        assertThat(menu.isCategoryOf(menuType.getCategory()))
                .isTrue();

    }

    @DisplayName("메뉴 카테고리 불일치 테스트")
    @ParameterizedTest
    @EnumSource(MenuType.class)
    void checkIsNotSameCategory(MenuType menuType) {
        int count = 1;
        Menu menu = new Menu(menuType, count);

        assertThat(menu.isNotCategoryOf(menuType.getCategory()))
                .isFalse();

    }

}