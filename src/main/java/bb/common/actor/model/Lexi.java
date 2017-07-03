package bb.common.actor.model;

import bb.common.BBConfig;
import bb.framework.actor.AbstractActor;
import bb.framework.actor.ActorBrain;
import bb.framework.actor.ActorUtil;
import bb.framework.actor.Direction;
import bb.framework.actor.DirectionIntent;
import bb.framework.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by willie on 6/24/17.
 */
public class Lexi extends AbstractActor {
	public enum State {
		BLINKING,
		WALKING,
		WAVING
	}

	private static final Logger log = LoggerFactory.getLogger(Lexi.class);

	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 3;
	private static final double BLINK_DURATION_MEAN = 2 * BBConfig.FRAMES_PER_SECOND;
	private static final double BLINK_DURATION_STDEV = BBConfig.FRAMES_PER_SECOND;
	private static final int UNBLINK_DURATION = 5;
	private static final int WAVE_DURATION = 4;

	private State state;
	private int walkCounter = 0;

	private boolean eyesOpen = true;
	private int blinkCountdown = generateBlinkDuration();

	private boolean wavingLeft = true;
	private int waveCountdown = WAVE_DURATION;

	/**
	 * Initial state is WALKING.
	 *
	 * @param brain
	 * @param x
	 * @param y
	 */
	public Lexi(ActorBrain brain, int x, int y) {
		super(brain, x, y, WIDTH, HEIGHT);
		setSpeed(SPEED);
		this.state = State.WALKING;
		brain.setActor(this);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getWalkCounter() { return walkCounter; }

	public boolean getEyesOpen() {
		return eyesOpen;
	}

	public boolean getWavingLeft() {
		return wavingLeft;
	}

	@Override
	public void updateBody() {
		switch (state) {
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
		int duration = (int) MathUtil.nextRandomGaussian(BLINK_DURATION_MEAN, BLINK_DURATION_STDEV);
		return Math.max(0, duration);
	}

	private void doWalk() {
		final DirectionIntent moveIntent = getBrain().getMoveDirectionIntent();
		final int speed = getSpeed();

		int deltaX = 0;
		int deltaY = 0;

		if (moveIntent.up) {
			deltaY -= speed;
		}
		if (moveIntent.down) {
			deltaY += speed;
		}
		if (moveIntent.left) {
			deltaX -= speed;
		}
		if (moveIntent.right) {
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
