package hangmanInterface;

import acm.program.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman extends ConsoleProgram {
    private SoundManager soundManager;
    private HangmanGame game;
    private HangmanCanvas canvas;

    public void init() {
        soundManager = new SoundManager(); // Instantiate SoundManager
        canvas = new HangmanCanvas();
        add(canvas);
        canvas.reset();
        soundManager.loadSounds(); // Initialize SoundManager
    }

    public void run() {
        int gamesCount = 0;
        int gamesWon = 0;
        int bestGame = Integer.MAX_VALUE;

        do {
            game = new HangmanGame(soundManager); // Pass SoundManager instance to HangmanGame
            int guessesLeft = game.run(canvas); // Run the game
            gamesCount++;
            if (guessesLeft > 0) {
                gamesWon++;
                if (guessesLeft < bestGame) {
                    bestGame = guessesLeft;
                }
            }
        } while (readBoolean("Do you want to play again? (Y/N): ", "Y", "N"));

        stats(gamesCount, gamesWon, bestGame == Integer.MAX_VALUE ? 0 : bestGame);
    }

    private void stats(int gamesCount, int gamesWon, int bestGame) {
        double winPercent = gamesCount == 0 ? 0.0 : ((double) gamesWon / gamesCount) * 100;

        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        println("Overall statistics:");
        println("Games played: " + gamesCount);
        println("Games won: " + gamesWon);
        println("Win percent: " + winPercent + "%");
        println("Best game: " + bestGame + " guess(es) remaining");
        println("Thanks for playing!");
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    public static void main(String[] args) {
        new Hangman().start(args);
    }

    private class HangmanGame {
        private final SoundManager soundManager;

        public HangmanGame(SoundManager soundManager) {
            this.soundManager = soundManager; // Assign SoundManager instance
        }

        private void intro() {
            String introMessage =
                    """
                            @@@@@@@@@@@@@@@@@@@@@@@@@@@@
                            Welcome to Hangman!
                            I will think of a random word.
                            You'll try to guess its letters.
                            Every time you guess a letter
                            that isn't in my word, a new body
                            part of the hanging man appears.
                            Good luck!
                            @@@@@@@@@@@@@@@@@@@@@@@@@@@@
                            """;

            println(introMessage);
        }

        private String createHint(String secretWord, String guessedLetters) {
            StringBuilder hint = new StringBuilder();

            for (char c : secretWord.toCharArray()) {
                if (guessedLetters.indexOf(c) >= 0) {
                    hint.append(c);
                } else {
                    hint.append('-');
                }
            }

            return hint.toString();
        }

        private char readGuess(String guessedLetters) {
            while (true) {
                String input = readLine("Your guess? ").toUpperCase();

                if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                    char guess = input.charAt(0);
                    if (guessedLetters.indexOf(guess) == -1) {
                        return guess;
                    } else {
                        println("You already guessed that letter.");
                    }
                } else {
                    println("Type a single letter from A-Z.");
                }
            }
        }

        private int playOneGame(String secretWord, HangmanCanvas canvas) {
            int guessesLeft = 8;
            String guessedLetters = "";
            boolean wordGuessed = false;

            while (guessesLeft > 0 && !wordGuessed) {
                println("Secret word: " + createHint(secretWord, guessedLetters));
                println("Your guesses: " + guessedLetters);
                println("Guesses left: " + guessesLeft);

                char guess = readGuess(guessedLetters);
                guessedLetters += guess;

                if (secretWord.indexOf(guess) == -1) {
                    guessesLeft--;
                    canvas.displayHangman(guessesLeft);
                    soundManager.playIncorrectGuessSound(); // Play incorrect guess sound
                    println("Incorrect.");
                } else {
                    soundManager.playCorrectGuessSound(); // Play correct guess sound
                    println("Correct!");
                }

                if (createHint(secretWord, guessedLetters).equals(secretWord)) {
                    wordGuessed = true;
                }
            }

            if (wordGuessed) {
                println("You guessed the word: " + secretWord);
                println("You win.");
            } else {
                println("You're completely hung.");
                println("The word was: " + secretWord);
                println("You lose.");
            }

            return guessesLeft;
        }

        public int run(HangmanCanvas canvas) {
            intro();
            String secretWord = getRandomWord();
            canvas.reset();
            return playOneGame(secretWord, canvas);
        }

        private String getRandomWord() {
            List<String> words = new ArrayList<>();
            try {
                File file = new File("assets/dict.txt");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String word = scanner.nextLine().trim().toUpperCase();
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                println("Word list file not found.");
            }

            Random random = new Random();
            return words.get(random.nextInt(words.size()));
        }
    }
}
