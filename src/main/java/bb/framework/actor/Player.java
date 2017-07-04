package bb.framework.actor;

import bb.framework.util.Assert;

/**
 * Created by willie on 7/2/17.
 */
public class Player {
	private Actor actor;
	private int lives = 3;
	private int level = 1;
	private int score = 0;

	public Player(Actor actor) {
		Assert.notNull(actor, "actor can't be null");
		this.actor = actor;
	}

	public Actor getActor() {
		return actor;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
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
	}
}
