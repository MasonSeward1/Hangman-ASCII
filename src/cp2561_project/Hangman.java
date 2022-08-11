// Started August 7, 2022
package cp2561_project;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman implements Runnable
{
    String gameWord = "";
    String difficultyLevel = "";
    int guessesLeft = 6;
    boolean gameEnd = false;
    boolean finishedCorrectGuess = true;
    char[] gameWordArray;

    public static void startGame() throws UnsupportedAudioFileException, IOException
    {
        // Launch background music and game on separate threads
        Thread audioThread = new Thread(new AudioPlayer("background.wav", true));
        audioThread.start();

        Thread gameThread = new Thread(new Hangman());
        gameThread.start();
    }

    public void playGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException
    {
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> guessedLetters = new ArrayList<>();

        Scanner difficulty = new Scanner(System.in);

        System.out.print("Enter a difficulty level 1-3: ");
        difficultyLevel = difficulty.next();

        switch (difficultyLevel) {
            case "1" -> gameWord = CreateWord.generateWord(1);
            case "2" -> gameWord = CreateWord.generateWord(2);
            case "3" -> gameWord = CreateWord.generateWord(3);
        }
        gameWordArray = gameWord.toCharArray();

        char[] userAnswers = new char[gameWordArray.length];

        for (int i = 0; i < gameWordArray.length; i++) {
            userAnswers[i] = '?';
        }

        while (!gameEnd)
        {
            boolean letterGuessed = false;

            System.out.println("\n");
            System.out.print("Enter a single letter (Enter 0 to exit): ");

            String letter = (userInput.next());

            while (!letter.matches("^[A-Za-z]{1}$"))
            {
                if (letter.matches("0"))
                {
                    System.out.println("You have guessed the word!");
                    System.out.println("Goodbye.");
                    gameEnd = true;
                    System.exit(1);
                }
                else
                {
                    System.out.print("Invalid input. Enter a single letter: ");
                    letter = userInput.next();
                }
            }

            guessedLetters.add(letter);

            for (int i = 0; i < gameWordArray.length; i++) {
                if (letter.charAt(0) == gameWordArray[i]) {
                    userAnswers[i] = letter.charAt(0);
                    letterGuessed = true;
                }
            }

            if (!letterGuessed) {
                System.out.println("Incorrect letter\n\n");
                guessesLeft--;
            }

            for (char c : userAnswers) {
                if (c == '?') {
                    System.out.print(" _");
                    finishedCorrectGuess = false;
                } else
                    System.out.print(" " + c);
            }

            System.out.println("\nGuesses left: " + guessesLeft);
            DrawMan.printHangMan(guessesLeft);
            System.out.println("Letter's Guessed: ");

            if (!difficultyLevel.equals("3") && !difficultyLevel.equals("2")) {
                for (String c : guessedLetters)
                    System.out.print(" " + c);
            } else
                System.out.println("Unavailable during level " + difficultyLevel);

            checkGuessesLeft();
        }
    }

    @Override
    public void run()
    {
        try {
            playGame();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("The audio file is not supported. Please use WAV format");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An IO Exception has occurred: ");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("The line is unavailable: ");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Execution has been interrupted: ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unknown error has occurred: ");
            e.printStackTrace();
        }
    }

    public void checkGuessesLeft() throws UnsupportedAudioFileException, IOException, InterruptedException
    {
        if (guessesLeft == 3) {
            System.out.println("\n\nHINT: " + gameWordArray[2]);
        }

        else if (guessesLeft == 2)
            System.out.println("\n\nHINT: " + gameWordArray[1]);

        else if (guessesLeft == 1) {
            System.out.println("\n\nHINT: " + gameWordArray[0]);
        }

        if (guessesLeft == 0) {
            System.out.println("\n\nYou are Dead");
            System.out.println("The word was: " + gameWord);

            new AudioPlayer("gameOver.wav").run();

            gameEnd = true;
            Thread.sleep(11500);
            System.exit(1);
        }
    }
}

