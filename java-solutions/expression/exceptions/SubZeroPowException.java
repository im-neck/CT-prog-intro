package expression.exceptions;

public class SubZeroPowException extends AbstractException {
    SubZeroPowException(String reason) {
        super("pow2", reason, "sub zero power");
    }
}