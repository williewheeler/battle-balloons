package bb.framework.actor;

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
}
