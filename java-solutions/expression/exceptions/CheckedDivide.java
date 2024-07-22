package expression.exceptions;

import expression.AbstractBinaryOperation;
import expression.Generalization;

public class CheckedDivide extends AbstractBinaryOperation {
    public CheckedDivide(Generalization x1, Generalization x2) {
        super(x1, x2, "/");
    }

    @Override
    public int calc(int x1, int x2) {
        if (x2 == 0) {
            throw new DivisionByZeroException(x1 + " / " + x2);
        }
        if ((x1 == Integer.MIN_VALUE) && (x2 == -1)) {
            throw new OverflowException("divide", x1 + " / " + x2);
        }
        return x1 / x2;
    }
}
