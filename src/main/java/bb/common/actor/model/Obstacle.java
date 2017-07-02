package bb.common.actor.model;

import bb.framework.actor.AbstractActor;

/**
 * Created by willie on 7/1/17.
 */
public class Obstacle extends AbstractActor {
	private static final int WIDTH = 8;
	private static final int HEIGHT = 8;

	public Obstacle(int x, int y) {
		super(null, x, y, WIDTH, HEIGHT);
	}
}
