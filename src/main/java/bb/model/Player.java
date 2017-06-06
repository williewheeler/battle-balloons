package bb.model;

/**
 * Created by willie on 6/5/17.
 */
public class Player {
	private int score = 1000;
	private int level = 1;
	private int lives = 3;
	private int x;
	private int y;

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public int getLives() {
		return lives;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
