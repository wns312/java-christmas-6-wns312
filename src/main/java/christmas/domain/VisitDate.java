package christmas.domain;

import christmas.exception.IllegalArgumentExceptionType;
import java.time.LocalDate;

public class VisitDate {
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int TWO_THOUSAND_TWENTY_THREE = 2023;
    private static final int DECEMBER = 12;

    private final LocalDate localDate;

    public VisitDate(int date) {
        validateMinDate(date);
        validateMaxDate(date);
        this.localDate = createLocaldate(date);
    }

    private void validateMinDate(int date) {
        if (date < MIN_DATE) {
            throw IllegalArgumentExceptionType.INVALID_DATE.getException();
        }

    }

    private void validateMaxDate(int date) {
        if (date > MAX_DATE) {
            throw IllegalArgumentExceptionType.INVALID_DATE.getException();
        }

    }

    private LocalDate createLocaldate(int date) {
        return LocalDate.of(TWO_THOUSAND_TWENTY_THREE, DECEMBER, date);
    }
}
