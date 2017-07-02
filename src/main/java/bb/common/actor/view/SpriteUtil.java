package bb.common.actor.view;

import bb.framework.actor.Direction;
import bb.framework.util.Assert;
import bb.game.arena.model.Entity;

import java.awt.image.BufferedImage;

/**
 * Created by willie on 6/9/17.
 */
public final class SpriteUtil {

	private SpriteUtil() {
	}

	public static int getWalkingSpriteIndex(Direction direction, int walkCounter) {
		Assert.notNull(direction, "direction can't be null");

		int baseIndex = 0;
		switch (direction) {
			case UP:
				baseIndex = 0;
				break;
			case DOWN:
				baseIndex = 4;
				break;
			case UP_LEFT:
			case LEFT:
			case DOWN_LEFT:
				baseIndex = 8;
				break;
			case UP_RIGHT:
			case RIGHT:
			case DOWN_RIGHT:
				baseIndex = 12;
				break;
			default:
				baseIndex = 4;
				break;
		}

		return baseIndex + walkCounter;
	}

	@Deprecated
	public static BufferedImage getCurrentSprite(Entity entity, BufferedImage[] sprites) {
		Direction direction = entity.getDirection();

		int spriteBaseIndex = 0;
		switch (direction) {
			case LEFT:
			case UP_LEFT:
			case DOWN_LEFT:
				spriteBaseIndex = 8;
				break;
			case RIGHT:
			case UP_RIGHT:
			case DOWN_RIGHT:
				spriteBaseIndex = 12;
				break;
			case UP:
				spriteBaseIndex = 0;
				break;
			case DOWN:
				spriteBaseIndex = 4;
				break;
		}

		int spriteIndex = spriteBaseIndex + entity.getAnimationCounter();
		return sprites[spriteIndex];
	}
}
