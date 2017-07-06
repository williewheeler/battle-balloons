package bb.game.arena.actor;

import bb.framework.actor.Actor;
import bb.framework.actor.DirectionIntent;
import bb.framework.actor.Player;
import bb.framework.util.Assert;
import bb.framework.util.MathUtil;

import static bb.common.BBConfig.CLEARING_RADIUS;
import static bb.common.BBConfig.WORLD_SIZE;

/**
 * Created by willie on 7/2/17.
 */
public final class ActorUtil {

	private ActorUtil() {
	}

	public static void randomizeLocation(Actor actor, Player player) {
		Assert.notNull(actor, "actor can't be null");
		Assert.notNull(player, "player can't be null");

		int xRange = WORLD_SIZE.width - actor.getWidth();
		int yRange = WORLD_SIZE.height - actor.getHeight();

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

	// This can result in an actor who just stands still. That's fine for now. [WLW]
	public static void randomizeDirection(Actor actor) {
		Assert.notNull(actor, "actor can't be null");
		DirectionIntent moveIntent = actor.getBrain().getMoveDirectionIntent();

		int upDown = MathUtil.nextRandomInt(3) - 1;
		int leftRight = MathUtil.nextRandomInt(3) - 1;

		switch (upDown) {
			case -1:
				moveIntent.up = true;
				break;
			case 1:
				moveIntent.down = true;
				break;
		}

		switch (leftRight) {
			case -1:
				moveIntent.left = true;
				break;
			case 1:
				moveIntent.right = true;
				break;
		}
	}
}
