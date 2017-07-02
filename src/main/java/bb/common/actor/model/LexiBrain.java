package bb.common.actor.model;

import bb.framework.actor.ActorBrain;

/**
 * Created by willie on 6/25/17.
 */
public abstract class LexiBrain implements ActorBrain {
	public enum State {
		BLINKING,
		WALKING,
		WAVING
	}

	private State state;

	public LexiBrain() {
		this.state = State.BLINKING;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
