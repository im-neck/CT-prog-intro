package expression.exceptions;

public class OverflowException extends AbstractException {
    OverflowException(String operation, String reason) {
        super(operation, reason, "overflow");
    }
}