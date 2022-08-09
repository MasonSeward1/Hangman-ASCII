package cp2561_project;

import java.util.ArrayList;
import java.util.Scanner;

public class Hangman
{
    public static void playGame()
    {
        String gameWord = CreateWord.generateWord();
        Scanner userInput = new Scanner(System.in);

        ArrayList<String> lettersGuessed = new ArrayList<>();

        char[] gameWordArray = gameWord.toCharArray();
        char [] userAnswers = new char[gameWordArray.length];

        boolean gameEnd = false;
        boolean finishedGuess = true;
        int guessesLeft = 6;

        for (int i = 0 ; i < gameWordArray.length ; i++)
        {
            userAnswers[i] = '?';
        }

        while (!gameEnd)
        {
            boolean letterGuessed = false;

            System.out.println("\n\n");
            System.out.print("Enter a single letter: ");

            String letter = userInput.next();

            while (!letter.matches("^[A-Za-z]{1}$") || Character.isDigit(letter.charAt(0)))
            {
                System.out.print("Invalid input. Enter a single letter: ");
                letter = userInput.next();
            }

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
                    finishedGuess = false;
                } else
                    System.out.print(" " + c);
            }

            System.out.println("\nGuesses left: " + guessesLeft);
            DrawMan.printHangMan(guessesLeft);
            System.out.println("Letter's Guessed: ");
            for (char c : userAnswers)
                System.out.print(" " + c);



            if (finishedGuess)
            {
                System.out.println("You have guessed the word!");
                gameEnd = true;
            }

            if (guessesLeft == 0)
            {
                System.out.println("\n\nYou are Dead");
                gameEnd = true;
            }


        }
     }
}
