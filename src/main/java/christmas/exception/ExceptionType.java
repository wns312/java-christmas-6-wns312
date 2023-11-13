package christmas.exception;

public interface ExceptionType<T> {

    static final String MESSAGE_PREFIX = "[ERROR] ";

    String getMessage();

    T getException();
}
