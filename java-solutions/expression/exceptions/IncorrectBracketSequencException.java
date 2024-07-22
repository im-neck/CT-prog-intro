
package expression.exceptions;

public class IncorrectBracketSequencException extends ParsingException {
    IncorrectBracketSequencException(String reason, String description) {
        super(reason, description);
    }
}
