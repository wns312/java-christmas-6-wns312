package christmas.domain;

import christmas.domain.builder.Discount;
import java.util.List;

public class EventResult {
    private final List<Discount> discounts;
    private final List<Menu> gifts;

    public EventResult(List<Discount> discounts, List<Menu> gifts) {
        this.discounts = discounts;
        this.gifts = gifts;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public List<Menu> getGifts() {
        return gifts;
    }

    public int getTotalDiscount() {
        return discounts.stream().mapToInt(Discount::getDiscountAmount).sum();
    }

    public int getTotalGiftsPrice() {
        return gifts.stream().mapToInt(Menu::getTotalPrice).sum();
    }

    public int getTotalBenefitAmount() {
        return getTotalGiftsPrice() - getTotalDiscount();
    }

    public int getTotalPaymentAfterDiscount(int payment) {
        return payment + getTotalDiscount();
    }
}
