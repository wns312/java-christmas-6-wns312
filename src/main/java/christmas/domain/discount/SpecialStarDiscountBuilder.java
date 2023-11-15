package christmas.domain.discount;

import christmas.domain.order.Reservation;
import christmas.domain.constant.DecemberEvent;

public class SpecialStarDiscountBuilder extends DiscountBuilder {
    private static final DecemberEvent SPECIAL_EVENT = DecemberEvent.SPECIAL;
    private static final int FIX_DISCOUNT_AMOUNT = -1000;

    public SpecialStarDiscountBuilder(Reservation reservation) {
        super(SPECIAL_EVENT, reservation);
    }

    @Override
    protected boolean isAvailableDate() {
        return reservation.isSunday() || reservation.isChristmas();
    }

    @Override
    protected int getDiscount() {
        validate();

        return FIX_DISCOUNT_AMOUNT;
    }
}
