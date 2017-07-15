package bb.common.scene;

import bb.common.actor.model.Bully;
import bb.common.actor.model.Dog;
import bb.common.event.GameEvents;
import retroge.actor.Actor;
import retroge.actor.ActorLifecycleState;
import retroge.actor.Player;
import retroge.event.GameEvent;
import retroge.util.Assert;
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
			checkPlayerDeath(scene, scene.getObstacles());
			checkPlayerDeath(scene, scene.getJudos());
			checkPlayerDeath(scene, scene.getBullies());
		}

		checkCollisions(scene, scene.getJudos(), scene.getObstacles(), GameEvents.JUDO_DIED);
		checkCollisions(scene, scene.getBalloons(), scene.getObstacles(), GameEvents.OBSTACLE_DESTROYED);
		checkCollisions(scene, scene.getBalloons(), scene.getJudos(), GameEvents.JUDO_DIED);

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
						log.trace("Player collided with {}", actor.getClass().getSimpleName());
						player.decrementLives();
						playerActor.setState(ActorLifecycleState.EXITING);
						actor.setState(ActorLifecycleState.EXITING);
						scene.fireGameEvent(GameEvents.PLAYER_DIED);
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
									scene.fireGameEvent(GameEvents.ANIMAL_DIED);
								}
							});
				});
	}
}
