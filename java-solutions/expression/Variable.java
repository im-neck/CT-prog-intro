package expression;

import java.util.Objects;

public class Variable implements Generalization {
    final private String x1;

    public Variable(String x) {
        this.x1 = x;
    }

    public int evaluate(int k) {
        return k;
    }

    public int evaluate(int x, int y, int z) {
        return switch (x1) {
            case ("y") -> y;
            case ("z") -> z;
            default -> x;
        };
    }

    public String toString() {
        return x1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(x1, variable.x1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1);
    }
}
