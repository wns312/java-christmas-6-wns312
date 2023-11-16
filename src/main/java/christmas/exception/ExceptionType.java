package christmas.exception;

public interface ExceptionType<T> {
    String MESSAGE_PREFIX = "[ERROR] ";

    String getMessage();

    T getException();
}
