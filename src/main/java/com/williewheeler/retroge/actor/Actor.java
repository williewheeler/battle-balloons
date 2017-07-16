package com.williewheeler.retroge.actor;

import com.williewheeler.retroge.actor.brain.ActorBrain;

/**
 * Created by willie on 6/24/17.
 */
public interface Actor {

	ActorLifecycleState getState();

	void setState(ActorLifecycleState state);

	ActorBrain getBrain();

	int getX();

	void setX(int x);

	void changeX(int dx);

	int getY();

	void setY(int y);

	void changeY(int dy);

	int getWidth();

	int getHeight();

	int getSpeed();

	Direction getDirection();

	int getScore();

	/**
	 * <p>
	 * Updates the actor. This typically involves a "planning" phase (brain update) and an "execution" phase (body
	 * update).
	 * </p>
	 * <p>
	 * Actors are responsible for enforcing their own sanity checks during updates. For example, if there's any chance
	 * that the actor might move out of bounds, but shouldn't, then the actor should take the steps to ensure that this
	 * doesn't occur.
	 * </p>
	 * <p>
	 * Similarly, actors are responsible for marking themselves ready for garbage collection, and that would normally
	 * happen in this method.
	 * </p>
	 */
	void update();

	boolean checkCollision(Actor that);
}
