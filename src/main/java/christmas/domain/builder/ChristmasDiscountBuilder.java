package christmas.domain.builder;

import christmas.domain.Reservation;
import christmas.domain.constant.DecemberEvent;

public class ChristmasDiscountBuilder extends DiscountBuilder {
    private static final DecemberEvent CHRISTMAS_D_DAY_EVENT = DecemberEvent.CHRISTMAS_D_DAY;
    private static final int CHRISTMAS_DATE = 25;
    private static final int BASE_DISCOUNT_AMOUNT = -900;
    private static final int ADDITIONAL_DISCOUNT_AMOUNT = -100;

    public ChristmasDiscountBuilder(Reservation reservation) {
        super(CHRISTMAS_D_DAY_EVENT, reservation);
    }

    @Override
    boolean isAvailableDate() {
        return reservation.getVisitDayOfMonth() <= CHRISTMAS_DATE;
    }

    @Override
    int getDiscount() {
        validate();

        return BASE_DISCOUNT_AMOUNT + (reservation.getVisitDayOfMonth() * ADDITIONAL_DISCOUNT_AMOUNT);
    }
}
