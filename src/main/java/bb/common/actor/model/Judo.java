package bb.common.actor.model;

import bb.common.scene.Scene;
import bb.framework.actor.ActorBrain;
import bb.framework.util.Countdown;

/**
 * Created by willie on 7/2/17.
 */
public class Judo extends AbstractActor {
	public static final int ENTERING_DURATION = 20;
	public static final int EXITING_DURATION = 5;

	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 2;

	// TODO Move entering/exiting up to AbstractActor? [WLW]
	private Countdown enteringCountdown = new Countdown(ENTERING_DURATION - 1);
	private Countdown exitingCountdown = new Countdown(EXITING_DURATION - 1);

	public Judo(Scene scene, ActorBrain brain, int x, int y) {
		super(scene, brain, x, y, WIDTH, HEIGHT);
		setSpeed(SPEED);
	}

	public int getEnteringCountdown() {
		return enteringCountdown.getCount();
	}

	public int getExitingCountdown() {
		return exitingCountdown.getCount();
	}

	@Override
	public void updateBodyEntering() {
		enteringCountdown.decrement();
		if (!enteringCountdown.isActive()) {
			setState(ActorState.ACTIVE);
		}
	}

	@Override
	public void updateBodyActive() {
		doWalk();
	}

	@Override
	public void updateBodyExiting() {
		exitingCountdown.decrement();
		if (!exitingCountdown.isActive()) {
			setState(ActorState.GONE);
		}
	}
}
