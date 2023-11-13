package christmas.domain.mapper;

import christmas.domain.EventBenefit;
import christmas.domain.Menu;
import christmas.domain.constant.DiscountEvent;
import christmas.domain.constant.MenuType;
import christmas.domain.dto.EventBenefitDto;
import christmas.domain.dto.MenuDto;
import christmas.exception.IllegalArgumentExceptionType;

public class EventBenefitMapper {


    public static EventBenefitDto toDto(EventBenefit eventBenefit) {
        DiscountEvent discountEventType = eventBenefit.getDiscountEventType();
        String eventName = discountEventType.getEventName();
        int benefitAmount = eventBenefit.getBenefitAmount();

        return new EventBenefitDto(eventName, benefitAmount);
    }

}
