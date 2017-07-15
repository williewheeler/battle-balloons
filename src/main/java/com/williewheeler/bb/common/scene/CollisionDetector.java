package com.williewheeler.bb.common.scene;

import com.williewheeler.bb.common.actor.model.Balloon;
import com.williewheeler.bb.common.actor.model.Bully;
import com.williewheeler.bb.common.actor.model.Dog;
import com.williewheeler.bb.common.event.GameEvents;
import com.williewheeler.retroge.actor.Actor;
import com.williewheeler.retroge.actor.ActorLifecycleState;
import com.williewheeler.retroge.actor.Player;
import com.williewheeler.retroge.event.GameEvent;
import com.williewheeler.retroge.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by willie on 7/3/17.
 */
public final class CollisionDetector {
	private static final Logger log = LoggerFactory.getLogger(CollisionDetector.class);

	public static void checkCollisions(BBScene scene) {
		Assert.notNull(scene, "scene can't be null");

		Player player = scene.getPlayer();
		if (player != null && player.getActor().getState() == ActorLifecycleState.ACTIVE) {
			checkPlayerRescue(scene, scene.getDogs());
			checkPlayerDeath(scene, scene.getObstacles(), true);
			checkPlayerDeath(scene, scene.getJudos(), true);
			checkPlayerDeath(scene, scene.getBullies(), false);
		}

		checkCollisions(scene, scene.getJudos(), scene.getObstacles(), GameEvents.JUDO_DIED, true);
		checkCollisions(scene, scene.getBalloons(), scene.getObstacles(), GameEvents.OBSTACLE_DESTROYED, true);
		checkCollisions(scene, scene.getBalloons(), scene.getJudos(), GameEvents.JUDO_DIED, true);
		checkBalloonBullyCollisions(scene);
		
		checkBullyAnimalCollisions(scene);

		// TODO Handle balloon/bully collisions, but the bully is indestructable! [WLW]
	}

	// TODO Combine this with checkPlayerDeath() by allowing client code to pass in a collision lambda. [WLW]
	private static void checkPlayerRescue(BBScene scene, List<? extends Actor> actors) {
		Player player = scene.getPlayer();
		assert (player != null);
		Actor playerActor = player.getActor();
		actors.stream()
				.filter(actor -> actor.getState() == ActorLifecycleState.ACTIVE)
				.forEach(actor -> {
					if (playerActor.checkCollision(actor)) {
						log.trace("Player rescued {}", actor.getClass().getSimpleName());
						player.increaseScore(actor.getScore());
						actor.setState(ActorLifecycleState.EXITING);
						scene.fireGameEvent(GameEvents.ANIMAL_RESCUED);
					}
				});
	}

	private static void checkPlayerDeath(BBScene scene, List<? extends Actor> actors, boolean actorDies) {
		Player player = scene.getPlayer();
		assert (player != null);
		Actor playerActor = player.getActor();
		actors.stream()
				.filter(actor -> actor.getState() == ActorLifecycleState.ACTIVE)
				.forEach(actor -> {
					if (playerActor.checkCollision(actor)) {
						log.trace("Player collided with {}", actor.getClass().getSimpleName());
						player.decrementLives();
						playerActor.setState(ActorLifecycleState.EXITING);
						if (actorDies) {
							actor.setState(ActorLifecycleState.EXITING);
							// TODO move this to actor's set state so we don't have to repeat
							// the logic in multiple collision detection methods. [WLW]
							player.increaseScore(actor.getScore());
						}
						scene.fireGameEvent(GameEvents.PLAYER_DIED);
					}
				});
	}
	
	private static void checkCollisions(
			BBScene scene,
			List<? extends Actor> these,
			List<? extends Actor> those,
			GameEvent event,
			boolean thoseDie) {
		
		these.stream()
				.filter(thisOne -> thisOne.getState() == ActorLifecycleState.ACTIVE)
				.forEach(thisOne -> {
					those.stream()
							.filter(thatOne -> thatOne.getState() == ActorLifecycleState.ACTIVE)
							.forEach(thatOne -> {
								if (thisOne.checkCollision(thatOne)) {
									thisOne.setState(ActorLifecycleState.EXITING);
									if (thoseDie) {
										thatOne.setState(ActorLifecycleState.EXITING);
									}
									
									final Player player = scene.getPlayer();
									if (player != null) {
										// TODO move this to actor's set state so we don't have to repeat
										// the logic in multiple collision detection methods. [WLW]
										player.increaseScore(thisOne.getScore());
										if (thoseDie) {
											player.increaseScore(thatOne.getScore());
										}
									}
									
									if (event != null) {
										scene.fireGameEvent(event);
									}
								}
							});
				});
	}
	
	private static void checkBalloonBullyCollisions(BBScene scene) {
		List<Balloon> balloons = scene.getBalloons();
		List<Bully> bullies = scene.getBullies();
		balloons.stream()
				.filter(balloon -> balloon.getState() == ActorLifecycleState.ACTIVE)
				.forEach(balloon -> {
					bullies.stream()
							// No need for this.
//							.filter(bully -> bully.getState() == ActorLifecycleState.ACTIVE)
							.forEach(bully -> {
								if (balloon.checkCollision(bully)) {
									bully.changeX(balloon.getDx());
									bully.changeY(balloon.getDy());
									
									// Keep EXITING here since I want to make the balloons pop.
									balloon.setState(ActorLifecycleState.EXITING);
								}
							});
				});
	}
	
	private static void checkBullyAnimalCollisions(BBScene scene) {
		List<Bully> bullies = scene.getBullies();
		List<Dog> dogs = scene.getDogs();
		bullies.stream()
				.filter(bully -> bully.getState() == ActorLifecycleState.ACTIVE)
				.forEach(bully -> {
					dogs.stream()
							.filter(dog -> dog.getState() == ActorLifecycleState.ACTIVE)
							.forEach(dog -> {
								if (bully.checkCollision(dog)) {
									dog.setState(ActorLifecycleState.EXITING);
									scene.fireGameEvent(GameEvents.ANIMAL_DIED);
								}
							});
				});
	}
}
