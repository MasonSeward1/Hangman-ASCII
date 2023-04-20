package hangman;

public class DrawMan
{
    public static void printHangMan(int guessesLeft)
    {
        System.out.println("\n");
        if (guessesLeft == 6)
        {
            System.out.println("|----------");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|----------");
        }
        else if (guessesLeft == 5)
        {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|----------");
        }
        else if (guessesLeft == 4)
        {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|    |");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|----------");
        }
        else if (guessesLeft == 3)
        {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|  - |");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|----------");
        }
        else if (guessesLeft == 2)
        {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|  - | -");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|----------");
        }
        else if (guessesLeft == 1)
        {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|  - | -");
            System.out.println("|   /");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|----------");
        }
        else
        {
            System.out.println("|----------");
            System.out.println("|     O");
            System.out.println("|   - | -");
            System.out.println("|    / \\ ");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|----------");
        }
    }
}
