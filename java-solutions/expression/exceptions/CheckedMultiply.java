package expression.exceptions;

import expression.AbstractBinaryOperation;
import expression.Generalization;

public class CheckedMultiply extends AbstractBinaryOperation {
    public CheckedMultiply(Generalization x1, Generalization x2) {
        super(x1, x2, "*");
    }

    @Override
    public int calc(int x1, int x2) {
        if (checkOverflow(x1, x2)) {
            throw new OverflowException("multiply", x1 + " * " + x2);
        }
        return x1 * x2;
    }

    private boolean checkOverflow(int x1, int x2) {
        return ((x1 > 0 && x2 > 0 && x1 > Integer.MAX_VALUE / x2) ||
                (x1 < 0 && x2 < 0 && x1 < Integer.MAX_VALUE / x2) ||
                (x1 > 0 && x2 < 0 && x2 < Integer.MIN_VALUE / x1) ||
                (x1 < 0 && x2 > 0 && x1 < Integer.MIN_VALUE / x2));
    }
}