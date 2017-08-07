package com.williewheeler.bb.common.actor.model;

import com.williewheeler.bb.game.actor.brain.DefaultBullyBrain;
import com.williewheeler.retroge.actor.model.AbstractActor;
import com.williewheeler.retroge.scene.Scene;

public class Beat extends AbstractActor {
	private static final int WIDTH = 13;
	private static final int HEIGHT = 9;
	private static final int SPEED = 5;
	private static final int SCORE = 50;
	private static final int MAX_ANIMATION_INDEX = 3;

	private int animationIndex = 0;

	public Beat(Scene scene, int x, int y) {
		// TODO Give this a DefaultBeatBrain
		super(new DefaultBullyBrain(), x, y, WIDTH, HEIGHT);
		setScene(scene);
		setSpeed(SPEED);
	}

	public int getAnimationIndex() {
		return animationIndex;
	}

	@Override
	public int getScore() {
		return SCORE;
	}

	@Override
	public void updateBodyActive() {
		this.animationIndex = (animationIndex >= MAX_ANIMATION_INDEX ? 0 : animationIndex + 1);
		doMove();
	}
}
