package christmas.domain.discount.factory;

import christmas.domain.discount.ChristmasDiscountBuilder;
import christmas.domain.discount.DiscountBuilder;
import christmas.domain.discount.SpecialStarDiscountBuilder;
import christmas.domain.discount.WeekDayDiscountBuilder;
import christmas.domain.discount.WeekEndDiscountBuilder;
import christmas.domain.order.Reservation;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public enum DiscountFactory {
    CHRISTMAS(ChristmasDiscountBuilder::new),
    SPECIAL(SpecialStarDiscountBuilder::new),
    WEEKDAY(WeekDayDiscountBuilder::new),
    WEEKEND(WeekEndDiscountBuilder::new);

    private final Function<Reservation, DiscountBuilder> builderFunction;

    DiscountFactory(Function<Reservation, DiscountBuilder> builderFunction) {
        this.builderFunction = builderFunction;
    }

    public DiscountBuilder build(Reservation reservation) {
        return builderFunction.apply(reservation);
    }

    public static List<DiscountBuilder> buildAll(Reservation reservation) {
        return Stream.of(values())
                .map(discountFactory -> discountFactory.build(reservation))
                .toList();
    }
}
