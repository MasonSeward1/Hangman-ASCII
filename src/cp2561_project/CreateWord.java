package cp2561_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateWord
{
    // Used for testing the generateWord method.
//    public static void main(String[] args)
//    {
//        System.out.println(generateWord());
//    }

    public static String generateWord()
    {
        int i = 0;
        String word = "";
        String fileName = "hangman_words.txt";
        ArrayList<String> usedWords = new ArrayList<>();
        boolean duplicateWord = false;

        try
        {
            Scanner readFile = new Scanner(new File(fileName));


            int random_int = generateInt();

            while (readFile.hasNext())
            {
                i++;
                word = readFile.next();
                
                if (usedWords.contains(readFile.next()))
                {
                    duplicateWord = true;
                }
                
                if (i == random_int)
                {
                    if (!duplicateWord)
                    {
                        word = readFile.next();
                        usedWords.add(word);
                        break;
                    }

                    if (duplicateWord)
                    {
                        random_int = generateInt();
                    }
                }
            }

            if (usedWords.size() == 50)
            {
                usedWords.clear();
            }

            readFile.close();
            return word;
        }

        catch (FileNotFoundException e)
        {
            System.out.println("The file could not be found. " + e);
        }
        return word;
    }

    public static int generateInt()
    {
        return (int) Math.floor(Math.random() * (200 - 1 + 1) + 1);
    }
}
