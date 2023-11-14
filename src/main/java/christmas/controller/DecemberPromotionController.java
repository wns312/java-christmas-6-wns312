package christmas.controller;

import christmas.domain.discount.factory.DiscountFactory;
import christmas.domain.result.EventResult;
import christmas.domain.order.Menu;
import christmas.domain.order.OrderMenus;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import christmas.domain.discount.Discount;
import christmas.domain.discount.DiscountBuilder;
import christmas.domain.discount.DiscountDirector;
import christmas.domain.constant.EventBadge;
import christmas.domain.discount.dto.DiscountDto;
import christmas.domain.order.dto.MenuDto;
import christmas.domain.discount.ChampagneGiftGenerator;
import christmas.domain.discount.mapper.DiscountMapper;
import christmas.domain.order.mapper.MenuMapper;
import christmas.view.InputView;
import christmas.view.OutputView;
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
        Reservation reservation = createReservation();
        EventResult eventResult = createEventResult(reservation);
        printOrderInformation(reservation);
        printEventResult(reservation, eventResult);
    }

    private Reservation createReservation() {
        return new Reservation(readVisitDate(), readOrderMenus());
    }

    private VisitDate readVisitDate() {
        outputView.printIntroducingPlannerMessage();
        return repeatToReadBeforeSuccess(() -> new VisitDate(inputView.readDateOfVisit()));
    }

    private OrderMenus readOrderMenus() {
        return repeatToReadBeforeSuccess(() -> {
            List<MenuDto> orderMenusData = inputView.readOrderMenus();
            List<Menu> orderMenus = orderMenusData.stream().map(MenuMapper::toDomain).toList();

            return new OrderMenus(orderMenus);
        });
    }

    private EventResult createEventResult(Reservation reservation) {
        DiscountDirector discountDirector = initDiscountDirector(reservation);
        return new EventResult(discountDirector.discountAll(), discountDirector.getGifts());
    }

    private DiscountDirector initDiscountDirector(Reservation reservation) {
        List<DiscountBuilder> discountBuilders = DiscountFactory.buildAll(reservation);
        return new DiscountDirector(discountBuilders, new ChampagneGiftGenerator(reservation.getTotalPayment()));
    }

    private void printOrderInformation(Reservation reservation) {
        outputView.printIntroducingBenefitMessage(reservation.getVisitDayOfMonth());
        printOrderMenus(reservation.getOrderMenus());
        outputView.printTotalPayment(reservation.getTotalPayment());
    }

    private void printOrderMenus(List<Menu> orderOrderMenus) {
        List<MenuDto> orderMenusDto = orderOrderMenus.stream().map(MenuMapper::toDto).toList();
        outputView.printOrderMenus(orderMenusDto);
    }

    private void printEventResult(Reservation reservation, EventResult eventResult) {
        int totalPaymentAfterDiscount = eventResult.getTotalPaymentAfterDiscount(reservation.getTotalPayment());

        printGifts(eventResult.getGifts());
        printDiscount(eventResult.getDiscounts());

        outputView.printTotalBenefitAmount(eventResult.getTotalBenefitAmount());
        outputView.printTotalPaymentAfterDiscount(totalPaymentAfterDiscount);
        printBadges(eventResult.getTotalBenefitAmount());
    }

    private void printGifts(List<Menu> gifts) {
        List<MenuDto> giftsData = gifts.stream().map(MenuMapper::toDto).toList();
        outputView.printGifts(giftsData);
    }

    private void printDiscount(List<Discount> discounts) {
        List<DiscountDto> discountsData = discounts.stream().map(DiscountMapper::toDto).toList();
        outputView.printDiscounts(discountsData);
    }

    private void printBadges(int totalBenefitAmount) {
        EventBadge badge = EventBadge.getBadgeByBenefitAmount(totalBenefitAmount);
        outputView.printBadge(badge);
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
