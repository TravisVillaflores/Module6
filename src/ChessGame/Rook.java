package ChessGame;

public class Rook extends ChessPiece {
    public Rook(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public boolean canMoveTo(int row, int col) {
        return row == getRow() || col == getCol();
    }
}