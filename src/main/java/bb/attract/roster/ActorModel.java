package bb.attract.roster;

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
	private ActorBehavior behavior;
	private boolean eyesOpen = true;
	private int blinkCountdown = generateBlinkDuration();
	private boolean wavingLeft = true;
	private int waveCountdown = WAVE_DURATION;

	public ActorModel(int x, int y) {
		this.x = x;
		this.y = y;
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

	public ActorBehavior getBehavior() {
		return behavior;
	}

	public void setBehavior(ActorBehavior behavior) {
		this.behavior = behavior;
	}

	public boolean getEyesOpen() {
		return eyesOpen;
	}

	public boolean getWavingLeft() {
		return wavingLeft;
	}

	public void update() {
		switch (behavior) {
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
