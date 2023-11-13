package christmas.domain;

import java.util.List;

public class Benefit {
    private static final int NO_DISCOUNT = 0;

    private final int discountAmount;
    private final List<Menu> gifts;

    public Benefit(int discountAmount, List<Menu> gifts) {
        this.discountAmount = discountAmount;
        this.gifts = gifts;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public List<Menu> getGifts() {
        return gifts;
    }

    private boolean hasDiscount() {
        return discountAmount != NO_DISCOUNT;
    }

    private boolean hasGift() {
        return !gifts.isEmpty();
    }

    public boolean hasDiscountOrGifts() {
        return hasDiscount() || hasGift();
    }

    public int getTotalGiftCost() {
        return gifts.stream().mapToInt(Menu::getTotalPayment).sum();
    }

    public int getTotalBenefitAmount() {
        return getDiscountAmount() + getTotalGiftCost();
    }

}
