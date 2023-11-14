package christmas.domain.dto;

import christmas.domain.constant.DiscountEvent;

public record DiscountDto(DiscountEvent discountEvent, int discountAmount) {
}
