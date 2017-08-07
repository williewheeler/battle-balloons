package com.williewheeler.bb.game.actor.brain;

import com.williewheeler.bb.common.actor.model.Turntables;
import com.williewheeler.bb.game.scene.GameScene;
import com.williewheeler.retroge.actor.brain.AbstractActorBrain;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.model.DirectionIntent;

/**
 * Created by willie on 7/16/17.
 */
public class DefaultBengyBrain extends AbstractActorBrain {
	private static final int MAX_MOVE_TTL = 2;
	private static final int MAX_FIRE_TTL = 60;

	private int walkTtl = -1;
	private int throwTtl = -1;

	@Override
	public void updateActive() {
		maybeWalk();
		maybeThrow();
	}

	private void maybeWalk() {
		final DirectionIntent moveIntent = getMoveDirectionIntent();
		moveIntent.reset();

		if (wantsToWalk()) {
			final Actor bengy = getActor();
			final GameScene scene = (GameScene) bengy.getScene();
			final Actor playerActor = scene.getPlayer().getActor();

			final int bengyX = bengy.getX();
			final int bengyY = bengy.getY();
			final int playerX = playerActor.getX();
			final int playerY = playerActor.getY();

			if (bengyX < playerX) {
				moveIntent.right = true;
			} else if (bengyX > playerX) {
				moveIntent.left = true;
			}
			if (bengyY < playerY) {
				moveIntent.down = true;
			} else if (bengyY > playerY) {
				moveIntent.up = true;
			}
		}
	}

	private void maybeThrow() {
		if (wantsToThrow()) {
			final Actor bengy = getActor();
			final GameScene scene = (GameScene) bengy.getScene();
			final int bengyX = bengy.getX();
			final int bengyY = bengy.getY();
			scene.getTurntables().add(new Turntables(scene, bengyX, bengyY));
		}
	}

	private boolean wantsToWalk() {
		decrementWalkTtl();
		return walkTtl == 0;
	}

	private boolean wantsToThrow() {
		decrementThrowTtl();
		return throwTtl == 0;
	}

	private void decrementWalkTtl() {
		this.walkTtl = (walkTtl < 0 ? MAX_MOVE_TTL : walkTtl - 1);
	}

	private void decrementThrowTtl() {
		this.throwTtl = (throwTtl < 0 ? MAX_FIRE_TTL : throwTtl - 1);
	}
}
