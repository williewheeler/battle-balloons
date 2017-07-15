package bb.common.actor.view;

import retroge.actor.Direction;
import retroge.util.Assert;

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
}
