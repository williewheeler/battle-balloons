package com.williewheeler.bb.game.actor.brain;

import com.williewheeler.bb.game.scene.GameScene;
import com.williewheeler.retroge.actor.brain.AbstractActorBrain;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.model.DirectionIntent;
import com.williewheeler.retroge.util.MathUtil;

// FIXME Would be better if this wasn't tied to either the arena or to Judo.
// I could see having this be a "PlayerTrackingBrain", and whoever has it just follows the player around.

/**
 * Created by willie on 7/2/17.
 */
public class DefaultJudoBrain extends AbstractActorBrain {
	private static final int MAX_THINK_TTL = 10;

	private int thinkTtl = -1;

	@Override
	public void updateActive() {
		decrementThinkTtl();
		chasePlayer();
	}

	private void decrementThinkTtl() {
		if (thinkTtl < 0) {
			this.thinkTtl = MathUtil.nextRandomInt(MAX_THINK_TTL);
		} else {
			this.thinkTtl--;
		}
	}

	private void chasePlayer() {
		final DirectionIntent moveIntent = getMoveDirectionIntent();
		moveIntent.reset();

		if (thinkTtl == 0) {
			final Actor judo = getActor();
			final GameScene scene = (GameScene) judo.getScene();
			final Actor playerActor = scene.getPlayer().getActor();

			final int jx = judo.getX();
			final int jy = judo.getY();
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
