package ChessGame;

public class Queen extends ChessPiece {
    public Queen(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public boolean canMoveTo(int row, int col) {
        int rowDiff = Math.abs(row - getRow());
        int colDiff = Math.abs(col - getCol());
        return rowDiff == colDiff || row == getRow() || col == getCol();
    }
}