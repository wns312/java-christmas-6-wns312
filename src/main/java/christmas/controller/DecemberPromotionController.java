package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.OrderMenus;
import christmas.domain.Reservation;
import christmas.domain.VisitDate;
import christmas.domain.builder.ChristmasDiscountBuilder;
import christmas.domain.builder.Discount;
import christmas.domain.builder.DiscountBuilder;
import christmas.domain.builder.DiscountDirector;
import christmas.domain.builder.SpecialStarDiscountBuilder;
import christmas.domain.builder.WeekDayDiscountBuilder;
import christmas.domain.builder.WeekEndDiscountBuilder;
import christmas.domain.constant.EventBadge;
import christmas.domain.dto.DiscountDto;
import christmas.domain.dto.MenuDto;
import christmas.domain.gift.ChampagneGiftGenerator;
import christmas.domain.mapper.DiscountMapper;
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
        Reservation reservation = createReservation();
        printIntroducingBenefitMessage(reservation.getDate());
        printOrderMenus(reservation.getOrderMenus());
        printTotalPayment(reservation.getTotalPayment());

        DiscountDirector discountDirector = initDiscountDirector(reservation);
        List<Discount> discounts = discountDirector.discountAll();
        List<Menu> gifts = discountDirector.getGifts();

        printGifts(gifts);
        printDiscount(discounts);
        printTotalAmountOfDiscountAndGifts(discounts, gifts);
        printTotalPaymentAfterDiscount(reservation, discounts);
        printBadges(discounts, gifts);
    }

    private Reservation createReservation() {
        VisitDate visitDate = readVisitDate();
        OrderMenus orderMenus = readOrderMenus();

        return new Reservation(visitDate, orderMenus);
    }

    private void printBadges(List<Discount> discounts, List<Menu> gifts) {
        int totalDiscount = discounts.stream().mapToInt(Discount::getDiscountAmount).sum();
        int totalGiftsPrice = gifts.stream().mapToInt(Menu::getTotalPrice).sum();
        EventBadge badge = EventBadge.getBadgeByBenefitAmount(totalGiftsPrice - totalDiscount);

        outputView.printBadge(badge);
    }

    private void printDiscount(List<Discount> discounts) {
        List<DiscountDto> discountsData = discounts.stream().map(DiscountMapper::toDto).toList();
        outputView.printDiscounts(discountsData);
    }

    private void printTotalAmountOfDiscountAndGifts(List<Discount> discounts, List<Menu> gifts) {
        int totalDiscount = discounts.stream().mapToInt(Discount::getDiscountAmount).sum();
        int totalGiftsPrice = gifts.stream().mapToInt(Menu::getTotalPrice).sum();
        outputView.printTotalBenefitAmount(totalDiscount - totalGiftsPrice);
    }


    private void printTotalPaymentAfterDiscount(Reservation reservation, List<Discount> discounts) {
        int totalDiscount = discounts.stream().mapToInt(Discount::getDiscountAmount).sum();
        outputView.printTotalPaymentAfterDiscount(reservation.getTotalPayment() + totalDiscount);
    }

    private DiscountDirector initDiscountDirector(Reservation reservation) {
        List<DiscountBuilder> discountBuilders = initDiscountBuilders(reservation);

        return new DiscountDirector(discountBuilders, new ChampagneGiftGenerator(reservation.getTotalPayment()));
    }

    private List<DiscountBuilder> initDiscountBuilders(Reservation reservation) {
        return List.of(
                new ChristmasDiscountBuilder(reservation),
                new WeekDayDiscountBuilder(reservation),
                new WeekEndDiscountBuilder(reservation),
                new SpecialStarDiscountBuilder(reservation)
        );
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

    private void printIntroducingBenefitMessage(LocalDate date) {
        outputView.printIntroducingBenefitMessage(date.getDayOfMonth());
    }
    private void printOrderMenus(List<Menu> orderOrderMenus) {
        List<MenuDto> orderMenusDto = orderOrderMenus.stream().map(MenuMapper::toDto).toList();

        outputView.printOrderMenus(orderMenusDto);
    }

    private void printTotalPayment(int totalPayment) {
        outputView.printTotalPayment(totalPayment);
    }

    private void printGifts(List<Menu> gifts) {
        List<MenuDto> giftsData = gifts.stream().map(MenuMapper::toDto).toList();
        outputView.printGifts(giftsData);
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
