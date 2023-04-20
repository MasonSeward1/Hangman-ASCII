package hangman;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface StartGame
{
    static void main(String[] args) throws UnsupportedAudioFileException, IOException
    {
        Thread audioThread = new Thread(new AudioPlayer("audio/background.wav", true));
        audioThread.start();
        Thread gameThread = new Thread(new Hangman());
        gameThread.start();
    }
}
