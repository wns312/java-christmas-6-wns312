package christmas.domain.constant;

import java.time.DayOfWeek;
import java.util.List;

public enum EventWeek {
    WEEKDAY(List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY)),
    WEEKEND(List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));

    private final List<DayOfWeek> daysOfWeek;

    EventWeek(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public boolean contains(DayOfWeek dayOfWeek) {
        return daysOfWeek.contains(dayOfWeek);
    }
}
