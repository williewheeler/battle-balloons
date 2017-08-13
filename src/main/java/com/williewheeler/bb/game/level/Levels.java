package com.williewheeler.bb.game.level;

import com.williewheeler.retroge.util.Assert;

// http://seanriddle.com/robowaves.html

/**
 * Created by willie on 7/4/17.
 */
public final class Levels {
	private Level[] levels = new Level[] {
			new Level(15, 5, 1, 1, 0, 0, 0, 0, 0),     // level 1
			new Level(17, 15, 1, 1, 1, 5, 0, 1, 0),    // level 2
			new Level(22, 25, 2, 2, 2, 6, 0, 3, 0),    // level 3
			new Level(34, 25, 2, 2, 2, 7, 0, 4, 0),    // level 4
			new Level(20, 20, 15, 0, 1, 0, 15, 1, 0),  // level 5
			new Level(32, 25, 3, 3, 3, 7, 0, 4, 0),    // level 6
			new Level(0, 0, 4, 4, 4, 12, 0, 0, 10),    // level 7
			new Level(35, 25, 3, 3, 3, 8, 0, 5, 0),    // level 8
			new Level(60, 0, 3, 3, 3, 4, 0, 5, 0),     // level 9
			new Level(25, 20, 0, 22, 0, 0, 20, 1, 0),  // level 10
			new Level(35, 25, 3, 3, 3, 8, 0, 5, 0),    // level 11
			new Level(0, 0, 3, 3, 3, 13, 0, 0, 12),    // level 12
			new Level(35, 25, 3, 3, 3, 8, 0, 5, 0),    // level 13
			new Level(27, 5, 5, 5, 5, 20, 0, 2, 0),    // level 14
			new Level(25, 20, 0, 0, 22, 2, 20, 1, 0),  // level 15
			new Level(35, 25, 3, 3, 3, 3, 0, 5, 0),    // level 16
			new Level(0, 0, 3, 3, 3, 14, 0, 0, 12),    // level 17
			new Level(35, 25, 3, 3, 3, 8, 0, 5, 0),    // level 18
			new Level(70, 0, 3, 3, 3, 3, 0, 5, 0),     // level 19
			new Level(25, 20, 8, 8, 8, 2, 20, 2, 0)    // level 20
	};

	public Level getLevel(int number) {
		Assert.isStrictlyPositive(number, "number must be strictly positive");
		int index = (number - 1) % levels.length;
		return levels[index];
	}
}
