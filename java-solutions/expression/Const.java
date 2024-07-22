package expression;

import java.util.Objects;

public class Const implements Generalization {
    final private int x1;

    public Const(int x) {
        this.x1 = x;
    }

    public int evaluate(int k) {
        return x1;
    }

    public int evaluate(int x, int y, int z) {
        return x1;
    }

    public String toString() {
        return "" + x1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return x1 == aConst.x1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1);
    }
}
