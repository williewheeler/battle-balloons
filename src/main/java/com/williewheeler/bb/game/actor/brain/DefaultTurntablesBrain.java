package com.williewheeler.bb.game.actor.brain;

import com.williewheeler.bb.common.actor.model.Beat;
import com.williewheeler.bb.game.actor.ActorUtil;
import com.williewheeler.bb.game.scene.GameScene;
import com.williewheeler.retroge.actor.brain.AbstractActorBrain;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTurntablesBrain extends AbstractActorBrain {
	private static final Logger log = LoggerFactory.getLogger(DefaultTurntablesBrain.class);

	private static final int MAX_MOVE_TTL = 5;
	private static final int MAX_FIRE_TTL = 60;
	private static final double CHANGE_DIR_THRESHOLD = 0.25;

	private int moveTtl = -1;
	private int fireTtl = -1;

	@Override
	public void updateActive() {
		decrementMoveTtl();
		decrementFireTtl();
		maybeMove();
		maybeFire();
	}

	private void maybeMove() {
		if (moveTtl == 0) {
//			if (aboutToCrash() || feelLikeChangingDir()) {
				ActorUtil.randomizeDirection(getActor());
//			}
		}
	}

	private void maybeFire() {
		if (fireTtl == 0) {
			final Actor turntables = getActor();
			final GameScene scene = (GameScene) turntables.getScene();
			final int turntablesX = turntables.getX();
			final int turntablesY = turntables.getY();
			log.trace("Turntable firing a beat: x={}, y={}", turntablesX, turntablesY);
			scene.getBeats().add(new Beat(scene, turntablesX, turntablesY));
		}
	}

	private void decrementMoveTtl() {
		this.moveTtl = (moveTtl < 0 ? MAX_MOVE_TTL : moveTtl - 1);
	}

	private void decrementFireTtl() {
		this.fireTtl = (fireTtl < 0 ? MAX_FIRE_TTL : fireTtl - 1);
	}

	private boolean aboutToCrash() {
		return false;
	}

	private boolean feelLikeChangingDir() {
		return MathUtil.nextRandomDouble() < CHANGE_DIR_THRESHOLD;
	}
}
