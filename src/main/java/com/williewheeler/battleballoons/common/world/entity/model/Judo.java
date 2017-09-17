package com.williewheeler.battleballoons.common.world.entity.model;

import com.williewheeler.battleballoons.common.world.event.GameEvents;
import io.halfling.world.entity.model.AbstractActor;
import io.halfling.world.entity.model.ActorLifecycleState;
import io.halfling.world.entity.brain.ActorBrain;
import io.halfling.world.collision.Scene;

/**
 * Created by willie on 7/2/17.
 */
public class Judo extends AbstractActor {
	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 2;
	private static final int SCORE = 100;
	
	public static final int ENTER_TTL = 20;
	public static final int EXIT_TTL = 5;

	private int enterTtl = ENTER_TTL;
	private int exitTtl = EXIT_TTL;

	public Judo(Scene scene, ActorBrain brain, int x, int y) {
		super(brain, x, y, WIDTH, HEIGHT);
		setScene(scene);
		setSpeed(SPEED);
		setDieEvent(GameEvents.JUDO_DIED);
	}

	@Override
	public int getScore() {
		return SCORE;
	}

	public int getEnterTtl() {
		return enterTtl;
	}

	public int getExitTtl() {
		return exitTtl;
	}

	@Override
	public void updateBodyEntering() {
		this.enterTtl--;
		if (enterTtl <= 0) {
			this.enterTtl = ENTER_TTL;
			setState(ActorLifecycleState.ACTIVE);
		}
	}

	@Override
	public void updateBodyActive() {
		doMove();
	}

	@Override
	public void updateBodyExiting() {
		this.exitTtl--;
		if (exitTtl <= 0) {
			this.exitTtl = EXIT_TTL;
			setState(ActorLifecycleState.GONE);
		}
	}
}
