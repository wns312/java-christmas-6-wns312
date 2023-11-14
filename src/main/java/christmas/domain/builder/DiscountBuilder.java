package christmas.domain.builder;

import christmas.domain.Reservation;
import christmas.domain.constant.DecemberEvent;
import christmas.exception.IllegalStateExceptionType;

public abstract class DiscountBuilder {
    static final int NO_DISCOUNT = 0;

    final DecemberEvent decemberEvent;
    final Reservation reservation;

    public DiscountBuilder(DecemberEvent decemberEvent, Reservation reservation) {
        this.decemberEvent = decemberEvent;
        this.reservation = reservation;
    }

    void validate() {
        if (!isAvailableDate()) {
            throw IllegalStateExceptionType.CANNOT_GET_DISCOUNT.getException();
        }
    }

    DecemberEvent getDiscountEvent() {
        return decemberEvent;
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
