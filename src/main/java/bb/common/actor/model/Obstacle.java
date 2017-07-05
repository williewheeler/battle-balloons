package bb.common.actor.model;

/**
 * Created by willie on 7/1/17.
 */
public class Obstacle extends AbstractActor {
	private static final int WIDTH = 7;
	private static final int HEIGHT = 7;
	private static final int SCORE = 50;

	public Obstacle(int x, int y) {
		super(null, x, y, WIDTH, HEIGHT);
	}

	@Override
	public int getScore() {
		return SCORE;
	}
}
