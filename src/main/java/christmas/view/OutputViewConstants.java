package christmas.view;

import java.text.DecimalFormat;

public interface OutputViewConstants {
    DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
    String INTRODUCING_PLANNER_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    String INTRODUCING_BENEFIT_EXPRESSION = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n";
    String ORDERED_MENU_MESSAGE = "\n<주문 메뉴>";
    String TOTAL_PAYMENT_MESSAGE = "\n<할인 전 총주문 금액>";
    String GIFT_MESSAGE = "\n<증정 메뉴>";
    String EVENT_BENEFITS_MESSAGE = "\n<혜택 내역>";
    String EVENT_TOTAL_BENEFIT_AMOUNT_MESSAGE = "\n<총혜택 금액>";
    String DISCOUNTED_TOTAL_PAYMENT_MESSAGE = "\n<할인 후 예상 결제 금액>";
    String DECEMBER_EVENT_BADGE_MESSAGE = "\n<12월 이벤트 배지>";
    String MENU_EXPRESSION = "%s %d개%n";
    String COST_EXPRESSION = "%s원%n";
    String EVENT_BENEFIT_EXPRESSION = "%s: %s원%n";
    String NOTHING_TO_PRINT_MESSAGE = "없음";
}
