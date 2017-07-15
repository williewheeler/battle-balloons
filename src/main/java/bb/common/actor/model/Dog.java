package bb.common.actor.model;

import retroge.actor.AbstractActor;
import retroge.actor.brain.ActorBrain;

/**
 * Created by willie on 7/6/17.
 */
public class Dog extends AbstractActor {
	private static final int WIDTH = 11;
	private static final int HEIGHT = 9;
	private static final int SPEED = 1;
	private static final int MAX_WALK_TTL = 5;
	private static final int SCORE = 1000;

	private int walkTtl = -1;

	public Dog(ActorBrain brain, int x, int y) {
		super(brain, x, y, WIDTH, HEIGHT);
		setSpeed(SPEED);
	}

	@Override
	public int getScore() {
		// TODO Make this more dynamic as we need to increase it during the level.
		return SCORE;
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
