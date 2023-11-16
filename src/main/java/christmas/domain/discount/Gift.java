package christmas.domain.discount;

import christmas.domain.order.Menu;
import christmas.domain.constant.MenuType;

public class Gift extends Menu {
    public Gift(MenuType menuType, int count) {
        super(menuType, count);
    }
}
