package bb.common.model;

import bb.framework.model.actor.AbstractActorModel;
import bb.framework.model.actor.ActorUtil;
import bb.framework.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by willie on 6/24/17.
 */
public class LexiModel extends AbstractActorModel {
	private static final Logger log = LoggerFactory.getLogger(LexiModel.class);

	private static final int SPEED = 3;
	private static final double BLINK_DURATION_MEAN = 66.0;
	private static final double BLINK_DURATION_STDEV = 33.0;
	private static final int UNBLINK_DURATION = 5;
	private static final int WAVE_DURATION = 4;

	private int walkCounter = 0;

	private boolean eyesOpen = true;
	private int blinkCountdown = generateBlinkDuration();

	private boolean wavingLeft = true;
	private int waveCountdown = WAVE_DURATION;

	public LexiModel(LexiBrain brain, int x, int y) {
		super(brain, x, y, SPEED);
	}

	public LexiBrain.State getState() {
		// TODO Generics?
		LexiBrain brain = (LexiBrain) getBrain();
		return brain.getState();
	}

	public int getWalkCounter() { return walkCounter; }

	public boolean getEyesOpen() {
		return eyesOpen;
	}

	public boolean getWavingLeft() {
		return wavingLeft;
	}

	@Override
	public void doUpdate() {
		// TODO Consider moving these into an Action abstraction.
		// The idea being that we simply grab the next action from the brain
		// (where it exists as an intent) and execute it.
		switch (getState()) {
			case BLINKING:
				doBlink();
				break;
			case WALKING:
				doWalk();
				break;
			case WAVING:
				doWave();
				break;
		}
	}

	private void doBlink() {
		if (eyesOpen) {
			if (blinkCountdown == 0) {
				this.eyesOpen = false;
				this.blinkCountdown = UNBLINK_DURATION;
			}
		} else {
			if (blinkCountdown == 0) {
				this.eyesOpen = true;
				this.blinkCountdown = generateBlinkDuration();
			}
		}
		this.blinkCountdown = Math.max(0, blinkCountdown - 1);
	}

	private int generateBlinkDuration() {
		return (int) MathUtil.nextRandomGaussian(BLINK_DURATION_MEAN, BLINK_DURATION_STDEV);
	}

	private void doWalk() {

		// TODO Generics?
		LexiBrain brain = (LexiBrain) getBrain();
		int speed = getSpeed();

		int deltaX = 0;
		int deltaY = 0;

		if (brain.moveUp()) {
			deltaY -= speed;
		}
		if (brain.moveDown()) {
			deltaY += speed;
		}
		if (brain.moveLeft()) {
			deltaX -= speed;
		}
		if (brain.moveRight()) {
			deltaX += speed;
		}

		Direction direction = ActorUtil.calculateDirection(deltaX, deltaY);

		changeX(deltaX);
		changeY(deltaY);

		if (direction != null) {
			setDirection(direction);
			this.walkCounter = (walkCounter + 1) % 4;
		}
	}

	private void doWave() {
		if (waveCountdown == 0) {
			this.wavingLeft = !wavingLeft;
			this.waveCountdown = WAVE_DURATION;
		}
		this.waveCountdown--;
	}
}
