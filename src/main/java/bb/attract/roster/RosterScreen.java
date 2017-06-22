package bb.attract.roster;

import bb.core.view.GameScreen;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;

/**
 * Created by willie on 6/19/17.
 */
public class RosterScreen extends GameScreen {
	private static final Random RANDOM = new Random();

	private RosterModel rosterModel;
	private List<Actor> actors;

	public RosterScreen(RosterModel rosterModel, List<Actor> actors) {
		this.rosterModel = rosterModel;
		this.actors = actors;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		actors.forEach(actor -> actor.paint(g));
	}
}
