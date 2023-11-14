package christmas.domain.discount.mapper;

import christmas.domain.discount.Discount;
import christmas.domain.discount.dto.DiscountDto;

public class DiscountMapper {
    public static DiscountDto toDto(Discount discount) {
        return new DiscountDto(discount.getDiscountEvent(), discount.getDiscountAmount());
    }
}
