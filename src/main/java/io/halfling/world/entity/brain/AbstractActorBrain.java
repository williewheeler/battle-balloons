package io.halfling.world.entity.brain;

import io.halfling.world.entity.model.Actor;
import io.halfling.world.entity.model.ActorLifecycleState;
import io.halfling.world.entity.model.DirectionIntent;

/**
 * Created by willie on 7/2/17.
 */
public abstract class AbstractActorBrain implements ActorBrain {
	private Actor actor;
	private final DirectionIntent moveDirectionIntent = new DirectionIntent();
	private final DirectionIntent fireDirectionIntent = new DirectionIntent();

	public Actor getActor() {
		return actor;
	}

	@Override
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	@Override
	public DirectionIntent getMoveDirectionIntent() {
		return moveDirectionIntent;
	}

	@Override
	public DirectionIntent getFireDirectionIntent() {
		return fireDirectionIntent;
	}

	@Override
	public void update() {
		if (actor.getState() == ActorLifecycleState.ACTIVE) {
			updateActive();
		}
	}

	public void updateActive() {
		// No-op
	}

	@Override
	public void reset() {
		moveDirectionIntent.reset();
		fireDirectionIntent.reset();
	}
}
