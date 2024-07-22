package game;

import java.util.Arrays;
import java.util.Map;

public class MNKboard implements Position, Board {

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '_'
    );

    private final Cell[][] cells;
    private Cell turn;
    private int empty = 0;
    private final int m;
    private final int n;
    private final int k;

    public MNKboard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }


    private boolean toCountCells(int stepR, int stepC, int row, int col, int k) {

        int res = 0;
        for (int i = -k + 1; i < k; i++) {
            int newR = row + stepR * i;
            int newC = col + stepC * i;
            if (-1 < newR && newR < m && -1 < newC &&  newC < n && cells[newR][newC] == turn) res++;
            else if (res < k) res = 0;
        }
        return res >= k;
    }
    private boolean checkCount(int row, int col, int k){

        return toCountCells(0, 1, row, col, k)
                ||toCountCells(1, 0, row, col, k)
                || toCountCells(1, 1, row, col, k)
                || toCountCells(1, -1, row, col, k);
    }
    @Override
    public Result makeMove(final Move move) {
        empty++;
        if (!isValid(move)) {
            return Result.LOSE;
        }
        int row = move.getRow();
        int col = move.getColumn();
        cells[row][col] = move.getValue();

        if (checkCount(row,col,k))
        {
            return Result.WIN;
        }
        if (!(checkCount(row,col,4))){
            turn = turn == Cell.X ? Cell.O : Cell.X;
        }
        else{
            System.out.println("Wooow! Congratulations! You got an extra move!!");
        }
        return Result.UNKNOWN;

    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(" ".repeat(3));

        int z;
        for (int r = 0; r < n; r++) {
            z = Integer.toString(r).length();
            if (z == 1) {
                sb.append(" ".repeat(2)).append(r+1).append(" ".repeat(2));
            }
            if (z == 2) {
                sb.append(" ".repeat(2)).append(r+1).append(" ".repeat(1));
            }
            if (z == 3) {
                sb.append(" ".repeat(1)).append(r+1).append(" ".repeat(1));
            }

        }
        for (int r = 0; r < m; r++) {
            sb.append(System.lineSeparator());
            z = Integer.toString(r).length();
            sb.append(" ".repeat(3 - z)).append(r+1);
            for (int c = 0; c < n; c++) {
                sb.append(" ".repeat(2)).append(SYMBOLS.get(cells[r][c])).append(" ".repeat(2));
            }
        }
        return sb.toString();
    }
}


