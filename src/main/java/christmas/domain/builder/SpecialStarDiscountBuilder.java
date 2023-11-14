package christmas.domain.builder;

import christmas.domain.Reservation;
import christmas.domain.constant.DiscountEvent;

public class SpecialStarDiscountBuilder extends DiscountBuilder {
    private static final DiscountEvent SPECIAL_EVENT = DiscountEvent.SPECIAL;
    private static final int FIX_DISCOUNT_AMOUNT = -1000;

    public SpecialStarDiscountBuilder(Reservation reservation) {
        super(SPECIAL_EVENT, reservation);
    }

    @Override
    boolean isAvailableDate() {
        return reservation.isVisitDaySunday() || reservation.isVisitDayChristmas();
    }

    @Override
    int getDiscount() {
        validate();

        return FIX_DISCOUNT_AMOUNT;
    }
}
