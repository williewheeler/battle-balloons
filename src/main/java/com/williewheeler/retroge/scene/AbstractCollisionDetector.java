package com.williewheeler.retroge.scene;

import com.williewheeler.retroge.actor.Actor;
import com.williewheeler.retroge.actor.ActorLifecycleState;
import com.williewheeler.retroge.actor.Player;

import java.util.List;

/**
 * Created by wwheeler on 7/16/17.
 */
public abstract class AbstractCollisionDetector implements CollisionDetector {
	
	protected <T extends Actor, U extends Actor> void checkCollisions(
			Scene scene,
			List<T> theseActors,
			List<U> thoseActors,
			CollisionCallback<T, U> callback) {
		
		// TODO Should I calculate thoseActors.stream().filter() just one time at the outset?
		// Not sure I can, as thoseActors can be involved in collisions as we iterate over
		// theseActors. [WLW]
		theseActors.stream()
				.filter(thisActor -> thisActor.getState() == ActorLifecycleState.ACTIVE)
				.forEach(thatActor -> {
					thoseActors.stream()
							.filter(thatOne -> thatOne.getState() == ActorLifecycleState.ACTIVE)
							.forEach(thatOne -> {
								if (thatActor.checkCollision(thatOne)) {
									callback.handleCollision(scene, thatActor, thatOne);
								}
							});
				});
	}
	
	protected <T extends Actor> void checkPlayerCollision(
			Scene scene,
			List<T> actors,
			CollisionCallback<Actor, T> callback) {
		
		final Player player = scene.getPlayer();
		if (player != null) {
			final Actor playerActor = player.getActor();
			
			if (playerActor.getState() == ActorLifecycleState.ACTIVE) {
				actors.stream()
						.filter(actor -> actor.getState() == ActorLifecycleState.ACTIVE)
						.forEach(actor -> {
							if (playerActor.checkCollision(actor)) {
								callback.handleCollision(scene, playerActor, actor);
							}
						});
			}
		}
	}
}
