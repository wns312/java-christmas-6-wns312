package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.exception.IllegalArgumentExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {
    @DisplayName("날짜 생성 성공 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10, 31})
    void createVisitDateSuccessTest(int date) {
        assertThatCode(() -> new VisitDate(date))
                .doesNotThrowAnyException();
    }

    @DisplayName("날짜 생성 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void createVisitDateFailTest(int date) {
        assertThatCode(() -> new VisitDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.INVALID_DATE.getMessage());
    }

    @DisplayName("일요일 아닌 날짜 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 25, 26, 27, 28, 29, 30})
    void checkIsSundayTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.isSunday()).isFalse();
    }

    @DisplayName("일요일인 날짜 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 31})
    void checkIsNotSundayTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.isSunday()).isTrue();
    }

    @DisplayName("일요일인 날짜 테스트")
    @ParameterizedTest
    @ValueSource(ints = {25})
    void checkIsChristmasTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.isChristmas()).isTrue();
    }

    @DisplayName("일요일인 날짜 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 26, 27, 28, 29, 30, 31})
    void checkIsNotChristmasTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.isChristmas()).isFalse();
    }

    @DisplayName("Weekday 주중 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void checkIsWeekDayTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.isWeekDay()).isTrue();
    }

    @DisplayName("Weekday 주중 아님 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void checkIsNotWeekDayTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.isWeekDay()).isFalse();
    }

    @DisplayName("WeekEnd 주말 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void checkIsWeekEndTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.isWeekEnd()).isTrue();
    }

    @DisplayName("WeekEnd 주말 아님 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void checkIsNotWeekEndTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.isWeekEnd()).isFalse();
    }
}
