package hangmanInterface;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoundManager {
    private static final Logger logger = Logger.getLogger(SoundManager.class.getName());
    private static Clip correctGuessSound;
    private static Clip incorrectGuessSound;
    private static Clip loseSound;
    private static Clip victorySound;

    public static void loadSounds() {
        try {
            correctGuessSound = loadAudioClip("CorrectAnswer.wav");
            incorrectGuessSound = loadAudioClip("WrongAnswer.wav");
            loseSound = loadAudioClip("Lose.wav");
            victorySound = loadAudioClip("Victory.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            logger.log(Level.SEVERE, "Error loading audio clip: ", e);
        }
    }

    private static Clip loadAudioClip(String filename) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            logger.log(Level.SEVERE, "Error loading audio clip: ", e);
            throw e; // Rethrow the exception to indicate loading failure
        }
    }

    public static void playCorrectGuessSound() {
        if (correctGuessSound != null && !correctGuessSound.isRunning()) {
            correctGuessSound.setFramePosition(0); // rewind to the beginning
            correctGuessSound.start();
        }
    }

    public static void playIncorrectGuessSound() {
        if (incorrectGuessSound != null && !incorrectGuessSound.isRunning()) {
            incorrectGuessSound.setFramePosition(0); // rewind to the beginning
            incorrectGuessSound.start();
        }
    }

    public static void playLoseSound() {
        if (loseSound != null && !loseSound.isRunning()) {
            loseSound.setFramePosition(0); // rewind to the beginning
            loseSound.start();
        }
    }

    public static void playVictorySound() {
        if (victorySound != null && !victorySound.isRunning()) {
            victorySound.setFramePosition(0); // rewind to the beginning
            victorySound.start();
        }
    }
}

