package christmas.domain.discount;

import christmas.domain.order.Reservation;
import christmas.domain.constant.DecemberEvent;
import christmas.exception.IllegalStateExceptionType;

public abstract class DiscountBuilder {
    static final int NO_DISCOUNT = 0;

    protected final DecemberEvent decemberEvent;
    protected final Reservation reservation;

    public DiscountBuilder(DecemberEvent decemberEvent, Reservation reservation) {
        this.decemberEvent = decemberEvent;
        this.reservation = reservation;
    }

    public DecemberEvent getDiscountEvent() {
        return decemberEvent;
    }

    public int getTotalPayment() {
        return reservation.getTotalPayment();
    }

    public int discount() {
        if (isAvailableDate()) {
            return getDiscount();
        }

        return NO_DISCOUNT;
    }

    protected void validate() {
        if (!isAvailableDate()) {
            throw IllegalStateExceptionType.CANNOT_GET_DISCOUNT.getException();
        }
    }

    abstract protected boolean isAvailableDate();

    abstract protected int getDiscount();
}
