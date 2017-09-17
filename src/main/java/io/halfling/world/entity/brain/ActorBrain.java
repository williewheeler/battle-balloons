package io.halfling.world.entity.brain;

import io.halfling.world.entity.model.Actor;
import io.halfling.world.entity.model.DirectionIntent;

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
