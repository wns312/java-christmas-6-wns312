package christmas.domain;

import christmas.domain.discount.OrderDiscounts;
import java.time.LocalDate;
import java.util.List;

public class DiscountManager {
    private final Reservation reservation;
    private final OrderDiscounts orderDiscounts;

    public DiscountManager(Reservation reservation, OrderDiscounts orderDiscounts) {
        this.reservation = reservation;
        this.orderDiscounts = orderDiscounts;
    }


    public List<EventBenefit> getBenefits() {
        int totalPayment = reservation.getTotalPayment();
        LocalDate visitLocalDate = reservation.getVisitLocalDate();
        List<Menu> orderMenusWithCounts = reservation.getOrderMenus();

        List<EventBenefit> eventBenefits = orderDiscounts.getEventBenefits(totalPayment, visitLocalDate,
                orderMenusWithCounts);

        return eventBenefits.stream()
                .filter(EventBenefit::hasActualBenefits)
                .toList();
    }
}
