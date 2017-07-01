package bb.common.view;

import bb.framework.view.AudioLoader;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayDeque;

/**
 * Created by willie on 6/3/17.
 */
public class AudioFactory {
	private AudioLoader audioLoader = new AudioLoader();
	private ArrayDeque<Clip> playerWalks;
	private ArrayDeque<Clip> playerThrowsBalloon;
	private ArrayDeque<Clip> playerCollision;
	private ArrayDeque<Clip> judoHit;
	
	public AudioFactory() {
		final int clipsPerId = Runtime.getRuntime().availableProcessors();
		this.playerWalks = loadClips("player-walks", clipsPerId, -10.f);
		this.playerThrowsBalloon = loadClips("player-throws-balloon", clipsPerId, -5.0f);
		this.playerCollision = loadClips("oh-woh-woh", clipsPerId, -2.0f);
		this.judoHit = loadClips("explode", clipsPerId, 0.0f);
	}
	
	public void playerWalks() {
		playSoundEffect(playerWalks);
	}
	
	public void playerThrowsBalloon() {
		playSoundEffect(playerThrowsBalloon);
	}
	
	public void playerCollision() {
		playSoundEffect(playerCollision);
	}

	public void judoHit() {
		playSoundEffect(judoHit);
	}

	private ArrayDeque<Clip> loadClips(String id, int clipsPerId, Float gainControlValue) {
		ArrayDeque<Clip> buffer = new ArrayDeque<>();
		for (int i = 0; i < clipsPerId; i++) {
			Clip clip = audioLoader.loadSoundEffect(id);
			if (gainControlValue != null) {
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(gainControlValue);
			}
			buffer.add(clip);
		}
		return buffer;
	}
	
	private synchronized void playSoundEffect(ArrayDeque<Clip> buffer) {
		Clip clip = buffer.poll();
		clip.setFramePosition(0);
		clip.start();
		buffer.add(clip);
	}
}
