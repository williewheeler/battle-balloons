package bb.model;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/5/17.
 */
public class Player {
	private int score = 1000;
	private int level = 1;
	private int lives = 3;
	private int x;
	private int y;
	private final DirectionIntent moveIntent = new DirectionIntent();

	public Player() {
		center();
	}

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

	public int getY() {
		return y;
	}

	public int getWidth() {
		return PLAYER_WIDTH;
	}

	public int getHeight() {
		return PLAYER_HEIGHT;
	}

	public DirectionIntent getMoveIntent() {
		return moveIntent;
	}

	public void center() {
		this.x = ARENA_INNER_WIDTH_PX / 2;
		this.y = ARENA_INNER_HEIGHT_PX / 2;
	}

	public void update() {
		int dx = 0;
		int dy = 0;

		if (moveIntent.up) {
			dy -= PLAYER_SPEED;
		}
		if (moveIntent.down) {
			dy += PLAYER_SPEED;
		}
		if (moveIntent.left) {
			dx -= PLAYER_SPEED;
		}
		if (moveIntent.right) {
			dx += PLAYER_SPEED;
		}

		this.x += dx;
		this.y += dy;

		enforceBounds();
	}

	private void enforceBounds() {
		int playerHalfWidth = getWidth() / 2;
		int playerHalfHeight = getHeight() / 2;

		// All bounds below are inclusive
		int playerXLo = x - playerHalfWidth;
		int playerXHi = playerXLo + getWidth() - 1;
		int playerYLo = y - playerHalfHeight;
		int playerYHi = playerYLo + getHeight() - 1;

		int arenaXLo = 0;
		int arenaXHi = ARENA_INNER_WIDTH_PX - 1;
		int arenaYLo = 0;
		int arenaYHi = ARENA_INNER_HEIGHT_PX - 1;

		if (playerXLo < arenaXLo) {
			this.x = playerHalfWidth;
		}
		if (playerXHi > arenaXHi) {
			this.x = arenaXHi - playerHalfWidth;
		}
		if (playerYLo < arenaYLo) {
			this.y = playerHalfHeight;
		}
		if (playerYHi > arenaYHi) {
			this.y = arenaYHi - playerHalfHeight;
		}
	}
}
