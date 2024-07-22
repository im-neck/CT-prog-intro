package expression.exceptions;

import expression.AbstractUnaryOperation;
import expression.Generalization;

public class CheckedLog2 extends AbstractUnaryOperation {
    public CheckedLog2(Generalization x1) {
        super(x1, "log2");
    }

    @Override
    protected int calc(int x1) {
        if (x1 < 1) {
            throw new IncorrectLogArgException(x1 + " is less than 1");
        }
        int log = 0;
        while (x1 > 1) {
            x1 /= 2;
            log++;
        }
        return log;
    }
}