package com.williewheeler.bb.common.actor.model;

import com.williewheeler.retroge.actor.AbstractActor;
import com.williewheeler.retroge.actor.brain.ActorBrain;
import com.williewheeler.retroge.scene.Scene;

/**
 * Created by willie on 7/5/17.
 */
public class Bully extends AbstractActor {
	private static final int WIDTH = 7;
	private static final int HEIGHT = 14;
	private static final int SPEED = 1;
	private static final int MAX_WALK_TTL = 3;

	private int walkTtl = -1;

	public Bully(Scene scene, ActorBrain brain, int x, int y) {
		super(brain, x, y, WIDTH, HEIGHT);
		setScene(scene);
		setSpeed(SPEED);
	}

	@Override
	public void updateBodyActive() {
		if (walkTtl < 0) {
			this.walkTtl = MAX_WALK_TTL;
		}
		if (walkTtl == 0) {
			doMove();
		}
		this.walkTtl--;
	}
}
