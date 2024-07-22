package expression;

public class Not extends AbstractUnaryOperation {
    public Not(Generalization x1) {
        super(x1, "~");
    }

    @Override
    protected int calc(int x1) {
        return ~x1;
    }
}