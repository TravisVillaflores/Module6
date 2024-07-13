package ChessGame;

import javax.swing.*;

public class Chess {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        setupInitialBoard(board);

        JFrame frame = new JFrame("Chess");
        ChessDisplay display = new ChessDisplay(board);
        frame.add(display);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void setupInitialBoard(ChessBoard board) {
        // Add white pieces
        board.addPiece(new Rook(0, 0, true));
        board.addPiece(new Knight(0, 1, true));
        board.addPiece(new Bishop(0, 2, true));
        board.addPiece(new Queen(0, 3, true));
        board.addPiece(new King(0, 4, true));
        board.addPiece(new Bishop(0, 5, true));
        board.addPiece(new Knight(0, 6, true));
        board.addPiece(new Rook(0, 7, true));
        for (int i = 0; i < ChessBoard.BOARD_SIZE; i++) {
            board.addPiece(new Pawn(1, i, true));
        }

        // Add black pieces
        board.addPiece(new Rook(7, 0, false));
        board.addPiece(new Knight(7, 1, false));
        board.addPiece(new Bishop(7, 2, false));
        board.addPiece(new Queen(7, 3, false));
        board.addPiece(new King(7, 4, false));
        board.addPiece(new Bishop(7, 5, false));
        board.addPiece(new Knight(7, 6, false));
        board.addPiece(new Rook(7, 7, false));
        for (int i = 0; i < ChessBoard.BOARD_SIZE; i++) {
            board.addPiece(new Pawn(6, i, false));
        }
    }
}