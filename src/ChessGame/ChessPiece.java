package ChessGame;

public abstract class ChessPiece {
    private int row;
    private int col;
    private boolean isWhite;

    public ChessPiece(int row, int col, boolean isWhite) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void moveTo(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public abstract boolean canMoveTo(int row, int col);
}