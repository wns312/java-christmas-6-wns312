package christmas.domain.order;

import christmas.domain.constant.EventWeek;
import christmas.exception.IllegalArgumentExceptionType;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int TWO_THOUSAND_TWENTY_THREE = 2023;
    private static final int DECEMBER = 12;
    private static final int CHRISTMAS_DATE = 25;

    private final LocalDate localDate;

    public VisitDate(int date) {
        validateMinDate(date);
        validateMaxDate(date);
        this.localDate = createLocaldate(date);
    }

    public LocalDate getLocalDate() {
        return localDate;
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

    public int getDayOfMonth() { return localDate.getDayOfMonth(); }

    public boolean isSunday() { return localDate.getDayOfWeek() == DayOfWeek.SUNDAY; }

    public boolean isChristmas() { return localDate.getDayOfMonth() == CHRISTMAS_DATE; }

    public boolean isWeekDay() { return EventWeek.WEEKDAY.contains(localDate.getDayOfWeek()); }

    public boolean isWeekEnd() { return EventWeek.WEEKEND.contains(localDate.getDayOfWeek()); }
}
