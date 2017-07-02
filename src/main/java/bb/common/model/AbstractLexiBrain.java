package bb.common.model;

import bb.framework.model.ActorBrain;

/**
 * Created by willie on 6/25/17.
 */
public abstract class AbstractLexiBrain implements ActorBrain {
	public enum State {
		BLINKING,
		WALKING,
		WAVING
	}

	private State state;

	public AbstractLexiBrain() {
		this.state = State.BLINKING;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
