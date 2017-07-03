package bb.common.actor.model;

import bb.common.scene.Scene;
import bb.framework.actor.Actor;
import bb.framework.actor.ActorBrain;
import bb.framework.actor.Direction;
import bb.framework.actor.DirectionIntent;
import bb.framework.util.Assert;

import static bb.common.BBConfig.ARENA_INNER_HEIGHT_PX;
import static bb.common.BBConfig.ARENA_INNER_WIDTH_PX;
import static bb.framework.actor.Direction.*;

/**
 * Created by willie on 6/19/17.
 */
public abstract class AbstractActor implements Actor {
	private static final int WALK_NUM_FRAMES = 4;

	// State
	private ActorState state;

	// World
	private Scene scene;

	// Brain
	private ActorBrain brain;

	// Body
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private Direction direction;

	// Other
	private int walkCounter = 0;

	public AbstractActor(Scene scene, ActorBrain brain, int x, int y, int width, int height) {
		Assert.notNull(scene, "scene can't be null");

		this.state = ActorState.ENTERING;
		this.scene = scene;
		this.brain = brain;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = 0;
		this.direction = Direction.DOWN;

		if (brain != null) {
			brain.setActor(this);
		}
	}

	@Override
	public ActorState getState() {
		return state;
	}

	@Override
	public void setState(ActorState state) {
		this.state = state;
	}

	public Scene getScene() {
		return scene;
	}

	@Override
	public ActorBrain getBrain() {
		return brain;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void changeX(int dx) {
		this.x += dx;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void changeY(int dy) {
		this.y += dy;
	}

	@Override
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getWalkCounter() { return walkCounter; }

	@Override
	public void update() {
		updateBrain();
		updateBody();
	}

	public void updateBrain() {
		if (brain != null) {
			brain.update();
		}
	}

	public void updateBody() {
		switch (state) {
			case ENTERING:
				updateBodyEntering();
				break;
			case ACTIVE:
				updateBodyActive();
				break;
			case EXITING:
				updateBodyExiting();
				break;
		}
	}

	/**
	 * Override as desired.
	 */
	public void updateBodyEntering() {
		setState(ActorState.ACTIVE);
	}

	/**
	 * Override as desired.
	 */
	public void updateBodyActive() {
	}

	/**
	 * Override as desired.
	 */
	public void updateBodyExiting() {
		setState(ActorState.GONE);
	}

	@Override
	public boolean checkCollision(Actor that) {
		Assert.notNull(that, "that can't be null");

		int thisLoX = getX() - getWidth() / 2;
		int thisLoY = getY() - getHeight() / 2;
		int thisHiX = thisLoX + getWidth();
		int thisHiY = thisLoY + getHeight();

		int thatLoX = that.getX() - that.getWidth() / 2;
		int thatLoY = that.getY() - that.getHeight() / 2;
		int thatHiX = thatLoX + that.getHeight();
		int thatHiY = thatLoY + that.getHeight();

		boolean xOverlap = (thisLoX <= thatHiX && thisHiX >= thatLoX);
		boolean yOverlap = (thisLoY <= thatHiY && thisHiY >= thatLoY);

		return xOverlap && yOverlap;
	}

	protected void doWalk() {
		final ActorBrain brain = getBrain();
		final DirectionIntent moveIntent = brain.getMoveDirectionIntent();
		final int speed = getSpeed();

		int dx = 0;
		int dy = 0;

		if (moveIntent.up) {
			dy -= speed;
		}
		if (moveIntent.down) {
			dy += speed;
		}
		if (moveIntent.left) {
			dx -= speed;
		}
		if (moveIntent.right) {
			dx += speed;
		}

		changeX(dx);
		changeY(dy);
		enforceBounds();

		Direction direction = calculateDirection(dx, dy);
		if (direction != null) {
			setDirection(direction);
			this.walkCounter = (walkCounter + 1) % WALK_NUM_FRAMES;
		}
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

	private static Direction calculateDirection(int deltaX, int deltaY) {
		Direction direction = null;
		if (deltaY < 0) {
			if (deltaX < 0) {
				direction = UP_LEFT;
			} else if (deltaX > 0) {
				direction = UP_RIGHT;
			} else {
				direction = UP;
			}
		} else if (deltaY > 0) {
			if (deltaX < 0) {
				direction = DOWN_LEFT;
			} else if (deltaX > 0) {
				direction = DOWN_RIGHT;
			} else {
				direction = DOWN;
			}
		} else {
			if (deltaX < 0) {
				direction = LEFT;
			} else if (deltaX > 0) {
				direction = RIGHT;
			} else {
				direction = null;
			}
		}
		return direction;
	}
}
