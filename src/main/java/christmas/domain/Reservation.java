package christmas.domain;

import java.time.LocalDate;
import java.util.List;

public class Reservation {
    private final VisitDate visitDate;
    private final OrderMenus orderMenus;

    public Reservation(VisitDate visitDate, OrderMenus orderMenus) {
        this.visitDate = visitDate;
        this.orderMenus = orderMenus;
    }

    public LocalDate getDate() {
        return visitDate.getLocalDate();
    }

    public List<Menu> getOrderMenus() {
        return orderMenus.getElements();
    }

    public int getTotalPayment() {
        return orderMenus.getTotalPayment();
    }

    public int getVisitDayOfMonth() {
        return visitDate.getDayOfMonth();
    }
    public boolean isVisitDaySunday() {
        return visitDate.isSunday();
    }
    public boolean isVisitDayChristmas() {
        return visitDate.isChristmas();
    }
    public boolean isVisitDayWeekDay() {
        return visitDate.isWeekDay();
    }
    public boolean isVisitDayWeekEnd() {
        return visitDate.isWeekEnd();
    }
}
