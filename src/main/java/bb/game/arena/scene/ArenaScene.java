package bb.game.arena.scene;

import bb.common.actor.model.Judo;
import bb.common.actor.model.Lexi;
import bb.common.actor.model.Obstacle;
import bb.common.event.GameEvents;
import bb.common.scene.BBScene;
import bb.framework.actor.Actor;
import bb.framework.actor.ActorLifecycleState;
import bb.framework.actor.Player;
import bb.framework.actor.brain.BasicActorBrain;
import bb.framework.util.Assert;
import bb.game.arena.actor.ActorUtil;
import bb.game.arena.actor.ArenaJudoBrain;
import bb.game.arena.level.Level;
import bb.game.arena.level.Levels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static bb.common.BBConfig.WORLD_SIZE;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScene extends BBScene {
	private static final Logger log = LoggerFactory.getLogger(ArenaScene.class);

	private Levels levels;
	private Player player;
	private Level level;

	public ArenaScene(Levels levels) {
		Assert.notNull(levels, "levels can't be null");
		this.levels = levels;
	}

	public void init(Player player) {
		Assert.notNull(player, "player can't be null");

		this.player = player;
		initPlayer();

		this.level = levels.getLevel(player.getLevel());

		initObstacles();
		initJudos();
	}

	private void initPlayer() {

		// Just create a new Lexi to reset everything.
		// FIXME Don't hardcode this to Lexi. [WLW]
		// FIXME Duplicates code from GameMode. [WLW]
		int x = WORLD_SIZE.width / 2;
		int y = WORLD_SIZE.height / 2;
		Lexi lexi = new Lexi(new BasicActorBrain(), x, y);
		lexi.setScene(this);
		player.setActor(lexi);
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void update() {
		if (player == null) {
			throw new IllegalStateException("player can't be null");
		}

		if (isActive()) {
			Actor playerActor = player.getActor();

			// Update player actor before calling super.update(), which includes collision detection.
			playerActor.update();
			super.update();

//			log.trace("player.actor.state = {}", playerActor.getState());
			if (playerActor.getState() == ActorLifecycleState.GONE) {
				// FIXME Hm, this is never happening.
				log.trace("Player is gone");
				setActive(false);
				if (player.isGameOver()) {
					fireGameEvent(GameEvents.GAME_OVER);
				}
			} else {
				checkNextLevel();
			}
		}
	}

	private void initObstacles() {
		for (int i = 0; i < level.getObstacles(); i++) {
			Obstacle obstacle = new Obstacle(0, 0);
			obstacle.setScene(this);
			ActorUtil.randomizeLocation(obstacle, player);
			getObstacles().add(obstacle);
		}
	}

	private void initJudos() {
		for (int i = 0; i < level.getJudos(); i++) {
			Judo judo = new Judo(new ArenaJudoBrain(), 0, 0);
			judo.setScene(this);
			ActorUtil.randomizeLocation(judo, player);
			getJudos().add(judo);
		}
	}

	private void checkNextLevel() {
		if (getJudos().isEmpty()) {
			player.incrementLevel();
			setActive(false);
			fireGameEvent(GameEvents.NEXT_LEVEL);
		}
	}
}
