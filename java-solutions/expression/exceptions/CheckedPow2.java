package expression.exceptions;

import expression.AbstractUnaryOperation;
import expression.Generalization;

public class CheckedPow2 extends AbstractUnaryOperation {
    final int maxPow = 30;

    public CheckedPow2(Generalization x1) {
        super(x1, "pow2");
    }

    @Override
    protected int calc(int x1) {
        if (x1 > maxPow) {
            throw new OverflowException("pow2", x1 + "power is bigger than Integer.MAX_VALUE");
        }
        if (x1 < 0) {
            throw new SubZeroPowException("" + x1);
        }
        int pow = 1;
        for (int i = 0; i < x1; i++) {
            pow *= 2;
        }
        return pow;
    }
}