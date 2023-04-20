package hangman;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;

public class AudioPlayer implements Runnable
{
    private final AudioInputStream audioFile;
    private final boolean isLooping;

    public AudioPlayer(String audioFileName, boolean isLooping) throws UnsupportedAudioFileException, IOException
    {
        this.audioFile = AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(AudioPlayer.class.getClassLoader()
                .getResourceAsStream(audioFileName))));
        this.isLooping = isLooping;
    }

    public AudioPlayer(String audioFileName) throws UnsupportedAudioFileException, IOException
    {
        this.audioFile = AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(AudioPlayer.class.getClassLoader()
                .getResourceAsStream(audioFileName))));
        this.isLooping = false;
    }

    @Override
    public void run()
    {
        try
        {
            playAudio();
        }
        catch (LineUnavailableException | IOException e)
        {
            e.printStackTrace();
        }
    }

    private void playAudio() throws LineUnavailableException, IOException
    {
        Clip clip = AudioSystem.getClip();
        clip.open(audioFile);

        if (isLooping)
        {
            while (isLooping)
            {
                clip.start();
            }
        }
        clip.start();
    }
}
