package com.williewheeler.battleballoons.game.world.entity.brain;

import com.williewheeler.battleballoons.common.world.entity.model.Beat;
import com.williewheeler.battleballoons.common.world.event.GameEvents;
import com.williewheeler.battleballoons.game.world.entity.EntityUtil;
import com.williewheeler.battleballoons.game.world.GameScene;
import io.halfling.world.entity.brain.AbstractActorBrain;
import io.halfling.world.entity.model.Actor;
import io.halfling.core.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTurntablesBrain extends AbstractActorBrain {
	private static final Logger log = LoggerFactory.getLogger(DefaultTurntablesBrain.class);

	private static final int MAX_MOVE_TTL = 5;
	private static final int MAX_FIRE_TTL = 60;
	private static final double CHANGE_DIR_THRESHOLD = 0.25;

	private int moveTtl = -1;
	private int fireTtl = RandomUtil.nextRandomInt(MAX_FIRE_TTL);

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
				EntityUtil.randomizeDirection(getActor());
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
			scene.fireGameEvent(GameEvents.BEAT_PLAYED);
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
		return RandomUtil.nextRandomDouble() < CHANGE_DIR_THRESHOLD;
	}
}
