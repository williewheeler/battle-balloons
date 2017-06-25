package bb.attract.roster;

import bb.framework.model.actor.AbstractActorModel;
import bb.framework.model.AbstractAttractModel;

import java.util.LinkedList;
import java.util.List;

import static bb.BBConfig.FRAMES_PER_SECOND;

/**
 * Created by willie on 6/19/17.
 */
public class RosterModel extends AbstractAttractModel {
	private final List<AbstractActorModel> actorModels = new LinkedList<>();

	public RosterModel() {
		super(20 * FRAMES_PER_SECOND);
	}

	public void addActorModel(AbstractActorModel actorModel) {
		actorModels.add(actorModel);
	}

	@Override
	public void update() {
		super.update();
		actorModels.forEach(model -> model.update());
	}
}
