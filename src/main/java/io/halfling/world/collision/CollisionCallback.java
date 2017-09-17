package io.halfling.world.collision;

import io.halfling.world.entity.model.Actor;

/**
 * Created by wwheeler on 7/16/17.
 */
public interface CollisionCallback<T extends Actor, U extends Actor> {
	
	void handleCollision(Scene scene, T thisActor, U thatActor);
}
