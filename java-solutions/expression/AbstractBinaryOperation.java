package expression;

import java.util.Objects;

public abstract class AbstractBinaryOperation implements Generalization {
    protected final Generalization x1;
    protected final Generalization x2;
    protected final String operation;

    public AbstractBinaryOperation(Generalization x1, Generalization x2, String operation) {
        this.operation = operation;
        this.x1 = x1;
        this.x2 = x2;
    }

    public int evaluate(int x) {
        return calc(x1.evaluate(x), x2.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return calc(x1.evaluate(x, y, z), x2.evaluate(x, y, z));
    }

    abstract protected int calc(int x1, int x2);

    public String toString() {
        return "(" + x1.toString() + " " + operation + " " + x2 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBinaryOperation operation1 = (AbstractBinaryOperation) o;
        return Objects.equals(operation, operation1.operation) && x1.equals(operation1.x1) && x2.equals(operation1.x2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, x2, operation);
    }
}