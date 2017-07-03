package bb.common.scene;

import bb.common.actor.model.ActorState;
import bb.common.event.ActorEvents;
import bb.framework.actor.Actor;
import bb.framework.event.ActorEvent;
import bb.framework.util.Assert;
import bb.game.arena.model.ArenaScene;
import bb.game.arena.model.Player;

import java.util.List;

/**
 * Created by willie on 7/3/17.
 */
public final class CollisionDetector {

	public static void checkCollisions(Scene scene) {
		Assert.notNull(scene, "scene can't be null");
		checkCollisions(scene, scene.getLexis(), scene.getObstacles(), ActorEvents.PLAYER_COLLISION);
		checkCollisions(scene, scene.getLexis(), scene.getJudos(), ActorEvents.PLAYER_COLLISION);
		checkCollisions(scene, scene.getJudos(), scene.getObstacles(), ActorEvents.JUDO_HIT);
		checkCollisions(scene, scene.getBalloons(), scene.getObstacles(), null);
		checkCollisions(scene, scene.getBalloons(), scene.getJudos(), ActorEvents.JUDO_HIT);
	}

	private static void checkCollisions(
			Scene scene,
			List<? extends Actor> these,
			List<? extends Actor> those,
			ActorEvent event) {

		these.stream()
				.filter(thisOne -> thisOne.getState() == ActorState.ACTIVE)
				.forEach(thisOne -> {
					those.stream()
							.filter(thatOne -> thatOne.getState() == ActorState.ACTIVE)
							.forEach(thatOne -> {
								if (thisOne.checkCollision(thatOne)) {
									thisOne.setState(ActorState.EXITING);
									thatOne.setState(ActorState.EXITING);

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
