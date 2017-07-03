package bb.game.arena.model;

import bb.common.actor.model.AbstractActorBrain;
import bb.common.actor.model.Judo;
import bb.framework.actor.Actor;
import bb.framework.actor.DirectionIntent;
import bb.framework.util.MathUtil;

// FIXME Would be better if this wasn't tied to either the arena or to Judo.
// I could see having this be a "PlayerTrackingBrain", and whoever has it just follows the player around.

/**
 * Created by willie on 7/2/17.
 */
public class ArenaJudoBrain extends AbstractActorBrain {
	private static final int MAX_THINK_PERIOD = 10;

	private int thinkTtl = -1;

	@Override
	public void update() {
		decrementThinkTtl();
		chasePlayer();
	}

	private void decrementThinkTtl() {
		if (thinkTtl < 0) {
			this.thinkTtl = MathUtil.nextRandomInt(MAX_THINK_PERIOD);
		} else {
			this.thinkTtl--;
		}
	}

	private void chasePlayer() {
		final DirectionIntent moveIntent = getMoveDirectionIntent();
		moveIntent.reset();

		if (thinkTtl == 0) {
			final Judo judo = (Judo) getActor();
			final ArenaScene scene = (ArenaScene) judo.getScene();
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
