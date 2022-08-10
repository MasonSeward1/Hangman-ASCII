package cp2561_project;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlayAudio implements Runnable
{

    public static void playAudio(boolean stopPlaying) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        File file = new File("background.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        while (!stopPlaying)
        {
            clip.start();
        }
    }

    @Override
    public void run()
    {
        try {
            playAudio(false);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
