package bb.common.actor.view;

import bb.framework.actor.Actor;
import bb.framework.actor.ActorView;

import java.awt.Graphics;

/**
 * Created by willie on 7/3/17.
 */
public abstract class AbstractActorView implements ActorView {

	@Override
	public void paint(Graphics g, Actor actor) {
		switch (actor.getState()) {
			case ENTERING:
				paintEntering(g, actor);
				break;
			case ACTIVE:
				paintActive(g, actor);
				break;
			case EXITING:
				paintExiting(g, actor);
				break;
		}
	}

	public abstract void paintEntering(Graphics g, Actor actor);

	public abstract void paintActive(Graphics g, Actor actor);

	public abstract void paintExiting(Graphics g, Actor actor);
}
