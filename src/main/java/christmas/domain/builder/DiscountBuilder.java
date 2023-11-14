package christmas.domain.builder;

import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.constant.DiscountEvent;

public abstract class DiscountBuilder {
    static final int NO_DISCOUNT = 0;

    final DiscountEvent discountEvent;
    final VisitDate visitDate;
    final OrderMenus orderMenus;

    public DiscountBuilder(DiscountEvent discountEvent, VisitDate visitDate, OrderMenus orderMenus) {
        this.discountEvent = discountEvent;
        this.visitDate = visitDate;
        this.orderMenus = orderMenus;
    }

    DiscountEvent getDiscountEvent() {
        return discountEvent;
    }

    int getTotalPayment() {
        return orderMenus.getTotalPayment();
    }

    int discount() {
        if (isAvailableDate()) {
            return getDiscount();
        }

        return NO_DISCOUNT;
    }

    abstract boolean isAvailableDate();

    abstract int getDiscount();
}
