package bb.common.model;

import bb.framework.model.actor.AbstractActorModel;
import bb.framework.util.MathUtil;

/**
 * Created by willie on 6/24/17.
 */
public class LexiModel extends AbstractActorModel {
	public enum State {
		BLINKING,
		WAVING
	}

	private static final double BLINK_DURATION_MEAN = 66.0;
	private static final double BLINK_DURATION_STDEV = 33.0;
	private static final int UNBLINK_DURATION = 5;
	private static final int WAVE_DURATION = 4;

	private State state;

	private boolean eyesOpen = true;
	private int blinkCountdown = generateBlinkDuration();

	private boolean wavingLeft = true;
	private int waveCountdown = WAVE_DURATION;

	public LexiModel(int x, int y) {
		super(x, y);
		this.state = State.BLINKING;
	}

	public State getState() {
		return state;
	}

	public boolean getEyesOpen() {
		return eyesOpen;
	}

	public boolean getWavingLeft() {
		return wavingLeft;
	}

	@Override
	public void update() {
		// TODO Replace this with an explicit state machine.
		switch (state) {
			case BLINKING:
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
				break;
			case WAVING:
				if (waveCountdown == 0) {
					this.wavingLeft = !wavingLeft;
					this.waveCountdown = WAVE_DURATION;
				}
				this.waveCountdown--;
				break;
		}
	}

	private int generateBlinkDuration() {
		return (int) (BLINK_DURATION_MEAN + BLINK_DURATION_STDEV * MathUtil.nextRandomGaussian());
	}
}
