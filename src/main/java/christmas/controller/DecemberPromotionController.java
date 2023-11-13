package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.OrderMenus;
import christmas.domain.VisitDate;
import christmas.domain.dto.MenuDto;
import christmas.domain.mapper.MenuMapper;
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
        VisitDate visitDate = readVisitDate();
        OrderMenus orderMenus = readOrderMenus();

    }

    private VisitDate readVisitDate() {
        outputView.printIntroducingPlannerMessage();

        return repeatToReadBeforeSuccess(() -> new VisitDate(inputView.readDateOfVisit()));
    }

    private OrderMenus readOrderMenus() {
        return repeatToReadBeforeSuccess(() -> {
            List<OrderMenuDto> orderMenusData = inputView.readOrderMenus();
            List<OrderMenu> orderMenus = orderMenusData.stream().map(OrderMenuMapper::toDomain).toList();
            List<MenuDto> orderMenusData = inputView.readOrderMenus();
            List<Menu> menus = orderMenusData.stream().map(MenuMapper::toDomain).toList();

            return new OrderMenus(menus);
        });
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
