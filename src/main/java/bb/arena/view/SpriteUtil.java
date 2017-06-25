package bb.arena.view;

import bb.common.model.Direction;
import bb.arena.model.Entity;

import java.awt.image.BufferedImage;

/**
 * Created by willie on 6/9/17.
 */
public class SpriteUtil {

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
