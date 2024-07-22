package expression;

public class Subtract extends AbstractBinaryOperation {
    public Subtract(Generalization x1, Generalization x2) {
        super(x1, x2, "-");
    }

    @Override
    public int calc(int x1, int x2) {
        return x1 - x2;
    }
}
