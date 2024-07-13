package ChessGame;

public class King extends ChessPiece {
    public King(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public boolean canMoveTo(int row, int col) {
        int rowDiff = Math.abs(row - getRow());
        int colDiff = Math.abs(col - getCol());
        return (rowDiff <= 1 && colDiff <= 1);
    }
}