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

    public static String generateWord()
    {
        String fileName = "hangman_words.txt";
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> usedWords = new ArrayList<>();

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
