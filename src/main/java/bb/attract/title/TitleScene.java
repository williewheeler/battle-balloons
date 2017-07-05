package bb.attract.title;

import bb.common.actor.model.BigBalloon;
import bb.common.actor.model.Text;
import bb.common.scene.ScriptScene;
import bb.framework.util.MathUtil;

import static bb.common.BBConfig.WORLD_SIZE;

/**
 * Created by willie on 7/2/17.
 */
public class TitleScene extends ScriptScene {

	// FIXME These use WORLD_SIZE, which is really the arena world size.
	// But the BigBalloon GC uses WORLD_SIZE, so we need to do the same here.
	// May want to allow different world sizes depending on the scene.
	private static final int BALLOON_X0_TOP = -10;
	private static final int BALLOON_X0_BOT = WORLD_SIZE.width + 10;
	private static final int BALLOON_Y_BASE_TOP = 20;
	private static final int BALLOON_Y_BASE_BOT = WORLD_SIZE.height;
	private static final int BALLOON_Y_RANGE = 15;
	private static final int BALLOON_DX_BASE = 4;
	private static final int BALLOON_DX_RANGE = 5;
	private static final int BALLOON_DROT = 1;
	private static final double CREATE_PROBABILITY = 0.33;

	private Text getReadyText;
	private Text playerText;

	public TitleScene() {
		initActors();
	}

	@Override
	public void doScript(int counter) {
		generateBalloon(BALLOON_X0_TOP, BALLOON_Y_BASE_TOP, BALLOON_DX_BASE, BALLOON_DROT);
		generateBalloon(BALLOON_X0_BOT, BALLOON_Y_BASE_BOT, -BALLOON_DX_BASE, -BALLOON_DROT);

		if (counter == 0) {
			getTexts().add(getReadyText);
			getTexts().add(playerText);
		} else if (counter >= 450) {
			setActive(false);
		}
	}

	private void initActors() {

		// TODO Support color specification
		this.getReadyText = new Text("GET READY FOR", 55, 60);
		getReadyText.setScene(this);

		this.playerText = new Text("PRESS [1] PLAYER OR [2] PLAYERS", 55, 190);
		playerText.setScene(this);
	}

	private void generateBalloon(int x0, int yBase, int dxBase, int drot) {
		if (MathUtil.nextRandomDouble() < CREATE_PROBABILITY) {
			int y = generateY(yBase);
			int dx = generateDx(dxBase);

			BigBalloon bigBalloon = new BigBalloon(x0, y, dx, 0, 0, drot);
			bigBalloon.setScene(this);
			getBigBalloons().add(bigBalloon);
		}
	}

	private int generateY(int yBase) {
		int yOffset = MathUtil.nextRandomInt(BALLOON_Y_RANGE) - BALLOON_Y_RANGE / 2;
		return yBase + yOffset;
	}

	private int generateDx(int dxBase) {
		int dxOffset = MathUtil.nextRandomInt(BALLOON_DX_RANGE) - BALLOON_DX_RANGE / 2;
		return dxBase + dxOffset;
	}
}
