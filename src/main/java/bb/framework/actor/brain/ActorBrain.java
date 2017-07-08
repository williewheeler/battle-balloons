package bb.framework.actor.brain;

import bb.framework.actor.Actor;
import bb.framework.actor.DirectionIntent;

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
	
	void reset();
}
