package bb.framework.actor;

/**
 * Actor's sensorimotor model.
 *
 * Created by willie on 6/25/17.
 */
public interface ActorBrain {

	void setActor(Actor actor);

	DirectionIntent getMoveDirectionIntent();

	DirectionIntent getFireDirectionIntent();

	void update();
}
