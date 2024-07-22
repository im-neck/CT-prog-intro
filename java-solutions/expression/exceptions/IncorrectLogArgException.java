package expression.exceptions;

public class IncorrectLogArgException extends AbstractException {
    IncorrectLogArgException(String reason) {
        super("pow2", reason, "sub zero power");
    }
}