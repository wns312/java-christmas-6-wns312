package christmas.view;

import christmas.exception.IllegalArgumentExceptionType;

public class InputValidator {

    public static void validateDateOfVisit(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw IllegalArgumentExceptionType.INVALID_DATE.getException();
        }
    }

}