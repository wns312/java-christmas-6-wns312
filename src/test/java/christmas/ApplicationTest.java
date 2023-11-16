package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메인_메뉴_최대_주문() {
        assertSimpleTest(() -> {
            run("25", "티본스테이크-20");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 최대_혜택_주문() {
        assertSimpleTest(() -> {
            run("25", "초코케이크-20");
            assertThat(output()).contains(
                    "12월 25일",
                    "<주문 메뉴>",
                    "초코케이크 20개",
                    "<할인 전 총주문 금액>",
                    "300,000원",
                    "<증정 메뉴>",
                    "샴페인 1개",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -3,400원",
                    "특별 할인: -1,000원",
                    "평일 할인: -40,460원",
                    "<총혜택 금액>",
                    "69,860원",
                    "<할인 후 예상 결제 금액>",
                    "255,140원",
                    "<12월 이벤트 배지>",
                    "산타"
            );
        });
    }

    @Test
    void 산타_배지() {
        assertSimpleTest(() -> {
            run("25", "초코케이크-4, 아이스크림-4");
            assertThat(output()).contains(
                    "12월 25일",
                    "<주문 메뉴>",
                    "초코케이크 4개",
                    "아이스크림 4개",
                    "<할인 전 총주문 금액>",
                    "80,000원",
                    "<증정 메뉴>" + LINE_SEPARATOR + "없음",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -3,400원",
                    "특별 할인: -1,000원",
                    "평일 할인: -16,184원",
                    "<총혜택 금액>",
                    "20,584원",
                    "<할인 후 예상 결제 금액>",
                    "59,416원",
                    "<12월 이벤트 배지>",
                    "산타"
            );
        });
    }

    @Test
    void 트리_배지() {
        assertSimpleTest(() -> {
            run("25", "초코케이크-4, 아이스크림-3");
            assertThat(output()).contains(
                    "12월 25일",
                    "<주문 메뉴>",
                    "초코케이크 4개",
                    "아이스크림 3개",
                    "<할인 전 총주문 금액>",
                    "75,000원",
                    "<증정 메뉴>" + LINE_SEPARATOR + "없음",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -3,400원",
                    "특별 할인: -1,000원",
                    "평일 할인: -14,161원",
                    "<총혜택 금액>",
                    "18,561원",
                    "<할인 후 예상 결제 금액>",
                    "56,439원",
                    "<12월 이벤트 배지>",
                    "트리"
            );
        });
    }

    @Test
    void 별_배지() {
        assertSimpleTest(() -> {
            run("25", "초코케이크-1, 아이스크림-1");
            assertThat(output()).contains(
                    "12월 25일",
                    "<주문 메뉴>",
                    "초코케이크 1개",
                    "아이스크림 1개",
                    "<할인 전 총주문 금액>",
                    "20,000원",
                    "<증정 메뉴>" + LINE_SEPARATOR + "없음",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -3,400원",
                    "특별 할인: -1,000원",
                    "평일 할인: -4,046원",
                    "<총혜택 금액>",
                    "8,446원",
                    "<할인 후 예상 결제 금액>",
                    "11,554원",
                    "<12월 이벤트 배지>",
                    "별"
            );
        });
    }

    @Test
    void 배지_없음() {
        assertSimpleTest(() -> {
            run("23", "초코케이크-1, 아이스크림-1");
            assertThat(output()).contains(
                    "12월 23일",
                    "<주문 메뉴>",
                    "초코케이크 1개",
                    "아이스크림 1개",
                    "<할인 전 총주문 금액>",
                    "20,000원",
                    "<증정 메뉴>" + LINE_SEPARATOR + "없음",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -3,200원",
                    "<총혜택 금액>",
                    "3,200원",
                    "<할인 후 예상 결제 금액>",
                    "16,800원",
                    "<12월 이벤트 배지>" + LINE_SEPARATOR + "없음"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
