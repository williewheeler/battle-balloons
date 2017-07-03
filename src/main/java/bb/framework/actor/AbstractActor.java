package bb.framework.actor;

/**
 * Created by willie on 6/19/17.
 */
public abstract class AbstractActor implements Actor {

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
	private boolean readyForGC;

	public AbstractActor(ActorBrain brain, int x, int y, int width, int height) {
		this.brain = brain;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = 0;
		this.direction = Direction.DOWN;
	}

	@Override
	public ActorBrain getBrain() {
		return brain;
	}

	public void setBrain(ActorBrain brain) {
		this.brain = brain;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	public void changeX(int deltaX) {
		this.x += deltaX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	public void changeY(int deltaY) {
		this.y += deltaY;
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

	@Override
	public boolean isReadyForGC() {
		return readyForGC;
	}

	public void setReadyForGC(boolean readyForGC) {
		this.readyForGC = readyForGC;
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
		// Override this as desired.
	}
}
