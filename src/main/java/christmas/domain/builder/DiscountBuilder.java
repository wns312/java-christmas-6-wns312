package christmas.domain.builder;

import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.constant.DiscountEvent;

public abstract class DiscountBuilder {

    protected final DiscountEvent discountEvent;
    protected final VisitDate visitDate;
    protected final OrderMenus orderMenus;

    public DiscountBuilder(DiscountEvent discountEvent, VisitDate visitDate, OrderMenus orderMenus) {
        this.discountEvent = discountEvent;
        this.visitDate = visitDate;
        this.orderMenus = orderMenus;
    }

    public DiscountEvent getDiscountEvent() {
        return discountEvent;
    }

    public int getTotalPayment() {
        return orderMenus.getTotalPayment();
    }

    public abstract boolean isAvailableDate();

    public abstract int getDiscount();
}
