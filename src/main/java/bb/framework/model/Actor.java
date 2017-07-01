package bb.framework.model;

import bb.framework.util.Assert;
import bb.framework.view.actor.ActorView;

import java.awt.Graphics;

/**
 * Created by willie on 6/21/17.
 */
public class Actor {
	private ActorModel model;
	private ActorView view;
	private boolean started;
	private boolean stopped;

	public Actor(ActorModel model, ActorView view) {
		Assert.notNull(model, "model can't be null");
		Assert.notNull(view, "view can't be null");
		this.model = model;
		this.view = view;
		this.started = false;
		this.stopped = false;
	}

	public ActorModel getModel() {
		return model;
	}

	public ActorView getView() {
		return view;
	}

	public boolean getStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean getStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public void paint(Graphics g) {
		view.paint(g);
	}
}
