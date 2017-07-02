package bb.framework.io;

import bb.framework.util.Assert;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by willie on 6/2/17.
 */
public class AudioLoader {
	
	public Clip loadSoundEffect(String path) {
		Assert.notNull(path, "path can't be null");
		InputStream is = ClassLoader.getSystemResourceAsStream(path);
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(is);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			return clip;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			throw new RuntimeException(e);
		}
	}
}
