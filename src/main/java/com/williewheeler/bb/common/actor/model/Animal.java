package com.williewheeler.bb.common.actor.model;

import com.williewheeler.bb.common.event.GameEvents;
import com.williewheeler.bb.game.actor.brain.DefaultAnimalBrain;
import com.williewheeler.retroge.actor.model.AbstractActor;
import com.williewheeler.retroge.actor.model.ActorLifecycleState;
import com.williewheeler.retroge.scene.Scene;

/**
 * Created by wwheeler on 7/16/17.
 */
public abstract class Animal extends AbstractActor {
	private static final int WIDTH = 11;
	private static final int HEIGHT = 9;
	private static final int SPEED = 1;
	private static final int SCORE = 1000;
	private static final int MAX_WALK_TTL = 5;
	
	private int walkTtl = -1;
	
	public Animal(Scene scene, int x, int y) {
		super(new DefaultAnimalBrain(), x, y, WIDTH, HEIGHT);
		setScene(scene);
		setSpeed(SPEED);
		setDieEvent(GameEvents.ANIMAL_DIED);
	}
	
	@Override
	public int getScore() {
		return SCORE;
	}
	
	public void rescue() {
		Scene scene = getScene();
		getPlayer().increaseScore(getScore());
		setState(ActorLifecycleState.EXITING);
		fireGameEvent(GameEvents.ANIMAL_RESCUED);
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
