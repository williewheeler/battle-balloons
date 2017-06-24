package bb.attract.roster;

import bb.actor.Actor;
import bb.actor.ActorModel;
import bb.core.view.GameScreen;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by willie on 6/19/17.
 */
public class RosterScreen extends GameScreen {
	private final RosterModel rosterModel = new RosterModel();
	private final List<Actor> actors = new LinkedList<>();

	public RosterModel getRosterModel() {
		return rosterModel;
	}

	public void addActor(Actor actor) {
		actors.add(actor);
		rosterModel.addActorModel(actor.getModel());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		actors.forEach(actor -> actor.paint(g));
	}
}
