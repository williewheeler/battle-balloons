package bb.common.scene;

import bb.common.event.GameEvents;
import bb.framework.actor.Actor;
import bb.framework.actor.ActorLifecycleState;
import bb.framework.actor.Player;
import bb.framework.event.GameEvent;
import bb.framework.util.Assert;
import bb.game.arena.scene.ArenaScene;

import java.util.Collections;
import java.util.List;

/**
 * Created by willie on 7/3/17.
 */
public final class CollisionDetector {

	public static void checkCollisions(BBScene scene) {
		Assert.notNull(scene, "scene can't be null");

		checkPlayerCollisions(scene, scene.getObstacles(), GameEvents.PLAYER_COLLISION);
		checkPlayerCollisions(scene, scene.getJudos(), GameEvents.PLAYER_COLLISION);

		checkCollisions(scene, scene.getJudos(), scene.getObstacles(), GameEvents.JUDO_HIT);
		checkCollisions(scene, scene.getBalloons(), scene.getObstacles(), null);
		checkCollisions(scene, scene.getBalloons(), scene.getJudos(), GameEvents.JUDO_HIT);
	}

	private static void checkPlayerCollisions(
			BBScene scene,
			List<? extends Actor> actors,
			GameEvent event) {

		Player player = scene.getPlayer();
		if (player != null) {
			Actor actor = player.getActor();
			List<? extends Actor> these = Collections.singletonList(actor);
			checkCollisions(scene, these, actors, event);
		}
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

									// FIXME This is hacky. Figure out a better way. [WLW]
									if (scene instanceof ArenaScene) {
										final Player player = ((ArenaScene) scene).getPlayer();
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
}
