package christmas.domain;

import christmas.domain.constant.DiscountEvent;
import java.util.List;

public class EventBenefit {
    private final DiscountEvent discountEvent;
    private final Benefit benefit;

    public EventBenefit(DiscountEvent discountEvent, Benefit benefit) {
        this.discountEvent = discountEvent;
        this.benefit = benefit;
    }

    public boolean hasActualBenefits() {
        return benefit.hasDiscountOrGifts();
    }

    public DiscountEvent getDiscountEventType() {
        return discountEvent;
    }

    public List<Menu> getGifts() {
        return benefit.getGifts();
    }

    public int getBenefitAmount() {
        return benefit.getTotalBenefitAmount();
    }

}
