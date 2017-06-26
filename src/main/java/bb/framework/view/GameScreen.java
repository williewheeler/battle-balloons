package bb.framework.view;

import bb.framework.model.actor.Actor;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static bb.BBConfig.SCREEN_SIZE_PX;

/**
 * Created by willie on 6/19/17.
 */
public class GameScreen extends JPanel {
	private boolean active;
	private final List<Actor> actors = new LinkedList<>();

	public GameScreen() {
		this.active = true;
		setBackground(Color.BLACK);
	}

	@Override
	public Dimension getPreferredSize() {
		return SCREEN_SIZE_PX;
	}

	public void addActor(Actor actor) {
		actors.add(actor);
	}

	public boolean isActive() {
		return active;
	}

	public void update() {
		garbageCollectActors();
		updateActors();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		actors.forEach(actor -> actor.paint(g));
	}

	private void garbageCollectActors() {
		for (ListIterator<Actor> it = actors.listIterator(); it.hasNext(); ) {
			Actor actor = it.next();
			if (actor.getStopped()) {
				it.remove();
			}
		}
	}

	private void updateActors() {
		actors.forEach(actor -> actor.getModel().update());
	}
}
