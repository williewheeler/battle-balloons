package bb.common.actor.model;

import bb.framework.actor.AbstractActor;
import bb.framework.actor.ActorLifecycleState;

import static bb.common.BBConfig.WORLD_SIZE;

/**
 * Created by willie on 7/3/17.
 */
public class Balloon extends AbstractActor {
	public static final int SPEED = 5;

	private static final int WIDTH = 3;
	private static final int HEIGHT = 3;

	private int dx;
	private int dy;

	public Balloon(int x, int y, int dx, int dy) {
		super(null, x, y, WIDTH, HEIGHT);
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void updateBodyActive() {
		changeX(dx);
		changeY(dy);
		checkGC();
	}

	// TODO Exiting should make a splash if it hits somebody, but not if it just goes off-screen.

	private void checkGC() {
		final int x = getX();
		final int y = getY();

		if (x < 0 || x > WORLD_SIZE.width || y < 0 || y > WORLD_SIZE.height) {
			setState(ActorLifecycleState.GONE);
		}
	}
}
