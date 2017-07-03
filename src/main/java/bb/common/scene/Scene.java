package bb.common.scene;

import bb.common.actor.model.ActorState;
import bb.common.actor.model.Balloon;
import bb.common.actor.model.BigBalloon;
import bb.common.actor.model.Judo;
import bb.common.actor.model.Lexi;
import bb.common.actor.model.Obstacle;
import bb.common.actor.model.Text;
import bb.framework.actor.Actor;
import bb.framework.event.ActorEvent;
import bb.framework.event.ActorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by willie on 7/2/17.
 */
public class Scene {
	private static final Logger log = LoggerFactory.getLogger(Scene.class);

	private final List<List<? extends Actor>> allActors = new ArrayList<>();

	private final List<Balloon> balloons = new LinkedList<>();
	private final List<BigBalloon> bigBalloons = new LinkedList<>();
	private final List<Judo> judos = new LinkedList<>();
	private final List<Lexi> lexis = new LinkedList<>();
	private final List<Obstacle> obstacles = new LinkedList<>();
	private final List<Text> texts = new LinkedList<>();

	private final List<ActorListener> actorListeners = new LinkedList<>();

	public Scene() {
		allActors.add(balloons);
		allActors.add(bigBalloons);
		allActors.add(judos);
		allActors.add(lexis);
		allActors.add(obstacles);
		allActors.add(texts);
	}

	public boolean isActive() {
		return true;
	}

	public List<List<? extends Actor>> getAllActors() {
		return allActors;
	}

	public List<Balloon> getBalloons() {
		return balloons;
	}

	public List<BigBalloon> getBigBalloons() {
		return bigBalloons;
	}

	public List<Judo> getJudos() {
		return judos;
	}

	public List<Lexi> getLexis() {
		return lexis;
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public List<Text> getTexts() {
		return texts;
	}

	public void addActorListener(ActorListener listener) {
		actorListeners.add(listener);
	}

	public void update() {
//		log.trace("balloons.size={}", balloons.size());
//		log.trace("bigBalloons.size={}", bigBalloons.size());
		garbageCollectActors();
		updateActors();
		CollisionDetector.checkCollisions(this);
	}

	public void fireEvent(ActorEvent event) {
		actorListeners.forEach(listener -> listener.handleEvent(event));
	}

	private void garbageCollectActors() {
		allActors.forEach(actors -> {
			for (ListIterator<? extends Actor> it = actors.listIterator(); it.hasNext();) {
				Actor actor = it.next();
				if (actor.getState() == ActorState.GONE) {
					it.remove();
				}
			}
		});
	}

	private void updateActors() {
		allActors.forEach(actors -> actors.forEach(actor -> actor.update()));
	}
}
