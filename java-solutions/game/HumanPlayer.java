package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;

    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");

            int x;
            int y;

            while (true) {
                try {
                    String move1 = in.nextLine();
                    Scanner scanFromLine = new Scanner(move1);
                    x = scanFromLine.nextInt();
                    y = scanFromLine.nextInt();
                    x--;
                    y--;
                    break;
                } catch (InputMismatchException e) { //NumberFormatException
                    System.out.println("I'm sorry, but you entered incorrect coordinates." +
                            " Try again, you will definitely succeed!");

                }
                catch ( NoSuchElementException e){
                    System.out.println("Oh my goodness! What are you doing ? Please, don't do this again");
                    System.exit(6);

                }

            }
            final Move move = new Move(x, y, cell);
            if (position.isValid(move)) {
                return move;
            }

            out.println("Move " + move + " is invalid");
        }
    }
}// ctrl alt l