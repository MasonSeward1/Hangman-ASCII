package cp2561_project;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;

public class AudioPlayer implements Runnable
{
    private final AudioInputStream audioFile;
    private final boolean isLooping;

    public AudioPlayer(String audioFileName, boolean isLooping) throws UnsupportedAudioFileException, IOException {
        this.audioFile = AudioSystem.getAudioInputStream(new BufferedInputStream(AudioPlayer.class.getClassLoader().getResourceAsStream(audioFileName)));
        this.isLooping = isLooping;
    }

    public AudioPlayer(String audioFileName) throws UnsupportedAudioFileException, IOException {
        this.audioFile = AudioSystem.getAudioInputStream(new BufferedInputStream(AudioPlayer.class.getClassLoader().getResourceAsStream(audioFileName)));
        this.isLooping = false;
    }

    @Override
    public void run()
    {
        try {
            playAudio();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    private void playAudio() throws LineUnavailableException, IOException {
        Clip clip = AudioSystem.getClip();
        clip.open(audioFile);

        while (isLooping)
            clip.start();

        if (!isLooping)
            clip.start();
    }
}
