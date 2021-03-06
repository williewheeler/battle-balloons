package com.williewheeler.battleballoons.common.view.resource;

import io.halfling.view.resource.AudioLoader;
import io.halfling.core.Assert;

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
	private final ArrayDeque<Clip> playerDies;
	private final ArrayDeque<Clip> playerFirstLevel;
	private final ArrayDeque<Clip> playerNextLevel;
	private final ArrayDeque<Clip> animalRescued;
	private final ArrayDeque<Clip> animalDies;
	private final ArrayDeque<Clip> judoHit;
	private final ArrayDeque<Clip> beat;
	private final ArrayDeque<Clip> teachers;
	
	public AudioFactory(AudioLoader audioLoader) {
		Assert.notNull(audioLoader, "audioLoader can't be null");
		
		this.audioLoader = audioLoader;
		this.startSound = loadClips("start-sound", -10.0f);
		this.playerWalks = loadClips("player-walks", -10.f);
		this.playerThrowsBalloon = loadClips("player-throws-balloon", -10.0f);
		this.playerDies = loadClips("player-dies", -10.0f);
		this.playerFirstLevel = loadClips("first-level", -10.0f);
		this.playerNextLevel = loadClips("next-level", -10.0f);
		this.animalRescued = loadClips("animal-rescued", -10.0f);
		this.animalDies = loadClips("animal-dies", -10.0f);
		this.judoHit = loadClips("explode", -10.0f);
		this.beat = loadClips("beat", -10.0f);
		this.teachers = loadClips("teachers", -10.0f);
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

	public void playerDies() {
		playSoundEffect(playerDies);
	}

	public void playerFirstLevel() {
		playSoundEffect(playerFirstLevel);
	}

	public void playerNextLevel() {
		playSoundEffect(playerNextLevel);
	}

	public void animalRescued() {
		playSoundEffect(animalRescued);
	}

	public void animalDies() {
		playSoundEffect(animalDies);
	}

	public void judoHit() {
		playSoundEffect(judoHit);
	}

	public void beat() {
		playSoundEffect(beat);
	}

	public void teachers() {
		playSoundEffect(teachers);
	}

	private ArrayDeque<Clip> loadClips(String id, float gainControlValue) {
		final String path = String.format("audio/%s.wav", id);
		final int clipsPerId = Runtime.getRuntime().availableProcessors();
		
		ArrayDeque<Clip> buffer = new ArrayDeque<>();
		for (int i = 0; i < clipsPerId; i++) {
			Clip clip = audioLoader.loadSoundEffect(path);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(gainControlValue);
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
