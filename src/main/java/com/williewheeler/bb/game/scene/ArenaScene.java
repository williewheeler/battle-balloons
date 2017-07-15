package com.williewheeler.bb.game.scene;

import com.williewheeler.bb.common.actor.model.Bully;
import com.williewheeler.bb.common.actor.model.Dog;
import com.williewheeler.bb.common.actor.model.Judo;
import com.williewheeler.bb.common.actor.model.Lexi;
import com.williewheeler.bb.common.actor.model.Obstacle;
import com.williewheeler.bb.common.event.GameEvents;
import com.williewheeler.bb.common.scene.BBScene;
import com.williewheeler.bb.game.actor.ActorUtil;
import com.williewheeler.bb.game.actor.DefaultAnimalBrain;
import com.williewheeler.bb.game.actor.DefaultBullyBrain;
import com.williewheeler.bb.game.actor.DefaultJudoBrain;
import com.williewheeler.bb.game.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.williewheeler.retroge.actor.Actor;
import com.williewheeler.retroge.actor.ActorLifecycleState;
import com.williewheeler.retroge.actor.Player;
import com.williewheeler.retroge.actor.brain.BasicActorBrain;
import com.williewheeler.retroge.util.Assert;

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
		
		getBullies().forEach(bully -> {
			ActorUtil.randomizeLocation(bully, player);
			ActorUtil.randomizeDirectionNoDiagonals(bully);
		});
		
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
			
			// Don't do this here. Scene is not responsible for level management.
			// When the level is complete, all we do here is signal that. [WLW]
			// TODO It's confusing that the other GameEvents indicate that the
			// state change has already occurred, whereas this one indicates that
			// the level needs to be incremented. [WLW]
//			player.incrementLevel();
			
			setActive(false);
			fireGameEvent(GameEvents.NEXT_LEVEL);
		}
	}
}
