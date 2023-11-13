package christmas.controller;

import christmas.domain.DiscountManager;
import christmas.domain.EventBenefit;
import christmas.domain.EventBenefits;
import christmas.domain.Menu;
import christmas.domain.OrderMenus;
import christmas.domain.Reservation;
import christmas.domain.VisitDate;
import christmas.domain.constant.DecemberEventBadge;
import christmas.domain.discount.ChampagneGiftDiscount;
import christmas.domain.discount.ChristmasDayDiscount;
import christmas.domain.discount.OrderDiscount;
import christmas.domain.discount.OrderDiscounts;
import christmas.domain.discount.SpecialStarDiscount;
import christmas.domain.discount.WeekDayDiscount;
import christmas.domain.discount.WeekEndDiscount;
import christmas.domain.dto.EventBenefitDto;
import christmas.domain.dto.MenuDto;
import christmas.domain.mapper.EventBenefitMapper;
import christmas.domain.mapper.MenuMapper;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

public class DecemberPromotionController {
    private final InputView inputView;
    private final OutputView outputView;

    public DecemberPromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Reservation reservation = makeReservation();

        printIntroducingBenefitMessage(reservation.getVisitLocalDate());
        printOrderMenus(reservation.getOrderMenus());
        printTotalPayment(reservation.getTotalPayment());

        OrderDiscounts orderDiscounts = initDiscounts();
        DiscountManager discountManager = new DiscountManager(reservation, orderDiscounts);
        List<EventBenefit> benefits = discountManager.getBenefits();
        EventBenefits eventBenefitStatistics = new EventBenefits(benefits);


        printGifts(eventBenefitStatistics.getGifts());
        printEventBenefits(eventBenefitStatistics.getEventBenefits());
        printEventBenefitsTotalAmount(eventBenefitStatistics.getEventBenefits());
    }

    private Reservation makeReservation() {
        VisitDate visitDate = readVisitDate();
        OrderMenus orderMenus = readOrderMenus();

        return new Reservation(visitDate, orderMenus);
    }

    private VisitDate readVisitDate() {
        outputView.printIntroducingPlannerMessage();

        return repeatToReadBeforeSuccess(() -> new VisitDate(inputView.readDateOfVisit()));
    }

    private OrderMenus readOrderMenus() {
        return repeatToReadBeforeSuccess(() -> {
            List<MenuDto> orderMenusData = inputView.readOrderMenus();
            List<Menu> menus = orderMenusData.stream().map(MenuMapper::toDomain).toList();

            return new OrderMenus(menus);
        });
    }

    private OrderDiscounts initDiscounts() {
        List<OrderDiscount> orderDiscounts = List.of(new ChristmasDayDiscount(), new WeekDayDiscount(),
                new WeekEndDiscount(), new SpecialStarDiscount(),
                new ChampagneGiftDiscount());

        return new OrderDiscounts(orderDiscounts);
    }

    private void printIntroducingBenefitMessage(LocalDate date) {
        outputView.printIntroducingBenefitMessage(date.getDayOfMonth());
    }
    private void printOrderMenus(List<Menu> orderMenus) {
        List<MenuDto> orderMenusDto = orderMenus.stream().map(MenuMapper::toDto).toList();

        outputView.printOrderMenus(orderMenusDto);
    }

    private void printTotalPayment(int totalPayment) {
        outputView.printTotalPayment(totalPayment);
    }

    private void printGifts(List<Menu> gifts) {
        List<MenuDto> giftsDto = gifts.stream().map(MenuMapper::toDto).toList();
        outputView.printGifts(giftsDto);
    }

    private void printEventBenefits(List<EventBenefit> eventBenefits) {
        List<EventBenefitDto> eventBenefitsDto = eventBenefits.stream().map(EventBenefitMapper::toDto).toList();
        outputView.printEventBenefits(eventBenefitsDto);
    }

    private void printEventBenefitsTotalAmount(List<EventBenefit> eventBenefits) {
        int sum = eventBenefits.stream().mapToInt(EventBenefit::getBenefitAmount).sum();
        outputView.printEventBenefitsAmount(sum);
    }


    private <R> R repeatToReadBeforeSuccess(Supplier<R> reader) {
        while (true) {
            try {
                return reader.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
