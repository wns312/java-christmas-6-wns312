package christmas.domain.discount;

import christmas.domain.EventBenefit;
import christmas.domain.Menu;
import java.time.LocalDate;
import java.util.List;

public class OrderDiscounts {
    private final List<OrderDiscount> orderDiscounts;

    public OrderDiscounts(List<OrderDiscount> orderDiscounts) {
        this.orderDiscounts = orderDiscounts;
    }

    public List<EventBenefit> getEventBenefits(int payment, LocalDate date, List<Menu> menus) {
        return orderDiscounts.stream()
                .map(orderDiscount -> orderDiscount.calculateEventBenefit(payment, date, menus))
                .toList();
    }
}
