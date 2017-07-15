package bb.game.level;

import retroge.util.Assert;

/**
 * Created by willie on 7/4/17.
 */
public final class Levels {
	private Level[] levels = new Level[] {
			new Level(15, 5, 1, 0),
			new Level(17, 15, 1, 5),
			new Level(22, 25, 2, 6),
			new Level(34, 25, 2, 7),
			new Level(20, 20, 15, 0),
			new Level(32, 25, 3, 7)
	};

	public Level getLevel(int number) {
		Assert.isStrictlyPositive(number, "number must be strictly positive");
		int index = (number - 1) % levels.length;
		return levels[index];
	}
}
