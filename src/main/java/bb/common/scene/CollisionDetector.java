package bb.common.scene;

import bb.framework.actor.ActorLifecycleState;
import bb.common.actor.event.ActorEvents;
import bb.framework.actor.Actor;
import bb.framework.event.ActorEvent;
import bb.framework.util.Assert;
import bb.game.arena.scene.ArenaScene;
import bb.framework.actor.Player;

import java.util.List;

/**
 * Created by willie on 7/3/17.
 */
public final class CollisionDetector {

	public static void checkCollisions(BBScene scene) {
		Assert.notNull(scene, "scene can't be null");
		checkCollisions(scene, scene.getLexis(), scene.getObstacles(), ActorEvents.PLAYER_COLLISION);
		checkCollisions(scene, scene.getLexis(), scene.getJudos(), ActorEvents.PLAYER_COLLISION);
		checkCollisions(scene, scene.getJudos(), scene.getObstacles(), ActorEvents.JUDO_HIT);
		checkCollisions(scene, scene.getBalloons(), scene.getObstacles(), null);
		checkCollisions(scene, scene.getBalloons(), scene.getJudos(), ActorEvents.JUDO_HIT);
	}

	private static void checkCollisions(
			BBScene scene,
			List<? extends Actor> these,
			List<? extends Actor> those,
			ActorEvent event) {

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
										scene.fireEvent(event);
									}
								}
							});
				});
	}
}
