package expression.exceptions;

public class ParsingException extends AbstractException {
    ParsingException(String reason, String description) {
        super("parsing", reason, description);
    }
}