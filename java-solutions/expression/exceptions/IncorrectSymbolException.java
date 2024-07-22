package expression.exceptions;

public class IncorrectSymbolException extends ParsingException {
    IncorrectSymbolException(String reason, String description) {
        super(reason, description);
    }
}