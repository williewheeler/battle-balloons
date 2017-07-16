package com.williewheeler.retroge.actor;

import com.williewheeler.bb.common.event.GameEvents;
import com.williewheeler.retroge.GameConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by willie on 7/2/17.
 */
public class Player {
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	
	// TODO Just a simple implementation for now. Can revisit this later.
	private static final int EXTRA_LIFE_INCREMENT = 25000;

	private Actor actor;
	private int lives = 3;
	private int level = 0;
	private int score = 0;
	private int extraLifeAt = EXTRA_LIFE_INCREMENT;

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public int getLives() {
		return lives;
	}

	public void incrementLives() {
		this.lives++;
	}

	public void decrementLives() {
		this.lives = Math.max(0, lives - 1);
		log.trace("{} lives remaining", lives);
	}

	public boolean isGameOver() {
		return lives <= 0;
	}

	public int getLevel() {
		return level;
	}

	public void incrementLevel() {
		this.level++;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(int amount) {
		this.score += amount;
		if (score >= extraLifeAt) {
			incrementLives();
			this.extraLifeAt += EXTRA_LIFE_INCREMENT;
		}
	}
	
	public void spawn() {
		log.trace("Spawning player");
		actor.getBrain().reset();
		actor.setX(GameConfig.WORLD_SIZE.width / 2);
		actor.setY(GameConfig.WORLD_SIZE.height / 2);
		actor.setState(ActorLifecycleState.ENTERING);
	}
	
	public void die() {
		decrementLives();
		actor.setState(ActorLifecycleState.EXITING);
		actor.getScene().fireGameEvent(GameEvents.PLAYER_DIED);
	}
	
//	@Override
//	public String toString() {
//		// FIXME Remove hardcode
//		Lexi lexi = (Lexi) actor;
//		return new StringBuilder("[Player:")
//				.append(" actor.state=")
//				.append(actor.getState())
//				.append(", actor.enterTtl=")
//				.append(lexi.getEnterTtl())
//				.append("]")
//				.toString();
//	}
}
