package retroge.actor;

import retroge.actor.brain.ActorBrain;
import retroge.scene.Scene;
import retroge.util.Assert;

/**
 * Created by willie on 6/19/17.
 */
public abstract class AbstractActor implements Actor {
	
	// TODO Don't hardcode these [WLW]
	private static final int WALK_ANIMATION_NUM_FRAMES = 4;
	// FIXME Temporarily protected so Lexi can see it
	protected static final int RECHARGE_TTL = 3;

	// General
	private ActorLifecycleState state;
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
	// FIXME Temporarily protected so Lexi can see it
	protected int rechargeTtl = 0;

	public AbstractActor(ActorBrain brain, int x, int y, int width, int height) {
		this.state = ActorLifecycleState.ENTERING;
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
	public ActorLifecycleState getState() {
		return state;
	}

	@Override
	public void setState(ActorLifecycleState state) {
		this.state = state;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
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
	public int getScore() {
		return 0;
	}

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
		setState(ActorLifecycleState.ACTIVE);
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
		setState(ActorLifecycleState.GONE);
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
	
	// TODO This might be better as a component, since not all entities (actors) move.
	protected boolean doMove() {
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

		if (direction == null) {
			return false;
		} else {
			setDirection(direction);
			this.walkCounter = (walkCounter + 1) % WALK_ANIMATION_NUM_FRAMES;
			return true;
		}
	}

	private void enforceBounds() {
		int halfActorWidth = getWidth() / 2;
		int halfActorHeight = getHeight() / 2;

		// All bounds below are inclusive
		int minActorX = getX() - halfActorWidth;
		int maxActorX = minActorX + getWidth() - 1;
		int minActorY = getY() - halfActorHeight;
		int maxActorY = minActorY + getHeight() - 1;
		
		if (minActorX < scene.getMinWorldX()) {
			setX(scene.getMinWorldX() + halfActorWidth);
		}
		if (maxActorX > scene.getMaxWorldX()) {
			setX(scene.getMaxWorldX() - halfActorWidth);
		}
		if (minActorY < scene.getMinWorldY()) {
			setY(scene.getMinWorldY() + halfActorHeight);
		}
		if (maxActorY > scene.getMaxWorldY()) {
			setY(scene.getMaxWorldY() - halfActorHeight);
		}
	}

	private static Direction calculateDirection(int deltaX, int deltaY) {
		Direction direction = null;
		if (deltaY < 0) {
			if (deltaX < 0) {
				direction = Direction.UP_LEFT;
			} else if (deltaX > 0) {
				direction = Direction.UP_RIGHT;
			} else {
				direction = Direction.UP;
			}
		} else if (deltaY > 0) {
			if (deltaX < 0) {
				direction = Direction.DOWN_LEFT;
			} else if (deltaX > 0) {
				direction = Direction.DOWN_RIGHT;
			} else {
				direction = Direction.DOWN;
			}
		} else {
			if (deltaX < 0) {
				direction = Direction.LEFT;
			} else if (deltaX > 0) {
				direction = Direction.RIGHT;
			} else {
				direction = null;
			}
		}
		return direction;
	}
}
