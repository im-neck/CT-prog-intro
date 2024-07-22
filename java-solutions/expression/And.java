package expression;

public class And extends AbstractBinaryOperation {
    public And(Generalization x1, Generalization x2) {
        super(x1, x2, "&");
    }

    @Override
    public int calc(int x1, int x2) {
        return x1 & x2;
    }
}