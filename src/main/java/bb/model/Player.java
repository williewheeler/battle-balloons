package bb.model;

import static bb.BBConfig.*;
import static bb.model.Direction.*;

/**
 * Created by willie on 6/5/17.
 */
public class Player extends AbstractEntity {
	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 3;

	private int score = 1000;
	private int level = 1;
	private int lives = 3;
	private final DirectionIntent moveIntent = new DirectionIntent();

	public Player(GameModel gameModel) {
		super(gameModel);
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

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	public DirectionIntent getMoveIntent() {
		return moveIntent;
	}

	public void center() {
		setX(ARENA_INNER_WIDTH_PX / 2);
		setY(ARENA_INNER_HEIGHT_PX / 2);
	}

	@Override
	public void update() {
		int dx = 0;
		int dy = 0;

		if (moveIntent.up) {
			dy -= SPEED;
		}
		if (moveIntent.down) {
			dy += SPEED;
		}
		if (moveIntent.left) {
			dx -= SPEED;
		}
		if (moveIntent.right) {
			dx += SPEED;
		}

		if (dx != 0 || dy != 0) {
			updateLocation(dx, dy);
			updateDirection(dx, dy);
			incrementAnimationCounter();
		}
	}

	protected void updateLocation(int dx, int dy) {
		super.updateLocation(dx, dy);
		enforceBounds();
	}

	private void enforceBounds() {
		int playerHalfWidth = getWidth() / 2;
		int playerHalfHeight = getHeight() / 2;

		// All bounds below are inclusive
		int playerXLo = getX() - playerHalfWidth;
		int playerXHi = playerXLo + getWidth() - 1;
		int playerYLo = getY() - playerHalfHeight;
		int playerYHi = playerYLo + getHeight() - 1;

		int arenaXLo = 0;
		int arenaXHi = ARENA_INNER_WIDTH_PX - 1;
		int arenaYLo = 0;
		int arenaYHi = ARENA_INNER_HEIGHT_PX - 1;

		if (playerXLo < arenaXLo) {
			setX(playerHalfWidth);
		}
		if (playerXHi > arenaXHi) {
			setX(arenaXHi - playerHalfWidth);
		}
		if (playerYLo < arenaYLo) {
			setY(playerHalfHeight);
		}
		if (playerYHi > arenaYHi) {
			setY(arenaYHi - playerHalfHeight);
		}
	}
}
