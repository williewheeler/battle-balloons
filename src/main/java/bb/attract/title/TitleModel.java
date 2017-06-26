package bb.attract.title;

import bb.framework.model.GameModel;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static bb.BBConfig.SCREEN_HEIGHT_PX;
import static bb.BBConfig.SCREEN_WIDTH_PX;

/**
 * Created by willie on 6/17/17.
 */
public class TitleModel implements GameModel {
	private static final double CREATE_PROBABILITY = 0.33;
	private static final int BASE_DX = 4;
	private static final Random RANDOM = new Random();

	private final List<BigBalloon> balloons = new LinkedList<>();

	public List<BigBalloon> getBalloons() {
		return balloons;
	}

	@Override
	public void update() {
		garbageCollect();
		balloons.forEach(balloon -> balloon.update());
		generateBalloon(0, 20, BASE_DX, 1);
		generateBalloon(SCREEN_WIDTH_PX, SCREEN_HEIGHT_PX - 20, -BASE_DX, -1);
	}

	private void garbageCollect() {
		for(ListIterator<BigBalloon> it = balloons.listIterator(); it.hasNext();) {
			BigBalloon balloon = it.next();
			int x = balloon.getX();
			int y = balloon.getY();
			if (x < 0 || x > SCREEN_WIDTH_PX || y < 0 || y > SCREEN_HEIGHT_PX) {
				it.remove();
			}
		}
	}

	private void generateBalloon(int x, int yBase, int dxBase, int dRotation) {
		if (RANDOM.nextDouble() < CREATE_PROBABILITY) {
			int y = generateY(yBase);
			int dx = generateDx(dxBase);
			BigBalloon.Color color = generateColor();
			balloons.add(new BigBalloon(x, y, dx, 0, 0, dRotation, color));
		}
	}

	private int generateY(int yBase) {
		return yBase + RANDOM.nextInt(15) - 7;
	}

	private int generateDx(int dxBase) {
		return dxBase + RANDOM.nextInt(5) - 2;
	}

	private BigBalloon.Color generateColor() {
		BigBalloon.Color[] colors = BigBalloon.Color.values();
		int index = RANDOM.nextInt(colors.length);
		return colors[index];
	}
}
