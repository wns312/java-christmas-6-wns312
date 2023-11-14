package christmas.domain;

import christmas.domain.constant.MenuCategory;
import christmas.domain.constant.MenuType;
import christmas.exception.IllegalArgumentExceptionType;

public class OrderMenu extends Menu {
    private static final int MIN_MENU_COUNT = 1;
    private static final int MAX_MENU_COUNT = 20;

    public OrderMenu(MenuType menuType, int count) {
        super(menuType, count);
        validateMinCount(count);
        validateMaxCount(count);
    }

    private void validateMinCount(int count) {
        if (count < MIN_MENU_COUNT) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }
    private void validateMaxCount(int count) {
        if (count > MAX_MENU_COUNT) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }
}
