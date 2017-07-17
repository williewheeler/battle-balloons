package com.williewheeler.retroge.actor.brain;

import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.model.DirectionIntent;

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
