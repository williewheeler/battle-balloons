package bb.common.scene;

import bb.common.actor.model.ActorState;
import bb.framework.actor.Actor;
import bb.framework.util.Assert;

import java.util.List;

/**
 * Created by willie on 7/3/17.
 */
public final class CollisionDetector {

	public static void checkCollisions(Scene scene) {
		Assert.notNull(scene, "scene can't be null");
		checkCollisions(scene.getLexis(), scene.getObstacles());
		checkCollisions(scene.getLexis(), scene.getJudos());
		checkCollisions(scene.getJudos(), scene.getObstacles());
	}

	private static void checkCollisions(List<? extends Actor> these, List<? extends Actor> those) {
		these.stream()
				.filter(thisOne -> thisOne.getState() == ActorState.ACTIVE)
				.forEach(thisOne -> {
					those.stream()
							.filter(thatOne -> thatOne.getState() == ActorState.ACTIVE)
							.forEach(thatOne -> {
								if (thisOne.checkCollision(thatOne)) {
									thisOne.setState(ActorState.EXITING);
									thatOne.setState(ActorState.EXITING);
								}
							});
				});
	}
}
