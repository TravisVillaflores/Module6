package ChessGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ChessDisplay extends JPanel {
    private ChessBoard board;
    private ChessPiece selectedPiece;
    private boolean isWhiteTurn;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private Map<String, Image> pieceImages;

    public ChessDisplay(ChessBoard board) {
        this.board = board;
        this.isWhiteTurn = true;
        this.pieceImages = loadPieceImages();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e);
                repaint();
            }
        });
    }

    private Map<String, Image> loadPieceImages() {
        Map<String, Image> images = new HashMap<>();
        try {
            images.put("white_pawn", ImageIO.read(new File("images/white_pawn.png")));
            images.put("black_pawn", ImageIO.read(new File("images/black_pawn.png")));
            images.put("white_rook", ImageIO.read(new File("images/white_rook.png")));
            images.put("black_rook", ImageIO.read(new File("images/black_rook.png")));
            images.put("white_knight", ImageIO.read(new File("images/white_knight.png")));
            images.put("black_knight", ImageIO.read(new File("images/black_knight.png")));
            images.put("white_bishop", ImageIO.read(new File("images/white_bishop.png")));
            images.put("black_bishop", ImageIO.read(new File("images/black_bishop.png")));
            images.put("white_queen", ImageIO.read(new File("images/white_queen.png")));
            images.put("black_queen", ImageIO.read(new File("images/black_queen.png")));
            images.put("white_king", ImageIO.read(new File("images/white_king.png")));
            images.put("black_king", ImageIO.read(new File("images/black_king.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    private void handleMouseClick(MouseEvent e) {
        int row = e.getY() / (getHeight() / ChessBoard.BOARD_SIZE);
        int col = e.getX() / (getWidth() / ChessBoard.BOARD_SIZE);

        if (selectedPiece == null) {
            ChessPiece piece = board.pieceAt(row, col);
            if (piece != null && piece.isWhite() == isWhiteTurn) {
                selectedPiece = piece;
                selectedRow = row;
                selectedCol = col;
            }
        } else {
            if (selectedPiece.canMoveTo(row, col) && (selectedPiece.getRow() != row || selectedPiece.getCol() != col)) {
                board.removePiece(selectedPiece.getRow(), selectedPiece.getCol());
                selectedPiece.moveTo(row, col);
                board.addPiece(selectedPiece);
                if (board.isInCheckmate(!isWhiteTurn)) {
                    JOptionPane.showMessageDialog(this, (isWhiteTurn ? "White" : "Black") + " wins by checkmate!");
                } else if (board.isInStalemate(!isWhiteTurn)) {
                    JOptionPane.showMessageDialog(this, "Stalemate!");
                }
                isWhiteTurn = !isWhiteTurn;
            }
            selectedPiece = null;
            selectedRow = -1;
            selectedCol = -1;
        }
    }

    private String getPieceImageKey(ChessPiece piece) {
        String color = piece.isWhite() ? "white" : "black";
        String type = piece.getClass().getSimpleName().toLowerCase();
        return color + "_" + type;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int squareSize = getWidth() / ChessBoard.BOARD_SIZE;

        for (int row = 0; row < ChessBoard.BOARD_SIZE; row++) {
            for (int col = 0; col < ChessBoard.BOARD_SIZE; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);

                if (row == selectedRow && col == selectedCol) {
                    g.setColor(Color.RED);
                    g.drawRect(col * squareSize, row * squareSize, squareSize, squareSize);
                }

                ChessPiece piece = board.pieceAt(row, col);
                if (piece != null) {
                    String key = getPieceImageKey(piece);
                    Image image = pieceImages.get(key);
                    if (image != null) {
                        g.drawImage(image, col * squareSize, row * squareSize, squareSize, squareSize, null);
                    } else {
                        System.out.println("Image not found for key: " + key);
                    }
                }
            }
        }
    }
}