package christmas.domain.gift;

import christmas.domain.Menu;
import christmas.domain.constant.MenuCategory;
import christmas.domain.constant.MenuType;
import christmas.exception.IllegalArgumentExceptionType;

public class Gift extends Menu {
    public Gift(MenuType menuType, int count) {
        super(menuType, count);
    }
}
