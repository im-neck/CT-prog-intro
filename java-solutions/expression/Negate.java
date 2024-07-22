package expression;

public class Negate extends AbstractUnaryOperation {
    public Negate(Generalization x1) {
        super(x1, "-");
    }

    @Override
    protected int calc(int x1) {
        return -x1;
    }
}