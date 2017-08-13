package com.williewheeler.bb.common.actor.model;

import com.williewheeler.bb.common.event.GameEvents;
import com.williewheeler.retroge.actor.brain.ActorBrain;
import com.williewheeler.retroge.actor.model.AbstractActor;
import com.williewheeler.retroge.actor.model.ActorLifecycleState;
import com.williewheeler.retroge.scene.Scene;

public class YardDuty extends AbstractActor {
	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 2;
	private static final int SCORE = 150;

	public static final int ENTER_TTL = 40;
	public static final int EXIT_TTL = 5;

	private int enterTtl = ENTER_TTL;
	private int exitTtl = EXIT_TTL;

	public YardDuty(Scene scene, ActorBrain brain, int x, int y) {
		super(brain, x, y, WIDTH, HEIGHT);
		setScene(scene);
		setSpeed(SPEED);
		setDieEvent(GameEvents.YARD_DUTY_DIED);
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
