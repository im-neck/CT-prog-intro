package expression.exceptions;

public class DivisionByZeroException extends AbstractException {
    DivisionByZeroException(String reason) {
        super("division", reason, "division by zero");
    }
}