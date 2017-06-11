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
	private Direction direction;
	private final DirectionIntent moveIntent = new DirectionIntent();
	private int animationCounter;

	public Player(GameModel gameModel) {
		super(gameModel);
		center();
		this.direction = DOWN;
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

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public DirectionIntent getMoveIntent() {
		return moveIntent;
	}

	public int getAnimationCounter() {
		return animationCounter;
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
	
	private void updateLocation(int dx, int dy) {
		changeX(dx);
		changeY(dy);
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

	private void updateDirection(int dx, int dy) {
		if (dx < 0) {
			if (dy < 0) {
				this.direction = UP_LEFT;
			} else if (dy == 0) {
				this.direction = LEFT;
			} else {
				this.direction = DOWN_LEFT;
			}
		} else if (dx == 0) {
			if (dy < 0) {
				this.direction = UP;
			} else if (dy == 0) {
				// Do nothing
			} else {
				this.direction = DOWN;
			}
		} else {
			if (dy < 0) {
				this.direction = UP_RIGHT;
			} else if (dy == 0) {
				this.direction = RIGHT;
			} else {
				this.direction = DOWN_RIGHT;
			}
		}
	}

	private void incrementAnimationCounter() {
		this.animationCounter = (animationCounter + 1) % 4;
	}
}
