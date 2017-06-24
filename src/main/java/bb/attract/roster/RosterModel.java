package bb.attract.roster;

import bb.framework.model.AbstractActorModel;
import bb.framework.model.GameModel;

import java.util.LinkedList;
import java.util.List;

import static bb.BBConfig.FRAMES_PER_SECOND;

/**
 * Created by willie on 6/19/17.
 */
public class RosterModel implements GameModel {

	// TODO Implement an AbstractGameModel that handles the activeCountdown automatically.
	private static final int NUM_FRAMES_ACTIVE = 20 * FRAMES_PER_SECOND;

	private final List<AbstractActorModel> actorModels = new LinkedList<>();

	private int activeCountdown = NUM_FRAMES_ACTIVE;

	public void addActorModel(AbstractActorModel actorModel) {
		actorModels.add(actorModel);
	}

	@Override
	public boolean isActive() {
		return activeCountdown > 0;
	}

	@Override
	public void update() {
		actorModels.forEach(model -> model.update());
		this.activeCountdown--;
	}
}
