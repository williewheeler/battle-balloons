package com.williewheeler.bb.common.actor.model;

import com.williewheeler.bb.common.event.GameEvents;
import com.williewheeler.retroge.actor.AbstractActor;
import com.williewheeler.retroge.scene.Scene;

/**
 * Created by willie on 7/1/17.
 */
public class Obstacle extends AbstractActor {
	private static final int WIDTH = 7;
	private static final int HEIGHT = 7;
	private static final int SCORE = 50;

	public Obstacle(Scene scene, int x, int y) {
		super(null, x, y, WIDTH, HEIGHT);
		setScene(scene);
		setDieEvent(GameEvents.OBSTACLE_DESTROYED);
	}

	@Override
	public int getScore() {
		return SCORE;
	}
}
