package bb.game.arena.actor;

import bb.framework.actor.brain.AbstractActorBrain;
import bb.framework.util.MathUtil;

/**
 * Created by willie on 7/5/17.
 */
public class DefaultBullyBrain extends AbstractActorBrain {
	private static final int MAX_THINK_TTL = 10;
	private static final double CHANGE_DIR_THRESHOLD = 0.25;

	private int thinkTtl = -1;

	@Override
	public void update() {
		decrementThinkTtl();
		walkAround();
	}

	private void decrementThinkTtl() {
		if (thinkTtl < 0) {
			this.thinkTtl = MAX_THINK_TTL;
		} else {
			this.thinkTtl--;
		}
	}

	private void walkAround() {
		if (aboutToCrash() || feelLikeChangingDir()) {
			ActorUtil.randomizeDirection(getActor());
		}
	}

	private boolean aboutToCrash() {
		return false;
	}

	private boolean feelLikeChangingDir() {
		return thinkTtl == 0 && MathUtil.nextRandomDouble() < CHANGE_DIR_THRESHOLD;
	}
}
