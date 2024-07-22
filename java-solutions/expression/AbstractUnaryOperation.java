package expression;

import java.util.Objects;

public abstract class AbstractUnaryOperation implements Generalization {
    final private Generalization x1;
    protected final String operation;

    public AbstractUnaryOperation(Generalization x, String operation) {
        this.x1 = x;
        this.operation = operation;
    }

    public int evaluate(int k) {
        return calc(x1.evaluate(k));
    }

    public int evaluate(int x, int y, int z) {
        return calc(x1.evaluate(x, y, z));
    }

    abstract protected int calc(int x1);

    public String toString() {
        return operation + "(" + x1 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUnaryOperation that = (AbstractUnaryOperation) o;
        return Objects.equals(x1, that.x1) && Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, operation);
    }
}
