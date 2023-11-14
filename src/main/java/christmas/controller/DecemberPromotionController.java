package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.builder.ChristmasDiscountBuilder;
import christmas.domain.builder.Discount;
import christmas.domain.builder.DiscountBuilder;
import christmas.domain.builder.DiscountDirector;
import christmas.domain.builder.SpecialStarDiscountBuilder;
import christmas.domain.builder.WeekDayDiscountBuilder;
import christmas.domain.builder.WeekEndDiscountBuilder;
import christmas.domain.constant.DecemberEventBadge;
import christmas.domain.constant.DiscountEvent;
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
        VisitDate visitDate = readVisitDate();
        OrderMenus orderMenus = readOrderMenus();

        printIntroducingBenefitMessage(visitDate.getLocalDate());
        printOrderMenus(orderMenus.getElements());
        printTotalPayment(orderMenus.getTotalPayment());

        DiscountDirector discountDirector = initDiscountDirector(visitDate, orderMenus);
        List<Discount> discounts = discountDirector.discountAll();
        List<Menu> gifts = discountDirector.getGifts();

        printGifts(gifts);
        printDiscount(discounts);
        printTotalAmountOfDiscountAndGifts(discounts, gifts);
        printTotalPaymentAfterDiscount(orderMenus, discounts);
        printBadges(discounts, gifts);
    }

    private void printBadges(List<Discount> discounts, List<Menu> gifts) {
        int totalDiscount = discounts.stream().mapToInt(Discount::getDiscountAmount).sum();
        int totalGiftsPrice = gifts.stream().mapToInt(Menu::getTotalPrice).sum();
        DecemberEventBadge badge = DecemberEventBadge.getBadgeByBenefitAmount(totalGiftsPrice - totalDiscount);

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


    private void printTotalPaymentAfterDiscount(OrderMenus orderMenus, List<Discount> discounts) {
        int totalDiscount = discounts.stream().mapToInt(Discount::getDiscountAmount).sum();
        outputView.printTotalPaymentAfterDiscount(orderMenus.getTotalPayment() + totalDiscount);
    }

    private DiscountDirector initDiscountDirector(VisitDate visitDate, OrderMenus orderMenus) {
        List<DiscountBuilder> discountBuilders = initDiscountBuilders(visitDate, orderMenus);

        return new DiscountDirector(discountBuilders, new ChampagneGiftGenerator(orderMenus.getTotalPayment()));
    }

    private List<DiscountBuilder> initDiscountBuilders(VisitDate visitDate, OrderMenus orderMenus) {
        return List.of(
                new ChristmasDiscountBuilder(DiscountEvent.CHRISTMAS_D_DAY, visitDate, orderMenus),
                new WeekDayDiscountBuilder(DiscountEvent.WEEKDAY, visitDate, orderMenus),
                new WeekEndDiscountBuilder(DiscountEvent.WEEKEND, visitDate, orderMenus),
                new SpecialStarDiscountBuilder(DiscountEvent.SPECIAL, visitDate, orderMenus)
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
