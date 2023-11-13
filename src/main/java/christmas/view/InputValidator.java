package christmas.view;

import christmas.exception.IllegalArgumentExceptionType;
import java.util.List;

public class InputValidator {
    private static final int VALID_MENU_INFO_SIZE = 2;

    public static void validateDateOfVisit(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw IllegalArgumentExceptionType.INVALID_DATE.getException();
        }
    }

    public static void validateMenuCount(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }

    public static void validateMenuInfoSize(List<String> menuInfo) {
        if (menuInfo.size() != VALID_MENU_INFO_SIZE) {
            throw IllegalArgumentExceptionType.INVALID_ORDERING.getException();
        }
    }

}
