package expression.exceptions;

public abstract class AbstractException extends RuntimeException {
    AbstractException(String operation, String reason, String description) {
        super("Exception in " + operation + ": " + reason + " (" + description + ")");
    }
}
