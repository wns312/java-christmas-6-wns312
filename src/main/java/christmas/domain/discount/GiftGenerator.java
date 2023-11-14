package christmas.domain.discount;

import christmas.domain.order.Menu;
import java.util.List;

public interface GiftGenerator {
    List<Menu> generateGift();
}
