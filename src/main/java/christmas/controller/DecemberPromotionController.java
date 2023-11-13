package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.OrderMenus;
import christmas.domain.Reservation;
import christmas.domain.VisitDate;
import christmas.domain.dto.MenuDto;
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
