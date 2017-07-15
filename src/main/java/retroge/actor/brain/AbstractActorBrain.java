package retroge.actor.brain;

import retroge.actor.Actor;
import retroge.actor.DirectionIntent;

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
	public void reset() {
		moveDirectionIntent.reset();
		fireDirectionIntent.reset();
	}
}
