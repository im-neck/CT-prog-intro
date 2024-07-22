package expression.exceptions;

import expression.AbstractUnaryOperation;
import expression.Generalization;

public class CheckedNegate extends AbstractUnaryOperation {
    public CheckedNegate(Generalization x1) {
        super(x1, "-");
    }

    @Override
    protected int calc(int x1) {
        if (x1 == Integer.MIN_VALUE) {
            throw new OverflowException("negate", "-(" + x1 + ")");
        }
        return -x1;
    }
}