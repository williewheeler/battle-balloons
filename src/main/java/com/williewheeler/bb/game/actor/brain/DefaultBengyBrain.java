package com.williewheeler.bb.game.actor.brain;

import com.williewheeler.bb.game.scene.ArenaScene;
import com.williewheeler.retroge.actor.brain.AbstractActorBrain;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.model.DirectionIntent;

/**
 * Created by willie on 7/16/17.
 */
public class DefaultBengyBrain extends AbstractActorBrain {
	private static final int MAX_THINK_TTL = 0;

	private int thinkTtl = -1;

	@Override
	public void update() {
		decrementThinkTtl();
		chasePlayer();
	}

	private void decrementThinkTtl() {
		if (thinkTtl < 0) {
			this.thinkTtl = MAX_THINK_TTL;
		} else {
			this.thinkTtl--;
		}
	}

	private void chasePlayer() {
		final DirectionIntent moveIntent = getMoveDirectionIntent();
		moveIntent.reset();

		if (thinkTtl == 0) {
			final Actor bengy = getActor();
			final ArenaScene scene = (ArenaScene) bengy.getScene();
			final Actor playerActor = scene.getPlayer().getActor();

			final int jx = bengy.getX();
			final int jy = bengy.getY();
			final int px = playerActor.getX();
			final int py = playerActor.getY();

			if (jx < px) {
				moveIntent.right = true;
			} else if (jx > px) {
				moveIntent.left = true;
			}
			if (jy < py) {
				moveIntent.down = true;
			} else if (jy > py) {
				moveIntent.up = true;
			}
		}
	}
}
