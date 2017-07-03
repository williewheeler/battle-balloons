package bb.attract.backstory;

import bb.common.BBConfig;
import bb.common.actor.model.Text;
import bb.common.scene.TtlScene;

/**
 * Created by willie on 7/2/17.
 */
public class BackstoryScene extends TtlScene {
	private static final int TTL = 15 * BBConfig.FRAMES_PER_SECOND;

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

	public BackstoryScene() {
		super(TTL);
		initScene();
	}

	private void initScene() {
		getTexts().add(new Text(this, BACKSTORY, 20, 40));
	}
}
