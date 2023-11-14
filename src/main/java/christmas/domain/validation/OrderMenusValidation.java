package christmas.domain.validation;

import christmas.domain.Menu;
import christmas.domain.OrderMenu;
import christmas.domain.constant.MenuType;
import christmas.exception.IllegalArgumentExceptionType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderMenusValidation {
    private static final int MIN_ORDER_AMOUNT = 1;
    private static final int MAX_ORDER_AMOUNT = 20;

    public static void validate(List<Menu> elements) {
        validateDuplicatedMenu(elements);
        validateIsBeverageOnly(elements);

        int totalCount = elements.stream().mapToInt(Menu::getCount).sum();
        validateMinAmount(totalCount);
        validateMaxAmount(totalCount);
    }

    private static void validateMinAmount(int totalCount) {
        if (totalCount < MIN_ORDER_AMOUNT) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }

    private static void validateMaxAmount(int totalCount) {

        if (totalCount > MAX_ORDER_AMOUNT) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }

    private static void validateDuplicatedMenu(List<Menu> elements) {
        Set<MenuType> menus = new HashSet<>();
        elements.forEach((orderMenu) -> menus.add(orderMenu.getMenuType()));

        if (menus.size() != elements.size()) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }

    private static void validateIsBeverageOnly(List<Menu> elements) {
        elements.stream()
                .filter(orderMenu -> !orderMenu.isBeverage())
                .findFirst()
                .orElseThrow(IllegalArgumentExceptionType.INVALID_ORDERING::getException);
    }
}
