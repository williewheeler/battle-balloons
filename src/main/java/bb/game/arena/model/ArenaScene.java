package bb.game.arena.model;

import bb.common.actor.model.Lexi;
import bb.common.actor.model.Obstacle;
import bb.framework.actor.BasicActorBrain;
import bb.framework.scene.Scene;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScene extends Scene {
	private static final int INIT_NUM_OBSTACLES = 15;
	private static final int INIT_NUM_JUDOS = 10;

	private Player player;

	public ArenaScene() {
		initScene();
	}

	public Player getPlayer() {
		return player;
	}

	private void initScene() {
		initPlayer();
		initObstacles();
	}

	private void initPlayer() {
		Lexi lexi = new Lexi(new BasicActorBrain(), 0, 0);
		ActorUtil.center(lexi);
		addActor(lexi);
		this.player = new Player(lexi);
	}

	private void initObstacles() {
		for (int i = 0; i < INIT_NUM_OBSTACLES; i++) {
			Obstacle obstacle = new Obstacle(0, 0);
			ActorUtil.randomizeLocation(obstacle, player);
			addActor(obstacle);
		}
	}
}
