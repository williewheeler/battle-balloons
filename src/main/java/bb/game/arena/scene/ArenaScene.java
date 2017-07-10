package bb.game.arena.scene;

import bb.common.actor.model.Bully;
import bb.common.actor.model.Dog;
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
import bb.game.arena.actor.DefaultAnimalBrain;
import bb.game.arena.actor.DefaultBullyBrain;
import bb.game.arena.actor.DefaultJudoBrain;
import bb.game.arena.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScene extends BBScene {
	private static final Logger log = LoggerFactory.getLogger(ArenaScene.class);

	private Player player;
	private Level level;

	public ArenaScene(Player player, Level level) {
		Assert.notNull(player, "player can't be null");
		Assert.notNull(level, "level can't be null");
		this.player = player;
		this.level = level;
		initPlayer();
		initActors();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void spawnPlayer() {
		log.trace("Spawning player");
		getBalloons().clear();
		player.spawn();
		randomizeActors();
		log.trace("player={}", player);
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
	
	private void initPlayer() {
		log.trace("Initializing player");
		
		// Just create a new Lexi to reset everything.
		// FIXME Don't hardcode this to Lexi. [WLW]
		Lexi lexi = new Lexi(new BasicActorBrain(), 0, 0);
		lexi.setScene(this);
		player.setActor(lexi);
	}
	
	private void initActors() {
		initObstacles();
		initJudos();
		initBullies();
		initDogs();
	}
	
	private void initObstacles() {
		log.trace("Initializing obstacles");
		for (int i = 0; i < level.getObstacles(); i++) {
			Obstacle obstacle = new Obstacle(0, 0);
			obstacle.setScene(this);
			getObstacles().add(obstacle);
		}
	}

	private void initJudos() {
		log.trace("Initializing Judos");
		for (int i = 0; i < level.getJudos(); i++) {
			Judo judo = new Judo(new DefaultJudoBrain(), 0, 0);
			judo.setScene(this);
			getJudos().add(judo);
		}
	}

	private void initBullies() {
		log.trace("Initializing bullies");
		for (int i = 0; i < level.getBullies(); i++) {
			Bully bully = new Bully(new DefaultBullyBrain(), 0, 0);
			bully.setScene(this);
			ActorUtil.randomizeLocation(bully, player);
			getBullies().add(bully);
		}
	}

	private void initDogs() {
		log.trace("Initializing dogs");
		for (int i = 0; i < level.getDogs(); i++) {
			Dog dog = new Dog(new DefaultAnimalBrain(), 0, 0);
			dog.setScene(this);
			ActorUtil.randomizeLocation(dog, player);
			getDogs().add(dog);
		}
	}
	
	private void randomizeActors() {
		log.trace("Randomizing actors");
		randomizeActors(getObstacles());
		randomizeActors(getJudos());
		randomizeActors(getBullies());
		randomizeActors(getDogs());
	}
	
	private void randomizeActors(List<? extends Actor> actors) {
		actors.forEach(actor -> {
			ActorUtil.randomizeLocation(actor, player);
			ActorUtil.randomizeDirection(actor);
		});
	}

	private void checkNextLevel() {
		if (getJudos().isEmpty()) {
			player.incrementLevel();
			setActive(false);
			fireGameEvent(GameEvents.NEXT_LEVEL);
		}
	}
}
