package bb.attract.roster;

import bb.core.model.GameModel;

import static bb.BBConfig.FRAMES_PER_SECOND;

/**
 * Created by willie on 6/19/17.
 */
public class RosterModel implements GameModel {

	// TODO Implement an AbstractGameModel that handles the activeCountdown automatically.
	private static final int NUM_FRAMES_ACTIVE = 20 * FRAMES_PER_SECOND;

	private ActorModel lexiModel;
	private int activeCountdown = NUM_FRAMES_ACTIVE;

	public RosterModel() {
		this.lexiModel = new ActorModel(80, 150);
//		lexiModel.setBehavior(ActorBehavior.BLINKING);
		lexiModel.setBehavior(ActorBehavior.WAVING);
	}

	public ActorModel getLexiModel() {
		return lexiModel;
	}

	@Override
	public boolean isActive() {
		return activeCountdown > 0;
	}

	@Override
	public void update() {
		lexiModel.update();
		this.activeCountdown--;
	}
}
