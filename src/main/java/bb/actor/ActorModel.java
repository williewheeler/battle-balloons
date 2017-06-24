package bb.actor;

import java.util.Random;

/**
 * Created by willie on 6/19/17.
 */
public class ActorModel {
	private static final double BLINK_DURATION_MEAN = 66.0;
	private static final double BLINK_DURATION_STDEV = 33.0;
	private static final int UNBLINK_DURATION = 5;
	private static final int WAVE_DURATION = 4;

	private static final Random RANDOM = new Random();

	private int x;
	private int y;
	private ActorModelState state;
	private boolean eyesOpen = true;
	private int blinkCountdown = generateBlinkDuration();
	private boolean wavingLeft = true;
	private int waveCountdown = WAVE_DURATION;

	public ActorModel(int x, int y) {
		this.x = x;
		this.y = y;
		this.state = ActorModelState.BLINKING;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ActorModelState getState() {
		return state;
	}

	public void setState(ActorModelState state) {
		this.state = state;
	}

	public boolean getEyesOpen() {
		return eyesOpen;
	}

	public boolean getWavingLeft() {
		return wavingLeft;
	}

	// TODO Replace this with an explicit state machine.
	public void update() {
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
		return (int) (BLINK_DURATION_MEAN + BLINK_DURATION_STDEV * RANDOM.nextGaussian());
	}
}
