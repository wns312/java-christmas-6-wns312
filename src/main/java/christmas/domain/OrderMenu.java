package christmas.domain;

import christmas.domain.constant.Menu;
import christmas.domain.constant.MenuCategory;
import christmas.exception.IllegalArgumentExceptionType;

public class OrderMenu {
    private static final int MIN_MENU_COUNT = 1;
    private static final int MAX_MENU_COUNT = 20;

    private final Menu menu;
    private final int count;

    public OrderMenu(Menu menu, int count) {
        validateMinCount(count);
        validateMaxCount(count);
        this.menu = menu;
        this.count = count;
    }

    public Menu getMenu() {
        return menu;
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

    public String getMenuName() {
        return menu.getName();
    }

    public int getTotalPayment() {
        return menu.getPrice() * count;
    }

    public boolean isCategoryOf(MenuCategory menuCategory) {
        return menuCategory == menu.getCategory();
    }

    public boolean isNotCategoryOf(MenuCategory menuCategory) {
        return !isCategoryOf(menuCategory);
    }

}
