package com.williewheeler.bb.game.actor;

import com.williewheeler.retroge.GameConfig;
import com.williewheeler.retroge.actor.Actor;
import com.williewheeler.retroge.actor.DirectionIntent;
import com.williewheeler.retroge.actor.Player;
import com.williewheeler.retroge.actor.brain.ActorBrain;
import com.williewheeler.retroge.util.Assert;
import com.williewheeler.retroge.util.MathUtil;

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

		int xRange = GameConfig.WORLD_SIZE.width - actor.getWidth();
		int yRange = GameConfig.WORLD_SIZE.height - actor.getHeight();

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
	
	public static void randomizeDirectionNoDiagonals(Actor actor) {
		Assert.notNull(actor, "actor can't be null");
		ActorBrain brain = actor.getBrain();
		if (brain != null) {
			DirectionIntent moveIntent = brain.getMoveDirectionIntent();
			moveIntent.reset();
			
			int axis = MathUtil.nextRandomInt(2);
			int dir = MathUtil.nextRandomInt(2);
			
			if (axis == 0) {
				if (dir == 0) {
					moveIntent.up = true;
				} else {
					moveIntent.down = true;
				}
			} else {
				if (dir == 0) {
					moveIntent.left = true;
				} else {
					moveIntent.right = true;
				}
			}
		}
	}
}
