package expression;

public class Or extends AbstractBinaryOperation {
    public Or(Generalization x1, Generalization x2) {
        super(x1, x2, "|");
    }

    @Override
    public int calc(int x1, int x2) {
        return x1 | x2;
    }
}
