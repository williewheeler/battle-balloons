package bb.framework.scene;

import bb.framework.actor.Actor;
import bb.framework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by willie on 7/2/17.
 */
public class Scene {
	private final List<Actor> actors = new LinkedList<>();

	public List<Actor> getActors() {
		return actors;
	}

	public boolean isActive() {
		return true;
	}

	public void addActor(Actor actor) {
		Assert.notNull(actor, "actor can't be null");
		actors.add(actor);
	}

	public void update() {
		garbageCollectActors();
		updateActors();
	}

	private void garbageCollectActors() {
		for (ListIterator<Actor> it = actors.listIterator(); it.hasNext();) {
			Actor actor = it.next();
			if (actor.isReadyForGC()) {
				it.remove();
			}
		}
	}

	private void updateActors() {
		actors.forEach(actor -> actor.update());
	}
}
