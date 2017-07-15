package bb.common.actor.model;

import retroge.actor.AbstractActor;
import retroge.actor.brain.ActorBrain;

/**
 * Created by willie on 7/5/17.
 */
public class Bully extends AbstractActor {
	private static final int WIDTH = 7;
	private static final int HEIGHT = 14;
	private static final int SPEED = 2;
	private static final int MAX_WALK_TTL = 11;

	private int walkTtl = -1;

	public Bully(ActorBrain brain, int x, int y) {
		super(brain, x, y, WIDTH, HEIGHT);
		setSpeed(SPEED);
	}

	@Override
	public void updateBodyActive() {
		if (walkTtl < 0) {
			this.walkTtl = MAX_WALK_TTL;
		}
		if (walkTtl == 0) {
			doMove();
		}
		this.walkTtl--;
	}
}
