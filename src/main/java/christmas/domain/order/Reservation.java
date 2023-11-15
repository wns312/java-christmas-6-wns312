package christmas.domain.order;

import java.util.List;

public class Reservation {
    private final VisitDate visitDate;
    private final OrderMenus orderMenus;

    public Reservation(VisitDate visitDate, OrderMenus orderMenus) {
        this.visitDate = visitDate;
        this.orderMenus = orderMenus;
    }

    public int getTotalPayment() {
        return orderMenus.getTotalPayment();
    }

    public int getVisitDayOfMonth() {
        return visitDate.getDayOfMonth();
    }

    public List<Menu> getOrderMenus() {
        return orderMenus.getElements();
    }

    public int getDessertMenuCount() {
        return orderMenus.getDessertMenuCount();
    }

    public int getMainMenuCount() {
        return orderMenus.getMainMenuCount();
    }

    public boolean isSunday() { return visitDate.isSunday(); }

    public boolean isChristmas() { return visitDate.isChristmas(); }

    public boolean isWeekDay() { return visitDate.isWeekDay(); }

    public boolean isWeekEnd() { return visitDate.isWeekEnd(); }
}
