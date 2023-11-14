package christmas.domain.order;

import christmas.domain.constant.MenuCategory;
import christmas.domain.constant.MenuType;

public abstract class Menu {
    protected final MenuType menuType;
    protected final int count;

    public Menu(MenuType menuType, int count) {
        this.menuType = menuType;
        this.count = count;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public int getCount() { return count; }

    public int getTotalPrice() {
        return menuType.getPrice() * count;
    }

    public boolean isDessert() { return menuType.getCategory() == MenuCategory.DESSERT; }

    public boolean isMain() { return menuType.getCategory() == MenuCategory.MAIN; }

    public boolean isBeverage() { return menuType.getCategory() == MenuCategory.BEVERAGE; }
}
