package christmas.domain.mapper;

import christmas.domain.OrderMenu;
import christmas.domain.constant.Menu;
import christmas.domain.dto.OrderMenuDto;
import christmas.exception.IllegalArgumentExceptionType;

public class OrderMenuMapper {
    public static OrderMenu toDomain(OrderMenuDto orderMenuDto) {
        String name = orderMenuDto.name();
        int count = orderMenuDto.count();
        Menu menu = convertMenuName(name);
        return new OrderMenu(menu, count);
    }

    public static OrderMenuDto toDto(OrderMenu orderMenu) {
        String name = orderMenu.getMenuName();
        int count = orderMenu.getCount();

        return new OrderMenuDto(name, count);
    }

    private static Menu convertMenuName(String menuName) {
        try {
            return Menu.getByMenuName(menuName);
        } catch (IllegalArgumentException e) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }
}
