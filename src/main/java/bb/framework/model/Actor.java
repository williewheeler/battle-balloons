package bb.framework.model;

import bb.framework.util.Assert;
import bb.framework.view.ActorView;

import java.awt.Graphics;

/**
 * Created by willie on 6/21/17.
 */
public class Actor {
	private AbstractActorModel model;
	private ActorView view;

	public Actor(AbstractActorModel model, ActorView view) {
		Assert.notNull(model, "model can't be null");
		Assert.notNull(view, "view can't be null");
		this.model = model;
		this.view = view;
	}

	public AbstractActorModel getModel() {
		return model;
	}

	public ActorView getView() {
		return view;
	}

	public void paint(Graphics g) {
		view.paint(g);
	}
}
