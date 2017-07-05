package bb.framework.actor;

import bb.framework.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by willie on 7/2/17.
 */
public class Player {
	private static final Logger log = LoggerFactory.getLogger(Player.class);

	// TODO Just a simple implementation for now. Can revisit this later.
	private static final int NEXT_LIFE_AT_INCREMENT = 25000;

	private Actor actor;
	private int lives = 3;
	private int level = 1;
	private int score = 0;
	private int nextLifeAt = NEXT_LIFE_AT_INCREMENT;

	public Player(Actor actor) {
		Assert.notNull(actor, "actor can't be null");
		this.actor = actor;
	}

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
		log.trace("lives={}", lives);
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
		if (score >= nextLifeAt) {
			incrementLives();
			this.nextLifeAt += NEXT_LIFE_AT_INCREMENT;
		}
	}
}
