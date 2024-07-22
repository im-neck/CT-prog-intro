package expression.exceptions;

import expression.AbstractBinaryOperation;
import expression.Generalization;

public class CheckedAdd extends AbstractBinaryOperation {

    public CheckedAdd(Generalization x1, Generalization x2) {
        super(x1, x2, "+");
    }

    @Override
    public int calc(int x1, int x2) {
        if ((x2 > 0 && x1 > Integer.MAX_VALUE - x2) ||
                (x2 < 0 && x1 < Integer.MIN_VALUE - x2)) {
            throw new OverflowException("add", x1 + " + " + x2);
        }
        return x1 + x2;
    }
}
