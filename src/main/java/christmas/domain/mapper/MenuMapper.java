package christmas.domain.mapper;

import christmas.domain.Menu;
import christmas.domain.constant.MenuType;
import christmas.domain.dto.MenuDto;
import christmas.exception.IllegalArgumentExceptionType;

public class MenuMapper {
    public static Menu toDomain(MenuDto menuDto) {
        String name = menuDto.name();
        int count = menuDto.count();
        MenuType menuType = convertMenuName(name);
        return new Menu(menuType, count);
    }

    public static MenuDto toDto(Menu menu) {
        MenuType menuType = menu.getMenu();
        String name = menuType.getName();
        int count = menu.getCount();

        return new MenuDto(name, count);
    }

    private static MenuType convertMenuName(String menuName) {
        try {
            return MenuType.getByMenuName(menuName);
        } catch (IllegalArgumentException e) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }
}
