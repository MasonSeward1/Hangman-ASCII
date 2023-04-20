// Started August 7, 2022
package hangman;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman implements Runnable
{
    private String gameWord = "";
    private String difficultyLevel = "";
    private int guessesLeft = 6;
    private boolean gameEnd = false;
    private char[] gameWordArray;

    public void playGame() throws
            UnsupportedAudioFileException,
            IOException,
            LineUnavailableException,
            InterruptedException
    {
        ArrayList<String> guessedLetters = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        setDifficulty();
        gameWordArray = gameWord.toCharArray();
        char[] userAnswers = new char[gameWordArray.length];

        for (int i = 0; i < gameWordArray.length; i++)
        {
            userAnswers[i] = '?';
        }

        while (!gameEnd)
        {
            System.out.print("\nEnter a single letter (Enter 0 to exit): ");
            String letter = userInput.next();
            guessedLetters.add(letter);
            letter = validateGuess(userInput, letter);

            if (!checkCorrectGuess(userAnswers, letter))
            {
                System.out.println("Incorrect letter\n\n");
                guessesLeft--;
            }

            for (char c : userAnswers)
            {
                if (c == '?') System.out.print(" _");
                else System.out.print(" " + c);
            }
            DrawMan.printHangMan(guessesLeft);
            checkGuessesLeft();
            displayGuessedLetters(guessedLetters);
        }
    }

    private String validateGuess(Scanner userInput, String letter)
    {
        while (!letter.matches("^[A-Za-z]$"))
        {
            if (letter.matches("0"))
            {
                gameEnd = true;
                System.out.println("You have guessed the word!");
                System.out.println("Goodbye.");
                System.exit(1);
            }
            else
            {
                System.out.print("Invalid input. Enter a single letter: ");
                letter = userInput.next();
            }
        }
        return letter;
    }

    private boolean checkCorrectGuess(char[] userAnswers, String letter)
    {
        if (letter.charAt(0) == gameWordArray[0])
        {
            userAnswers[0] = letter.charAt(0);
            return true;
        }
        else return false;
    }

    private void displayGuessedLetters(ArrayList<String> guessedLetters)
    {
        System.out.println("Letter's Guessed: ");
        if (!difficultyLevel.equals("3") && !difficultyLevel.equals("2"))
        {
            for (String c : guessedLetters)
            {
                System.out.print(" " + c);
            }
        }
        else System.out.println("Unavailable during level " + difficultyLevel);
    }

    private void setDifficulty()
    {
        Scanner difficulty = new Scanner(System.in);
        System.out.print("Enter a difficulty level 1-3: ");
        difficultyLevel = difficulty.next();
        switch (difficultyLevel)
        {
            case "1" -> gameWord = CreateWord.generateWord(1);
            case "2" -> gameWord = CreateWord.generateWord(2);
            case "3" -> gameWord = CreateWord.generateWord(3);
        }
        DrawMan.printHangMan(guessesLeft);
    }

    @Override
    public void run()
    {
        try
        {
            playGame();
        }
        catch (UnsupportedAudioFileException e)
        {
            System.out.println("The audio file is not supported. Please use WAV format");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("An IO Exception has occurred: ");
            e.printStackTrace();
        }
        catch (LineUnavailableException e)
        {
            System.out.println("The line is unavailable: ");
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            System.out.println("Execution has been interrupted: ");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println("An unknown error has occurred: ");
            e.printStackTrace();
        }
    }

    private void checkGuessesLeft()
    {
        System.out.println("\nGuesses left: " + guessesLeft);
        try
        {
            switch (guessesLeft)
            {
                case 3 -> System.out.println("\n\nHINT: " + gameWordArray[2]);
                case 2 -> System.out.printf("HINTS: %s, %s\n", gameWordArray[2], gameWordArray[1]);
                case 1 -> System.out.printf("HINTS: %s, %s, %s\n", gameWordArray[2], gameWordArray[1], gameWordArray[0]);
                case 0 -> gameOver();
            }
        }
        catch (UnsupportedAudioFileException | IOException | InterruptedException e)
        {
            System.out.println("An error occured: " + e);
            e.printStackTrace();
        }
    }

    private void gameOver() throws UnsupportedAudioFileException, IOException, InterruptedException
    {
        new AudioPlayer("audio/gameOver.wav").run();
        gameEnd = true;
        System.out.println("\n\nYou are Dead");
        System.out.println("The word was: " + gameWord);
        Thread.sleep(11500);
        System.exit(1);
    }
}

