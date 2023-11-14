package christmas.exception;

public enum IllegalArgumentExceptionType implements ExceptionType<IllegalArgumentException> {

    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDERING("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DISCOUNT("할인 가격은 음수나 0이어야 합니다."),
    INVALID_PAYMENT_AMOUNT("총액은 0 또는 양수여야 합니다");

    private final String message;

    IllegalArgumentExceptionType(String message) {
        this.message = MESSAGE_PREFIX + message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public IllegalArgumentException getException() {
        return new IllegalArgumentException(message);
    }
}
