//package cp2561_project;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Scanner;
//
//public class CreateWord
//{
//    // Used for testing the generateWord method.
////    public static void main(String[] args)
////    {
////        for (int i = 0; i < 50; i++)
////        System.out.println(generateWord());
////    }
//
//    public static String generateWord(int i) throws IOException {
//        ArrayList<String> words = new ArrayList<>();
//        ArrayList<String> usedWords = new ArrayList<>();
//        System.out.println(System.getProperty("user.dir"));
//        FileInputStream fis = new FileInputStream("C:\\Users\\mason\\Desktop\\CP2561-Java\\project\\hangman_words.txt");
//        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
//        BufferedReader br = new BufferedReader(isr);
//        String line;
//
//        if (i == 2)
//            fis = new FileInputStream("hangman_words_2.txt");
//        else if (i == 3)
//            fis = new FileInputStream("hangman_words_3.txt");
//        else if (i != 1 && i != 2 && i != 3)
//        {
//            System.out.println("Invalid Input. Selecting level 1 as default");
//        }
//
//        try
//        {
//            while (br.readLine() != null)
//            {
//                line = br.readLine();
//                words.add(line);
//            }
//        }
//
//        catch (FileNotFoundException e)
//        {
//            System.out.println("The file could not be found. " + e);
//        }
//
//        Collections.shuffle(words);
//        usedWords.add(words.toString());
//
//        if (usedWords.contains(words.toString()))
//        {
//            Collections.shuffle(words);
//        }
//
//        return words.get(0);
//    }
//}
