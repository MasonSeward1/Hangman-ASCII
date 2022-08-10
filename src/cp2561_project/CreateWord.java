package cp2561_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CreateWord
{
    // Used for testing the generateWord method.
//    public static void main(String[] args)
//    {
//        for (int i = 0; i < 50; i++)
//        System.out.println(generateWord());
//    }

    public static String generateWord(int i)
    {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> usedWords = new ArrayList<>();
        String fileName = "hangman_words.txt";

        if (i == 2)
            fileName = "hangman_words_2.txt";
        else if (i == 3)
            fileName = "hangman_words_3.txt";
        else if (i != 1 && i != 2 && i != 3)
        {
            System.out.println("Invalid Input. Selecting level 1 as default");
        }

        try
        {
            Scanner readFile = new Scanner(new File(fileName));

            while (readFile.hasNext())
            {
                words.add(readFile.nextLine());
            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("The file could not be found. " + e);
        }

        Collections.shuffle(words);
        usedWords.add(words.toString());

        if (usedWords.contains(words.toString()))
        {
            Collections.shuffle(words);
        }

        return words.get(0);
    }
}
