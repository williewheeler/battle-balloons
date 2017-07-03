package bb.game.arena.model;

import bb.framework.actor.Actor;
import bb.framework.util.Assert;
import bb.framework.util.MathUtil;

import static bb.common.BBConfig.*;

/**
 * Created by willie on 7/2/17.
 */
public final class ActorUtil {

	private ActorUtil() {
	}

	public static void center(Actor actor) {
		Assert.notNull(actor, "actor can't be null");
		actor.setX(ARENA_INNER_WIDTH_PX / 2);
		actor.setY(ARENA_INNER_HEIGHT_PX / 2);
	}

	public static void randomizeLocation(Actor actor, Player player) {
		Assert.notNull(actor, "actor can't be null");
		Assert.notNull(player, "player can't be null");

		int xRange = ARENA_INNER_WIDTH_PX - actor.getWidth();
		int yRange = ARENA_INNER_HEIGHT_PX - actor.getHeight();

		Actor playerActor = player.getActor();

		while (true) {
			int x = MathUtil.nextRandomInt(xRange) + actor.getWidth() / 2;
			int y = MathUtil.nextRandomInt(yRange) + actor.getHeight() / 2;

			int xDiff = x - playerActor.getX();
			int yDiff = y - playerActor.getY();

			double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
			if (dist > CLEARING_RADIUS) {
				actor.setX(x);
				actor.setY(y);
				break;
			}
		}
	}
}
