package christmas.domain.builder;

import christmas.domain.Reservation;
import christmas.domain.constant.DiscountEvent;
import christmas.exception.IllegalStateExceptionType;

public abstract class DiscountBuilder {
    static final int NO_DISCOUNT = 0;

    final DiscountEvent discountEvent;
    final Reservation reservation;

    public DiscountBuilder(DiscountEvent discountEvent, Reservation reservation) {
        this.discountEvent = discountEvent;
        this.reservation = reservation;
    }

    void validate() {
        if (!isAvailableDate()) {
            throw IllegalStateExceptionType.CANNOT_GET_DISCOUNT.getException();
        }
    }

    DiscountEvent getDiscountEvent() {
        return discountEvent;
    }

    int getTotalPayment() {
        return reservation.getTotalPayment();
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
