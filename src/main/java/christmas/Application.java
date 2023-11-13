package christmas;

import christmas.controller.DecemberPromotionController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        DecemberPromotionController controller = new DecemberPromotionController(inputView, outputView);
        controller.run();
    }
}
