package christmas.exception;

public enum IllegalStateExceptionType implements ExceptionType<IllegalStateException> {
    CANNOT_GET_DISCOUNT("날짜 검증을 통과한 경우에만 할인 금액을 구할 수 있습니다."),
    INVALID_BENEFIT_AMOUNT("혜택 금액은 음수가 입력될 수 없습니다.");

    private final String message;

    IllegalStateExceptionType(String message) {
        this.message = MESSAGE_PREFIX + message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public IllegalStateException getException() {
        return new IllegalStateException(message);
    }
}
