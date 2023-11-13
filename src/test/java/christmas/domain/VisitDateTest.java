package christmas.domain;

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
}