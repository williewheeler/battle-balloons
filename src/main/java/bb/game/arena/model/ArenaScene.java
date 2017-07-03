package bb.game.arena.model;

import bb.common.actor.model.ActorState;
import bb.common.actor.model.BasicActorBrain;
import bb.common.actor.model.Judo;
import bb.common.actor.model.Lexi;
import bb.common.actor.model.Obstacle;
import bb.common.scene.Scene;

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

	@Override
	public void update() {
		if (player.getActor().getState() != ActorState.GONE) {
			super.update();
		}
	}

	private void initScene() {
		initPlayer();
		initObstacles();
		initJudos();
	}

	private void initPlayer() {
		Lexi lexi = new Lexi(this, new BasicActorBrain(), 0, 0);
		ActorUtil.center(lexi);
		getLexis().add(lexi);

		// This too
		this.player = new Player(lexi);
	}

	private void initObstacles() {
		for (int i = 0; i < INIT_NUM_OBSTACLES; i++) {
			Obstacle obstacle = new Obstacle(this, 0, 0);
			ActorUtil.randomizeLocation(obstacle, player);
			getObstacles().add(obstacle);
		}
	}

	private void initJudos() {
		for (int i = 0; i < INIT_NUM_JUDOS; i++) {
			Judo judo = new Judo(this, new ArenaJudoBrain(), 0, 0);
			ActorUtil.randomizeLocation(judo, player);
			getJudos().add(judo);
		}
	}
}
