package christmas.domain.discount.dto;

import christmas.domain.constant.DecemberEvent;

public record DiscountDto(DecemberEvent decemberEvent, int discountAmount) {
}
