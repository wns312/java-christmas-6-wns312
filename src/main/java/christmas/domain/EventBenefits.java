package christmas.domain;

import java.util.List;

public class EventBenefits {

    private final List<EventBenefit> eventBenefits;

    public EventBenefits(List<EventBenefit> eventBenefits) {
        this.eventBenefits = eventBenefits;
    }

    public List<Menu> getGifts() {
        return eventBenefits.stream()
                .flatMap((eventBenefit) -> eventBenefit.getGifts().stream())
                .toList();
    }

    public List<EventBenefit> getEventBenefits() {
        return eventBenefits.stream()
                .filter(EventBenefit::hasActualBenefits)
                .toList();
    }

    public int getDiscountAmount() {
        return eventBenefits.stream()
                .mapToInt(EventBenefit::getDiscountAmount).sum();
    }
}
