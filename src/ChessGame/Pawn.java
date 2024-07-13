package ChessGame;

public class Pawn extends ChessPiece {
    public Pawn(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public boolean canMoveTo(int row, int col) {
        int direction = isWhite() ? 1 : -1;
        if (col == getCol() && row == getRow() + direction) {
            return true;
        }
        // Initial two-step move
        if (col == getCol() && row == getRow() + 2 * direction && (getRow() == 1 || getRow() == 6)) {
            return true;
        }
        // Capture move
        if (Math.abs(col - getCol()) == 1 && row == getRow() + direction) {
            return true;
        }
        return false;
    }
}