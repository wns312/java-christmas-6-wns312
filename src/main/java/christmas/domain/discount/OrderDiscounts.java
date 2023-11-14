package christmas.domain.discount;

import christmas.domain.EventBenefit;
import christmas.domain.EventBenefits;
import christmas.domain.Menu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import java.time.LocalDate;
import java.util.List;

public class OrderDiscounts {
    private final List<OrderDiscount> orderDiscounts;

    public OrderDiscounts(List<OrderDiscount> orderDiscounts) {
        this.orderDiscounts = orderDiscounts;
    }

    public EventBenefits getEventBenefits(VisitDate visitDate, OrderMenus orderMenus) {
        int payment = orderMenus.getTotalPayment();
        LocalDate date = visitDate.getLocalDate();
        List<Menu> menus = orderMenus.getElements();

        List<EventBenefit> eventBenefits = orderDiscounts.stream()
                .map(orderDiscount -> orderDiscount.calculateEventBenefit(payment, date, menus))
                .filter(EventBenefit::hasActualBenefits)
                .toList();

        return new EventBenefits(eventBenefits);
    }
}
