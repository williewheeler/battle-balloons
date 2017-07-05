package bb.common.resource;

import bb.framework.resource.AudioLoader;
import bb.framework.util.Assert;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayDeque;

/**
 * Created by willie on 6/3/17.
 */
public class AudioFactory {
	private final AudioLoader audioLoader;
	private final ArrayDeque<Clip> startSound;
	private final ArrayDeque<Clip> playerWalks;
	private final ArrayDeque<Clip> playerThrowsBalloon;
	private final ArrayDeque<Clip> playerCollision;
	private final ArrayDeque<Clip> playerFirstLevel;
	private final ArrayDeque<Clip> playerNextLevel;
	private final ArrayDeque<Clip> judoHit;
	
	public AudioFactory(AudioLoader audioLoader) {
		Assert.notNull(audioLoader, "audioLoader can't be null");
		this.audioLoader = audioLoader;
		final int clipsPerId = Runtime.getRuntime().availableProcessors();
		this.startSound = loadClips("start-sound", clipsPerId, 1.0f);
		this.playerWalks = loadClips("player-walks", clipsPerId, -10.f);
		this.playerThrowsBalloon = loadClips("player-throws-balloon", clipsPerId, -5.0f);
		this.playerCollision = loadClips("oh-woh-woh", clipsPerId, -2.0f);
		this.playerFirstLevel = loadClips("first-level", clipsPerId, 1.0f);
		this.playerNextLevel = loadClips("next-level", clipsPerId, 1.0f);
		this.judoHit = loadClips("explode", clipsPerId, 0.0f);
	}

	public void startSound() {
		playSoundEffect(startSound);
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

	public void playerFirstLevel() {
		playSoundEffect(playerFirstLevel);
	}

	public void playerNextLevel() {
		playSoundEffect(playerNextLevel);
	}

	public void judoHit() {
		playSoundEffect(judoHit);
	}

	private ArrayDeque<Clip> loadClips(String id, int clipsPerId, Float gainControlValue) {
		String path = String.format("audio/%s.wav", id);
		ArrayDeque<Clip> buffer = new ArrayDeque<>();
		for (int i = 0; i < clipsPerId; i++) {
			Clip clip = audioLoader.loadSoundEffect(path);
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
