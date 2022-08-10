// Started August 7, 2022
package cp2561_project;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman implements Runnable
{
    public static void startGame()
    {
        // Launch background music and game on separate threads
        BackgroundAudio audioPlayer = new BackgroundAudio();
        Hangman game = new Hangman();

        Thread musicThread = new Thread(audioPlayer);
        musicThread.start();

        Thread gameThread = new Thread(game);
        gameThread.start();
    }

    public static void playGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException
    {
        String gameWord = CreateWord.generateWord(1);
        Scanner difficulty = new Scanner(System.in);

        System.out.print("Enter a difficulty level 1-3: ");
        int difficultyLevel = difficulty.nextInt();

        File file = new File("gameOver.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        if (difficultyLevel == 2)
        {
            gameWord = CreateWord.generateWord(2);
        }
        else if (difficultyLevel == 3)
        {
            gameWord = CreateWord.generateWord(3);
        }


        Scanner userInput = new Scanner(System.in);
        ArrayList<String> guessedLetters = new ArrayList<>();

        char[] gameWordArray = gameWord.toCharArray();
        char [] userAnswers = new char[gameWordArray.length];

        boolean gameEnd = false;
        boolean finishedCorrectGuess = true;
        int guessesLeft = 6;

        for (int i = 0 ; i < gameWordArray.length ; i++)
        {
            userAnswers[i] = '?';
        }

        while (!gameEnd)
        {
            boolean letterGuessed = false;

            System.out.println("\n");
            System.out.print("Enter a single letter: ");

            String letter = (userInput.next());

            while (!letter.matches("^[A-Za-z]{1}$"))
            {
                System.out.print("Invalid input. Enter a single letter: ");
                letter = userInput.next();
            }

            guessedLetters.add(letter);

            for (int i = 0 ; i < gameWordArray.length ; i++)
            {
                if (letter.charAt(0) == gameWordArray[i])
                {
                    userAnswers[i] = letter.charAt(0);
                    letterGuessed = true;
                }
            }

            if (!letterGuessed)
            {
                System.out.println("Incorrect letter\n\n");
                guessesLeft--;
            }

            for (char c : userAnswers)
            {
                if (c == '?') {
                    System.out.print(" _");
                    finishedCorrectGuess = false;
                }
                else
                    System.out.print(" " + c);
            }

            System.out.println("\nGuesses left: " + guessesLeft);
            DrawMan.printHangMan(guessesLeft);
            System.out.println("Letter's Guessed: ");

            if (difficultyLevel != 3 && difficultyLevel != 2)
            {
                for (String c : guessedLetters)
                    System.out.print(" " + c);
            }

            else
                System.out.println("Unavailable during level " + difficultyLevel);

            if (guessesLeft <= 3 && guessesLeft > 1)
            {
                System.out.println("HINT: " + gameWordArray[2]);
            }
            else if (guessesLeft == 1)
            {
                System.out.println("\nHINT: " + gameWordArray[0]);
            }

            if (finishedCorrectGuess)
            {
                System.out.println("You have guessed the word!");
                gameEnd = true;
            }

            if (guessesLeft == 0)
            {
                System.out.println("\n\nYou are Dead");
                System.out.println("The word was: " + gameWord);
                clip.start();
                gameEnd = true;
                Thread.sleep(10000);
                System.exit(1);
            }
        }
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
            System.out.println("An IO Exception has occured: ");
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
}
