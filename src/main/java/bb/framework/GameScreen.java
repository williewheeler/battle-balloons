package bb.framework;

import bb.BBConfig;
import bb.BBContext;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.model.Actor;
import bb.framework.util.Assert;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static bb.BBConfig.SCREEN_SIZE_PX;

/**
 * Created by willie on 6/19/17.
 */
public abstract class GameScreen extends JPanel {
	private GameScreen me;
	private BBContext context;
	private GameListener gameListener;
	private KeyListener keyListener;
	private ActionListener tickListener;
	private Timer timer;

	private final List<Actor> actors = new LinkedList<>();

	public GameScreen(BBContext context, GameListener gameListener) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(gameListener, "gameListener can't be null");

		this.me = this;
		this.context = context;
		this.gameListener = gameListener;
		this.keyListener = buildKeyListener();
		this.tickListener = buildTickListener();
		this.timer = new Timer(BBConfig.FRAME_PERIOD_MS, tickListener);

		setBackground(Color.BLACK);
	}

	public BBContext getContext() {
		return context;
	}

	public abstract KeyListener buildKeyListener();

	public KeyListener getKeyListener() {
		return keyListener;
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public abstract boolean isActive();

	@Override
	public Dimension getPreferredSize() {
		return SCREEN_SIZE_PX;
	}

	public void addActor(Actor actor) {
		actors.add(actor);
	}

	public void updateModel() {
		garbageCollectActors();
		updateActors();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		actors.forEach(actor -> actor.paint(g));
	}

	protected void fireGameEvent(Object source, String type) {
		Assert.notNull(source, "source can't be null");
		Assert.notNull(type, "type can't be null");
		gameListener.handleEvent(new GameEvent(source, type));
	}

	private ActionListener buildTickListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Use the game screen for lifecycle management, not the model, as the screen manages the actors.
				if (isActive()) {
					updateModel();
					getTopLevelAncestor().repaint();
				} else {
					fireGameEvent(me, GameEvent.SCREEN_EXPIRED);
				}
			}
		};
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
