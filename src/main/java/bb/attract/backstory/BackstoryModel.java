package bb.attract.backstory;

import bb.framework.model.AbstractAttractModel;

import static bb.BBConfig.FRAMES_PER_SECOND;

/**
 * Created by willie on 6/18/17.
 */
public class BackstoryModel extends AbstractAttractModel {
	private static final String BACKSTORY =
			"BATTLE BALLOONS\n\n" +
			"IN THE YEAR 2112 THERE LIVES A GROUP OF FRIENDS:\n" +
			"THE BEAST SQUAD.\n\n" +
			"AT SCHOOL,\n" +
			"A WATER BALLOON FIGHT GETS OUT OF HAND.\n" +
			"THE BEAST SQUAD BREAKS TABLES AND GETS SOAKED.\n" +
			"THEY CAN'T GET TOWELS.\n" +
			"THE ONLY DRY PERSON IS A GIRL NAMED LEXI.\n\n" +
			"CAN YOU SAVE HER FROM GETTING...\n" +
			"GROUNDED?";

	public BackstoryModel() {
		super(20 * FRAMES_PER_SECOND);
	}

	public String getBackstory() {
		return BACKSTORY;
	}
}
