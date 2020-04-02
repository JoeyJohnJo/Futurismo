package futurismo;

import futurismo.framework.Frame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.start();
        musicPlayer.start();
    }
    static Thread musicPlayer = new Thread(() -> {
        try {
            FileInputStream filename = new FileInputStream(Main.class.getResource("/res/music.wav").getPath());
            BufferedInputStream bis = new BufferedInputStream(filename);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (Exception e) {
            e.printStackTrace();

        }
    });

}
