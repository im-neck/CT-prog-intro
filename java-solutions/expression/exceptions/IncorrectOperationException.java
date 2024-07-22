package expression.exceptions;

public class IncorrectOperationException extends ParsingException {
    IncorrectOperationException(String reason, String description) {
        super(reason, description);
    }
}