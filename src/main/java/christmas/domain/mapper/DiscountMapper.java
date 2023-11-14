package christmas.domain.mapper;

import christmas.domain.builder.Discount;
import christmas.domain.dto.DiscountDto;

public class DiscountMapper {
    public static DiscountDto toDto(Discount discount) {
        return new DiscountDto(discount.getDiscountEvent(), discount.getDiscountAmount());
    }
}
