package com.williewheeler.battleballoons.game.world.entity;

import io.halfling.GameConfig;
import io.halfling.world.entity.model.Actor;
import io.halfling.world.entity.model.DirectionIntent;
import io.halfling.world.entity.model.Player;
import io.halfling.world.entity.brain.ActorBrain;
import io.halfling.core.Assert;
import io.halfling.core.RandomUtil;

/**
 * Created by willie on 7/2/17.
 */
public final class EntityUtil {
	
	/**
	 * Clearing around the player when enemies are initially placed.
	 */
	private static final int CLEARING_RADIUS = 50;

	private EntityUtil() {
	}

	public static void randomizeLocation(Actor actor, Player player) {
		Assert.notNull(actor, "actor can't be null");
		Assert.notNull(player, "player can't be null");

		int xRange = GameConfig.WORLD_SIZE.width - actor.getWidth();
		int yRange = GameConfig.WORLD_SIZE.height - actor.getHeight();

		Actor playerActor = player.getActor();

		while (true) {
			int x = RandomUtil.nextRandomInt(xRange) + actor.getWidth() / 2;
			int y = RandomUtil.nextRandomInt(yRange) + actor.getHeight() / 2;

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

			int upDown = 0;
			int leftRight = 0;
			while (upDown == 0 && leftRight == 0) {
				upDown = RandomUtil.nextRandomInt(3) - 1;
				leftRight = RandomUtil.nextRandomInt(3) - 1;
			}

			if (upDown != 0) {
				moveUpOrDown(moveIntent, upDown == -1 ? 0 : 1);
			}
			if (leftRight != 0) {
				moveLeftOrRight(moveIntent, leftRight == -1 ? 0 : 1);
			}
		}
	}
	
	public static void randomizeDirectionNoDiagonals(Actor actor) {
		Assert.notNull(actor, "actor can't be null");
		ActorBrain brain = actor.getBrain();
		if (brain != null) {
			DirectionIntent moveIntent = brain.getMoveDirectionIntent();
			moveIntent.reset();
			int dir = RandomUtil.nextRandomInt(2);
			switch (RandomUtil.nextRandomInt(2)) {
				case 0:
					moveUpOrDown(moveIntent, dir);
					break;
				case 1:
					moveLeftOrRight(moveIntent, dir);
					break;
			}
		}
	}
	
	private static void moveUpOrDown(DirectionIntent moveIntent, int upDown) {
		switch (upDown) {
			case 0:
				moveIntent.up = true;
				break;
			case 1:
				moveIntent.down = true;
				break;
		}
	}
	
	private static void moveLeftOrRight(DirectionIntent moveIntent, int leftRight) {
		switch (leftRight) {
			case 0:
				moveIntent.left = true;
				break;
			case 1:
				moveIntent.right = true;
				break;
		}
	}
}
