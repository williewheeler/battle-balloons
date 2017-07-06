package bb.game.arena.level;

import bb.framework.util.Assert;

/**
 * Created by willie on 7/4/17.
 */
public final class Levels {
	private Level[] levels = new Level[] {
			new Level(5, 15, 0),
			new Level(15, 17, 5),
			new Level(25, 22, 6),
			new Level(25, 34, 7),
			new Level(20, 20, 0),
			new Level(25, 32, 7)
	};

	public Level getLevel(int number) {
		Assert.isStrictlyPositive(number, "number must be strictly positive");
		int index = (number - 1) % levels.length;
		return levels[index];
	}
}
