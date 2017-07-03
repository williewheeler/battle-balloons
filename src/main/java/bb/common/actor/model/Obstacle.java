package bb.common.actor.model;

import bb.common.scene.Scene;

/**
 * Created by willie on 7/1/17.
 */
public class Obstacle extends AbstractActor {
	private static final int WIDTH = 8;
	private static final int HEIGHT = 8;
	private static final int SCORE = 50;

	public Obstacle(Scene scene, int x, int y) {
		super(scene, null, x, y, WIDTH, HEIGHT);
	}

	@Override
	public int getScore() {
		return SCORE;
	}
}
