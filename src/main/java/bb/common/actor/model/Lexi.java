package bb.common.actor.model;

import bb.common.BBConfig;
import bb.common.scene.Scene;
import bb.framework.actor.ActorBrain;
import bb.framework.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by willie on 6/24/17.
 */
public class Lexi extends AbstractActor {
	public enum Substate {
		BLINKING,
		WALKING,
		WAVING
	}

	private static final Logger log = LoggerFactory.getLogger(Lexi.class);

	public static final int ENTERING_DURATION = 20;
	public static final int EXITING_DURATION = 40;

	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 3;
	private static final double BLINK_DURATION_MEAN = 2 * BBConfig.FRAMES_PER_SECOND;
	private static final double BLINK_DURATION_STDEV = BBConfig.FRAMES_PER_SECOND;
	private static final int UNBLINK_DURATION = 5;
	private static final int WAVE_DURATION = 4;

	// TODO Move entering/exiting up to AbstractActor? [WLW]
	private int enteringCountdown = ENTERING_DURATION - 1;
	private int exitingCountdown = EXITING_DURATION - 1;

	private Substate substate;
	private boolean eyesOpen = true;
	private int blinkCountdown = generateBlinkDuration();
	private boolean wavingLeft = true;
	private int waveCountdown = WAVE_DURATION;

	/**
	 * Initial substate is WALKING.
	 *
	 * @param brain
	 * @param x
	 * @param y
	 */
	public Lexi(Scene scene, ActorBrain brain, int x, int y) {
		super(scene, brain, x, y, WIDTH, HEIGHT);
		setSpeed(SPEED);
		this.substate = Substate.WALKING;
	}

	public int getEnteringCountdown() {
		return enteringCountdown;
	}

	public int getExitingCountdown() {
		return exitingCountdown;
	}

	public Substate getSubstate() {
		return substate;
	}

	public void setSubstate(Substate substate) {
		this.substate = substate;
	}

	public boolean getEyesOpen() {
		return eyesOpen;
	}

	public boolean getWavingLeft() {
		return wavingLeft;
	}

	@Override
	public void updateBodyEntering() {
		this.enteringCountdown--;
		if (enteringCountdown < 0) {
			setState(ActorState.ACTIVE);
		}
	}

	@Override
	public void updateBodyActive() {
		switch (substate) {
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

	@Override
	public void updateBodyExiting() {
		this.exitingCountdown--;
		if (exitingCountdown < 0) {
			setState(ActorState.GONE);
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

	private void doWave() {
		if (waveCountdown == 0) {
			this.wavingLeft = !wavingLeft;
			this.waveCountdown = WAVE_DURATION;
		}
		this.waveCountdown--;
	}
}
