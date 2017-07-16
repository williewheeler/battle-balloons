package com.williewheeler.retroge.scene;

import com.williewheeler.retroge.actor.Actor;

/**
 * Created by wwheeler on 7/16/17.
 */
public interface CollisionCallback<T extends Actor, U extends Actor> {
	
	void handleCollision(Scene scene, T thisActor, U thatActor);
}
