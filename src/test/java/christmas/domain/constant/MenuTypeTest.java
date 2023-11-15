package christmas.domain.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class MenuTypeTest {
    @DisplayName("메뉴 이름 조회 테스트")
    @ParameterizedTest
    @EnumSource(MenuType.class)
    void getMenuNameTest(MenuType menuType) {
        String name = menuType.getName();

        assertThat(MenuType.getByMenuName(name))
                .isEqualTo(menuType);
    }
}
