package christmas.domain.builder;

import christmas.domain.Menu;
import christmas.domain.gift.GiftGenerator;
import java.util.List;

public class DiscountDirector {
    private static final int NO_DISCOUNT = 0;
    private static final int MINIMUM_PAYMENT_AMOUNT = 10000;

    private final List<DiscountBuilder> discountBuilders;
    private final GiftGenerator giftGenerator;

    public DiscountDirector(List<DiscountBuilder> discountBuilders, GiftGenerator giftGenerator) {
        this.discountBuilders = discountBuilders;
        this.giftGenerator = giftGenerator;
    }

    private boolean isAvailablePayment(DiscountBuilder discountBuilder) {
        return discountBuilder.getTotalPayment() >= MINIMUM_PAYMENT_AMOUNT;
    }

    private int discount(DiscountBuilder discountBuilder) {
        if (isAvailablePayment(discountBuilder)) {
            return discountBuilder.discount();
        }

        return NO_DISCOUNT;
    }

    public List<Discount> discountAll() {
        return discountBuilders.stream()
                .map(discountBuilder -> new Discount(discountBuilder.getDiscountEvent(), discount(discountBuilder)))
                .filter(Discount::hasDiscount)
                .toList();
    }

    public List<Menu> getGifts() { return giftGenerator.generateGift(); }
}
