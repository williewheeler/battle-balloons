package bb.framework.model.actor;

import bb.common.model.Direction;
import bb.framework.util.Assert;

/**
 * Actor data relevant to actual gameplay, such as location and size. Excludes data purely view-related data, such as
 * whether the actor's eyes are open or whether the actor's tapping foot is up vs. down.
 *
 * Created by willie on 6/19/17.
 */
public abstract class AbstractActorModel implements ActorModel {
	private ActorBrain brain;
	private int x;
	private int y;
	private int speed;
	private Direction direction;

	public AbstractActorModel(ActorBrain brain, int x, int y, int speed) {
		Assert.notNull(brain, "brain can't be null");
		this.brain = brain;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = Direction.DOWN;
	}

	@Override
	public ActorBrain getBrain() {
		return brain;
	}

	@Override
	public int getX() {
		return x;
	}

	protected void changeX(int deltaX) {
		this.x += deltaX;
	}

	@Override
	public int getY() {
		return y;
	}

	protected void changeY(int deltaY) {
		this.y += deltaY;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	protected void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void update() {
		brain.update();
		doUpdate();
	}

	public abstract void doUpdate();
}
