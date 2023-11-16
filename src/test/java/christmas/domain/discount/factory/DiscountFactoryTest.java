package christmas.domain.discount.factory;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.MenuType;
import christmas.domain.discount.ChristmasDiscountBuilder;
import christmas.domain.discount.DiscountBuilder;
import christmas.domain.discount.SpecialStarDiscountBuilder;
import christmas.domain.discount.WeekDayDiscountBuilder;
import christmas.domain.discount.WeekEndDiscountBuilder;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountFactoryTest {
    @DisplayName("할인 객체 생성 테스트")
    @Test
    void getDiscountBuildersTest() {
        OrderMenus orderMenus = new OrderMenus(List.of(
                new OrderMenu(MenuType.BARBECUED_RIBS, 1)
        ));
        Reservation reservation = new Reservation(new VisitDate(25), orderMenus);

        List<DiscountBuilder> discountBuilders = DiscountFactory.buildAll(reservation);

        assertThat(discountBuilders).hasExactlyElementsOfTypes(ChristmasDiscountBuilder.class,
                SpecialStarDiscountBuilder.class, WeekDayDiscountBuilder.class, WeekEndDiscountBuilder.class);
    }
}
