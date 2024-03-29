package hangman;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class CreateWord
{
    public static String generateWord(int i)
    {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> usedWords = new ArrayList<>();
        InputStream inputStream = CreateWord.class.getClassLoader()
                .getResourceAsStream("./words/hangman_words_" + i + ".txt");

        if (i != 1 && i != 2 && i != 3)
        {
            System.out.println("Invalid Input. Selecting level 1 as default");
        }

        assert inputStream != null;
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(streamReader);

        try
        {
            for (String line; (line = in.readLine()) != null; )
            {
                words.add(line);
            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("The file could not be found. " + e);
        }
        catch (IOException e)
        {
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
