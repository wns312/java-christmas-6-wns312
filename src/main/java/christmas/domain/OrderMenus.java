package christmas.domain;

import christmas.domain.validation.OrderMenusValidation;
import java.util.Collections;
import java.util.List;

public class OrderMenus {
    private final List<OrderMenu> elements;

    public OrderMenus(List<OrderMenu> elements) {
        OrderMenusValidation.validate(elements);
        this.elements = elements;
    }

    public List<OrderMenu> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public int getTotalPayment() {
        return elements.stream().mapToInt(OrderMenu::getTotalPayment).sum();
    }

}
