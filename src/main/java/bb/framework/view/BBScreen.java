package bb.framework.view;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.framework.event.ScreenEvent;
import bb.framework.event.ScreenListener;
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

import static bb.common.BBConfig.SCREEN_SIZE_PX;

/**
 * Created by willie on 6/19/17.
 */
public abstract class BBScreen extends JPanel {
	private BBScreen me;
	private BBContext context;
	private KeyListener keyListener;
	private ActionListener tickListener;
	private Timer timer;

	private final List<Actor> actors = new LinkedList<>();
	private final List<ScreenListener> screenListeners = new LinkedList<>();

	public BBScreen(BBContext context) {
		Assert.notNull(context, "context can't be null");

		this.me = this;
		this.context = context;
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

	public void addScreenListener(ScreenListener listener) {
		screenListeners.add(listener);
	}

	protected void fireScreenEvent(int type) {
		ScreenEvent event = new ScreenEvent(type);
		screenListeners.forEach(listener -> listener.handleEvent(event));
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
					fireScreenEvent(ScreenEvent.SCREEN_EXPIRED);
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
