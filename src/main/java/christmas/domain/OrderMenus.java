package christmas.domain;

import christmas.domain.validation.OrderMenusValidation;
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

    public int getTotalPayment() {
        return elements.stream().mapToInt(Menu::getTotalPayment).sum();
    }

}
