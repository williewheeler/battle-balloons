package com.williewheeler.battleballoons.game.world.entity.brain;

import com.williewheeler.battleballoons.game.world.entity.EntityUtil;
import io.halfling.world.entity.brain.AbstractActorBrain;
import io.halfling.core.RandomUtil;

/**
 * Created by willie on 7/5/17.
 */
public class DefaultBullyBrain extends AbstractActorBrain {
	private static final int MAX_THINK_TTL = 30;
	private static final double CHANGE_DIR_THRESHOLD = 0.25;

	private int thinkTtl = -1;

	@Override
	public void updateActive() {
		decrementThinkTtl();
		walkAround();
	}

	private void decrementThinkTtl() {
		this.thinkTtl = (thinkTtl < 0 ? MAX_THINK_TTL : thinkTtl - 1);
	}

	private void walkAround() {
		if (aboutToCrash() || feelLikeChangingDir()) {
			EntityUtil.randomizeDirectionNoDiagonals(getActor());
		}
	}

	private boolean aboutToCrash() {
		return false;
	}

	private boolean feelLikeChangingDir() {
		return thinkTtl == 0 && RandomUtil.nextRandomDouble() < CHANGE_DIR_THRESHOLD;
	}
}
