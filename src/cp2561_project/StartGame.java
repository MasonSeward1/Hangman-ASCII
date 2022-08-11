package cp2561_project;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface StartGame
{
    static void main(String[] args) throws UnsupportedAudioFileException, IOException {
        Hangman.startGame();
    }
}
