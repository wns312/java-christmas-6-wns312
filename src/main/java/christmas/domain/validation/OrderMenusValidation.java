package christmas.domain.validation;

import christmas.domain.OrderMenu;
import christmas.domain.constant.MenuCategory;
import christmas.exception.IllegalArgumentExceptionType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderMenusValidation {
    private static final int MIN_ORDER_AMOUNT = 1;
    private static final int MAX_ORDER_AMOUNT = 20;

    public static void validate(List<OrderMenu> elements) {
        validateDuplicatedMenu(elements);
        validateIsBeverageOnly(elements);

        int totalCount = elements.stream().mapToInt(OrderMenu::getCount).sum();
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

    private static void validateDuplicatedMenu(List<OrderMenu> elements) {
        Set<String> menus = new HashSet<>();
        elements.forEach((orderMenu) -> menus.add(orderMenu.getMenuName()));

        if (menus.size() != elements.size()) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }

    private static void validateIsBeverageOnly(List<OrderMenu> elements) {
        elements.stream()
                .filter(orderMenu -> orderMenu.isNotCategoryOf(MenuCategory.BEVERAGE))
                .findFirst()
                .orElseThrow(IllegalArgumentExceptionType.INVALID_ORDERING::getException);
    }
}
