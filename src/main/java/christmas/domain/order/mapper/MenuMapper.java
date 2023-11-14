package christmas.domain.order.mapper;

import christmas.domain.order.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.constant.MenuType;
import christmas.domain.order.dto.MenuDto;
import christmas.exception.IllegalArgumentExceptionType;

public class MenuMapper {
    public static Menu toDomain(MenuDto menuDto) {
        String name = menuDto.name();
        int count = menuDto.count();
        MenuType menuType = convertMenuName(name);
        return new OrderMenu(menuType, count);
    }

    public static MenuDto toDto(Menu orderMenu) {
        MenuType menuType = orderMenu.getMenuType();
        String name = menuType.getName();
        int count = orderMenu.getCount();

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
