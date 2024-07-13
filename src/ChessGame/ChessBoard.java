package ChessGame;

public class ChessBoard {
    public static final int BOARD_SIZE = 8;
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    }

    public ChessPiece pieceAt(int row, int col) {
        return board[row][col];
    }

    public void addPiece(ChessPiece piece) {
        board[piece.getRow()][piece.getCol()] = piece;
    }

    public void removePiece(int row, int col) {
        board[row][col] = null;
    }

    public boolean isInCheckmate(boolean isWhite) {
        // Implement checkmate logic
        return false;
    }

    public boolean isInStalemate(boolean isWhite) {
        // Implement stalemate logic
        return false;
    }
}