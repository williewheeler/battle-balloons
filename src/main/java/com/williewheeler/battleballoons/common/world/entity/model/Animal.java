package com.williewheeler.battleballoons.common.world.entity.model;

import com.williewheeler.battleballoons.common.world.event.GameEvents;
import com.williewheeler.battleballoons.game.world.entity.brain.DefaultAnimalBrain;
import io.halfling.world.entity.model.AbstractActor;
import io.halfling.world.entity.model.ActorLifecycleState;
import io.halfling.world.collision.Scene;

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
