package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Player {
    Move move(Position position, Cell cell) throws NumberFormatException ;

   // Move move(Position position, Cell cell, int m, int n);
}
