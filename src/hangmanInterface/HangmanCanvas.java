package hangmanInterface;

import acm.graphics.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HangmanCanvas extends GCanvas {
    private static final int FONT_SIZE = 18;

    public HangmanCanvas() {
        adjustCanvasSize(); // Adjust canvas size based on text file dimensions
    }

    public void reset() {
        removeAll();
        displayHangman(8); // Start with 8 guesses
    }

    public void displayHangman(int guessesLeft) {
        removeAll();
        drawHangman(guessesLeft);
    }

    private void adjustCanvasSize() {
        try {
            File file = new File("assets/display0.txt"); // Assuming all display files have the same dimensions
            Scanner scanner = new Scanner(file);
            int numLines = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                numLines++;
            }
            scanner.close();
            setSize(600, numLines * FONT_SIZE); // Set canvas width to 600 pixels and height based on text size
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void drawHangman(int guessesLeft) {
        try {
            File file = new File("assets/display" + guessesLeft + ".txt");
            Scanner scanner = new Scanner(file);
            int y = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                GLabel label = new GLabel(line);
                label.setFont(new Font("Courier", Font.PLAIN, FONT_SIZE));
                add(label, (getWidth() - label.getWidth()) / 2, y + label.getAscent());
                y += FONT_SIZE; // Increment y-coordinate
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
