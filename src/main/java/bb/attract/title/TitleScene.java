package bb.attract.title;

import bb.common.BBConfig;
import bb.common.actor.model.BigBalloon;
import bb.common.actor.model.Text;
import bb.common.scene.TtlScene;
import bb.framework.util.MathUtil;

import static bb.common.BBConfig.WORLD_SIZE;

/**
 * Created by willie on 7/2/17.
 */
public class TitleScene extends TtlScene {
	private static final int TTL = 15 * BBConfig.FRAMES_PER_SECOND;
	private static final int BASE_DX = 4;
	private static final double CREATE_PROBABILITY = 0.33;

	public TitleScene() {
		super(TTL);
		initScene();
	}

	@Override
	public void update() {
		super.update();

		// FIXME This is using the WORLD_SIZE, which is really the arena world size.
		// But the BigBalloon GC uses WORLD_SIZE, so we need to do the same here.
		// May want to allow different world sizes depending on the screen.
		generateBalloon(-10, 20, BASE_DX, 1);
		generateBalloon(WORLD_SIZE.width + 10, WORLD_SIZE.height, -BASE_DX, -1);
	}

	private void initScene() {

		// TODO Green
		getTexts().add(new Text(this, "GET READY FOR", 55, 60));

		// TODO Yellow
		getTexts().add(new Text(this, "PRESS [1] PLAYER OR [2] PLAYERS", 55, 190));
	}

	private void generateBalloon(int x, int yBase, int dxBase, int dRotation) {
		if (MathUtil.nextRandomDouble() < CREATE_PROBABILITY) {
			int y = generateY(yBase);
			int dx = generateDx(dxBase);
			getBigBalloons().add(new BigBalloon(this, x, y, dx, 0, 0, dRotation));
		}
	}

	private int generateY(int yBase) {
		return yBase + MathUtil.nextRandomInt(15) - 7;
	}

	private int generateDx(int dxBase) {
		return dxBase + MathUtil.nextRandomInt(5) - 2;
	}
}
