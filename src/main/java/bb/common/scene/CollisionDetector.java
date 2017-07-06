package bb.common.scene;

import bb.common.actor.model.Bully;
import bb.common.actor.model.Dog;
import bb.common.event.GameEvents;
import bb.framework.actor.Actor;
import bb.framework.actor.ActorLifecycleState;
import bb.framework.actor.Player;
import bb.framework.event.GameEvent;
import bb.framework.util.Assert;

import java.util.List;

/**
 * Created by willie on 7/3/17.
 */
public final class CollisionDetector {

	public static void checkCollisions(BBScene scene) {
		Assert.notNull(scene, "scene can't be null");

		Player player = scene.getPlayer();
		if (player != null) {
			checkPlayerRescue(scene, scene.getDogs());

			checkPlayerDeath(scene, scene.getObstacles());
			checkPlayerDeath(scene, scene.getJudos());
			checkPlayerDeath(scene, scene.getBullies());
		}

		checkCollisions(scene, scene.getJudos(), scene.getObstacles(), GameEvents.JUDO_DIES);
		checkCollisions(scene, scene.getBalloons(), scene.getObstacles(), GameEvents.OBSTACLE_DESTROYED);
		checkCollisions(scene, scene.getBalloons(), scene.getJudos(), GameEvents.JUDO_DIES);

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
						player.increaseScore(actor.getScore());
						actor.setState(ActorLifecycleState.EXITING);
						scene.fireGameEvent(GameEvents.ANIMAL_RESCUED);
					}
				});
	}

	private static void checkPlayerDeath(BBScene scene, List<? extends Actor> actors) {
		Player player = scene.getPlayer();
		assert (player != null);
		Actor playerActor = player.getActor();
		actors.stream()
				.filter(actor -> actor.getState() == ActorLifecycleState.ACTIVE)
				// FIXME This could allow a player to lose multiple lives at once (when there are multiple
				// simultaneous collisions).
				.forEach(actor -> {
					if (playerActor.checkCollision(actor)) {
						player.decrementLives();
						playerActor.setState(ActorLifecycleState.EXITING);
						actor.setState(ActorLifecycleState.EXITING);
						scene.fireGameEvent(GameEvents.PLAYER_DIES);
					}
				});
	}

	private static void checkCollisions(
			BBScene scene,
			List<? extends Actor> these,
			List<? extends Actor> those,
			GameEvent event) {

		these.stream()
				.filter(thisOne -> thisOne.getState() == ActorLifecycleState.ACTIVE)
				.forEach(thisOne -> {
					those.stream()
							.filter(thatOne -> thatOne.getState() == ActorLifecycleState.ACTIVE)
							.forEach(thatOne -> {
								if (thisOne.checkCollision(thatOne)) {
									thisOne.setState(ActorLifecycleState.EXITING);
									thatOne.setState(ActorLifecycleState.EXITING);

									final Player player = scene.getPlayer();
									if (player != null) {
										player.increaseScore(thisOne.getScore());
										player.increaseScore(thatOne.getScore());
									}

									if (event != null) {
										scene.fireGameEvent(event);
									}
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
									scene.fireGameEvent(GameEvents.ANIMAL_DIES);
								}
							});
				});
	}
}
