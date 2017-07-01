package bb.framework.model;

import bb.common.model.Direction;

/**
 * Actor data relevant to actual gameplay, such as location and size. Excludes data purely view-related data, such as
 * whether the actor's eyes are open or whether the actor's tapping foot is up vs. down.
 *
 * Created by willie on 6/19/17.
 */
public class BasicActorModel implements ActorModel {
	private ActorBrain brain;
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private Direction direction;

	// TODO Get rid of speed in the constructor. Not all actors have it. [WLW]
	// TODO Add width and height since all actors have it. [WLW]
	public BasicActorModel(ActorBrain brain, int x, int y, int speed) {
		this.brain = brain;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = Direction.DOWN;
	}

	public ActorBrain getBrain() {
		return brain;
	}

	@Override
	public int getX() {
		return x;
	}

	public void changeX(int deltaX) {
		this.x += deltaX;
	}

	@Override
	public int getY() {
		return y;
	}

	public void changeY(int deltaY) {
		this.y += deltaY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

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

	@Override
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void update() {
		if (brain != null) {
			brain.update();
		}
		doUpdate();
	}

	public void doUpdate() {
		// Override this as desired.
	}
}
