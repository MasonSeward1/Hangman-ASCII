package cp2561_project;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class CreateWord
{
    // Used for testing the generateWord method.
//    public static void main(String[] args)
//    {
//        for (int i = 0; i < 50; i++)
//        System.out.println(generateWord());
//    }

    public static String generateWord(int i) throws UnsupportedEncodingException {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> usedWords = new ArrayList<>();

        InputStream inputStream = ClassLoader.
                getSystemResourceAsStream("hangman_words.txt");
        InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader in = new BufferedReader(streamReader);

        if (i == 2)
            inputStream = ClassLoader.
                    getSystemResourceAsStream("hangman_words_2.txt");
        else if (i == 3)
            inputStream = ClassLoader.
                    getSystemResourceAsStream("hangman_words_3.txt");
        else if (i != 1 && i != 2 && i != 3)
        {
            System.out.println("Invalid Input. Selecting level 1 as default");
        }

        try
        {
            for (String line; (line = in.readLine()) != null;)
            {
                words.add(line);
            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("The file could not be found. " + e);
        } catch (IOException e) {
            e.printStackTrace();
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
