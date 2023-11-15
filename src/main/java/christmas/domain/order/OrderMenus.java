package christmas.domain.order;

import christmas.domain.order.validation.OrderMenusValidation;
import java.util.Collections;
import java.util.List;

public class OrderMenus {
    private final List<Menu> elements;

    public OrderMenus(List<Menu> elements) {
        OrderMenusValidation.validate(elements);
        this.elements = elements;
    }

    public List<Menu> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public int getDessertMenuCount() {
        return elements.stream()
                .filter(Menu::isDessert)
                .mapToInt(Menu::getCount)
                .sum();
    }

    public int getMainMenuCount() {
        return elements.stream()
                .filter(Menu::isMain)
                .mapToInt(Menu::getCount)
                .sum();
    }

    public int getTotalPayment() {
        return elements.stream().mapToInt(Menu::getTotalPrice).sum();
    }
}
