package com.williewheeler.bb.common.actor.model;

import com.williewheeler.bb.common.BBConfig;
import com.williewheeler.bb.common.event.GameEvents;
import com.williewheeler.bb.common.scene.BBScene;
import com.williewheeler.retroge.actor.AbstractActor;
import com.williewheeler.retroge.actor.ActorLifecycleState;
import com.williewheeler.retroge.actor.DirectionIntent;
import com.williewheeler.retroge.actor.brain.ActorBrain;
import com.williewheeler.retroge.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by willie on 6/24/17.
 */
public class Lexi extends AbstractActor {
	public enum Substate {
		BLINKING,
		BATTLING,
		WAVING
	}
	
	private static final Logger log = LoggerFactory.getLogger(Lexi.class);
	
	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 3;

	public static final int ENTER_TTL = 20;
	public static final int EXIT_TTL = 40;

	private static final int WALK_EVENT_TTL = 5;
	private static final double BLINK_DURATION_MEAN = 2 * BBConfig.FRAMES_PER_SECOND;
	private static final double BLINK_DURATION_STDEV = BBConfig.FRAMES_PER_SECOND;
	private static final int UNBLINK_DURATION = 5;
	private static final int WAVE_DURATION = 4;

	// TODO Move entering/exiting up to AbstractActor? [WLW]
	private int enterTtl = ENTER_TTL;
	private int exitTtl = EXIT_TTL;

	private Substate substate;
	private int walkEventTtl = WALK_EVENT_TTL;
	private boolean eyesOpen = true;
	private int blinkCountdown = generateBlinkDuration();
	private boolean wavingLeft = true;
	private int waveCountdown = WAVE_DURATION;

	/**
	 * Initial substate is BATTLING.
	 *
	 * @param brain
	 * @param x
	 * @param y
	 */
	public Lexi(ActorBrain brain, int x, int y) {
		super(brain, x, y, WIDTH, HEIGHT);
		setSpeed(SPEED);
		this.substate = Substate.BATTLING;
	}

	public int getEnterTtl() {
		return enterTtl;
	}

	public int getExitTtl() {
		return exitTtl;
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
		this.enterTtl--;
		if (enterTtl <= 0) {
			this.enterTtl = ENTER_TTL;
			setState(ActorLifecycleState.ACTIVE);
		}
	}

	@Override
	public void updateBodyActive() {
		switch (substate) {
			case BATTLING:
				doMove();
				doFire();
				break;
			case BLINKING:
				doBlink();
				break;
			case WAVING:
				doWave();
				break;
		}
	}

	@Override
	public void updateBodyExiting() {
		BBScene bbScene = (BBScene) getScene();
		this.exitTtl--;
		if (exitTtl <= 0) {
			this.exitTtl = EXIT_TTL;
			setState(ActorLifecycleState.GONE);
			bbScene.fireGameEvent(GameEvents.PLAYER_GONE);
		}
	}

	@Override
	protected boolean doMove() {
		BBScene bbScene = (BBScene) getScene();
		boolean walked = super.doMove();
		if (walked) {
			this.walkEventTtl--;
			if (walkEventTtl == 0) {
				bbScene.fireGameEvent(GameEvents.PLAYER_WALKED);
				this.walkEventTtl = WALK_EVENT_TTL;
			}
			return true;
		} else {
			return false;
		}
	}

	protected boolean doFire() {
		BBScene bbScene = (BBScene) getScene();
		
		boolean fired = false;
		if (rechargeTtl > 0) {
			this.rechargeTtl--;
			return false;
		} else {
			final ActorBrain brain = getBrain();
			final DirectionIntent fireIntent = brain.getFireDirectionIntent();
			
			int dx = 0;
			int dy = 0;
			
			if (fireIntent.up) {
				dy -= Balloon.SPEED;
			}
			if (fireIntent.down) {
				dy += Balloon.SPEED;
			}
			if (fireIntent.left) {
				dx -= Balloon.SPEED;
			}
			if (fireIntent.right) {
				dx += Balloon.SPEED;
			}
			
			if (dx != 0 || dy != 0) {
				Balloon balloon = new Balloon(getX(), getY(), dx, dy);
				bbScene.getBalloons().add(balloon);
				this.rechargeTtl = RECHARGE_TTL;
				fired = true;
			} else {
				fired = false;
			}
		}

		if (fired) {
			bbScene.fireGameEvent(GameEvents.PLAYER_THREW_BALLOON);
		}
		
		return fired;
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
