package com.williewheeler.bb.game.scene;

import com.williewheeler.bb.common.actor.model.Bengy;
import com.williewheeler.bb.common.actor.model.Bully;
import com.williewheeler.bb.common.actor.model.Cat;
import com.williewheeler.bb.common.actor.model.Dog;
import com.williewheeler.bb.common.actor.model.Judo;
import com.williewheeler.bb.common.actor.model.Lexi;
import com.williewheeler.bb.common.actor.model.Obstacle;
import com.williewheeler.bb.common.actor.model.Parrot;
import com.williewheeler.bb.common.actor.model.YardDuty;
import com.williewheeler.bb.common.event.GameEvents;
import com.williewheeler.bb.common.scene.BBScene;
import com.williewheeler.bb.game.actor.ActorUtil;
import com.williewheeler.bb.game.actor.brain.DefaultBengyBrain;
import com.williewheeler.bb.game.actor.brain.DefaultBullyBrain;
import com.williewheeler.bb.game.actor.brain.DefaultJudoBrain;
import com.williewheeler.bb.game.level.Level;
import com.williewheeler.retroge.actor.brain.BasicActorBrain;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.model.ActorLifecycleState;
import com.williewheeler.retroge.actor.model.Player;
import com.williewheeler.retroge.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by willie on 6/4/17.
 */
public class GameScene extends BBScene {
	private static final Logger log = LoggerFactory.getLogger(GameScene.class);
	
	private Player player;
	private Level level;

	public GameScene(Player player, Level level) {
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
		clearMissiles();
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
		initYardDuties();
		initBengies();
		initDogs();
		initCats();
		initParrots();
	}
	
	private void initObstacles() {
		log.trace("Initializing obstacles");
		for (int i = 0; i < level.getObstacles(); i++) {
			getObstacles().add(new Obstacle(this, 0, 0));
		}
	}

	private void initJudos() {
		log.trace("Initializing Judos");
		for (int i = 0; i < level.getJudos(); i++) {
			getJudos().add(new Judo(this, new DefaultJudoBrain(), 0, 0));
		}
	}

	private void initBullies() {
		log.trace("Initializing bullies");
		for (int i = 0; i < level.getBullies(); i++) {
			getBullies().add(new Bully(this, new DefaultBullyBrain(), 0, 0));
		}
	}

	private void initYardDuties() {
		log.trace("Initializing yard duties");
		for (int i = 0; i < level.getYardDuties(); i++) {

			// FIXME Give the yard duty its own brain
			getYardDuties().add(new YardDuty(this, new DefaultBengyBrain(), 0, 0));
		}
	}

	private void initBengies() {
		log.trace("Initializing Bengies");
		for (int i = 0; i < level.getBengies(); i++) {
			getBengies().add(new Bengy(this, new DefaultBengyBrain(), 0, 0));
		}
	}

	private void initDogs() {
		log.trace("Initializing dogs");
		for (int i = 0; i < level.getDogs(); i++) {
			getAnimals().add(new Dog(this, 0, 0));
		}
	}

	private void initCats() {
		log.trace("Initializing cats");
		for (int i = 0; i < level.getCats(); i++) {
			getAnimals().add(new Cat(this, 0, 0));
		}
	}

	private void initParrots() {
		log.trace("Initializing parrots");
		for (int i = 0; i < level.getParrots(); i++) {
			getAnimals().add(new Parrot(this, 0, 0));
		}
	}

	private void clearMissiles() {
		getBalloons().clear();
		getBeats().clear();
	}

	private void randomizeActors() {
		log.trace("Randomizing actors");
		randomizeActors(getObstacles());
		randomizeActors(getJudos());
		
		getBullies().forEach(bully -> {
			ActorUtil.randomizeLocation(bully, player);
			ActorUtil.randomizeDirectionNoDiagonals(bully);
		});

		randomizeActors(getYardDuties());
		randomizeActors(getBengies());
		randomizeActors(getTurntables());
		randomizeActors(getAnimals());
	}
	
	private void randomizeActors(List<? extends Actor> actors) {
		actors.forEach(actor -> {
			ActorUtil.randomizeLocation(actor, player);
			ActorUtil.randomizeDirection(actor);
		});
	}

	private void checkNextLevel() {
		if (getJudos().isEmpty() && getYardDuties().isEmpty() && getBengies().isEmpty() && getTurntables().isEmpty()) {
			
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
