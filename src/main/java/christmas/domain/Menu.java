package christmas.domain;

import christmas.domain.constant.MenuType;
import christmas.domain.constant.MenuCategory;
import christmas.exception.IllegalArgumentExceptionType;

public class Menu {
    private static final int MIN_MENU_COUNT = 1;
    private static final int MAX_MENU_COUNT = 20;

    private final MenuType menuType;
    private final int count;

    public Menu(MenuType menuType, int count) {
        validateMinCount(count);
        validateMaxCount(count);
        this.menuType = menuType;
        this.count = count;
    }

    public MenuType getMenu() {
        return menuType;
    }

    public int getCount() { return count; }

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

    public int getTotalPayment() {
        return menuType.getPrice() * count;
    }

    public boolean isCategoryOf(MenuCategory menuCategory) {
        return menuCategory == menuType.getCategory();
    }

    public boolean isNotCategoryOf(MenuCategory menuCategory) {
        return !isCategoryOf(menuCategory);
    }
}
