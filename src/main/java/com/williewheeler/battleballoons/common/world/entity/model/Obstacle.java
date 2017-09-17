package com.williewheeler.battleballoons.common.world.entity.model;

import com.williewheeler.battleballoons.common.world.event.GameEvents;
import io.halfling.world.entity.model.AbstractActor;
import io.halfling.world.collision.Scene;

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
