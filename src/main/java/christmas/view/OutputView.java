package christmas.view;

public class OutputView {
    private static final String INTRODUCING_PLANNER_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final static String INTRODUCING_BENEFIT_EXPRESSION = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n";

    public void printIntroducingPlannerMessage() { System.out.println(INTRODUCING_PLANNER_MESSAGE); }

    public void printIntroducingBenefitMessage(int date) {
        System.out.printf(INTRODUCING_BENEFIT_EXPRESSION, date);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
