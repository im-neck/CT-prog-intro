package game;

import java.util.Random;


public class RandomPlayer implements Player {

    private final Random random;
    private final int m;
    private final int n;

    public RandomPlayer(final Random random, int m, int n) {
        this.random = random;
        this.m = m;
        this.n = n;
    }


    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(m);
            int c = random.nextInt(n);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
