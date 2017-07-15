package bb.game.actor;

import retroge.actor.Actor;
import retroge.actor.DirectionIntent;
import retroge.actor.Player;
import retroge.actor.brain.ActorBrain;
import retroge.util.Assert;
import retroge.util.MathUtil;

import static bb.common.BBConfig.WORLD_SIZE;

/**
 * Created by willie on 7/2/17.
 */
public final class ActorUtil {
	
	/**
	 * Clearing around the player when enemies are initially placed.
	 */
	private static final int CLEARING_RADIUS = 50;

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

	// FIXME This can result in an actor who just stands still.
	public static void randomizeDirection(Actor actor) {
		Assert.notNull(actor, "actor can't be null");
		ActorBrain brain = actor.getBrain();
		if (brain != null) {
			DirectionIntent moveIntent = brain.getMoveDirectionIntent();
			moveIntent.reset();
			
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
}
