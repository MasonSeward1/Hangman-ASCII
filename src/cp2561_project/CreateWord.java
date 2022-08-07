package cp2561_project;

import java.io.File;
import java.io.FileNotFoundException;
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
        try {
            Scanner readFile = new Scanner(new File(fileName));


            int random_int = (int) Math.floor(Math.random() * (200 - 1 + 1) + 1);

            while (readFile.hasNext())
            {
                i++;
                word = readFile.next();
                if (i == random_int)
                {
                    word = readFile.next();
                    break;
                }
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
}
