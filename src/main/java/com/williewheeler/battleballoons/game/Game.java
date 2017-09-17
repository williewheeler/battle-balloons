package com.williewheeler.battleballoons.game;

import com.williewheeler.battleballoons.common.world.entity.model.Lexi;
import com.williewheeler.battleballoons.game.level.Level;
import com.williewheeler.battleballoons.game.level.Levels;
import com.williewheeler.battleballoons.game.world.GameScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.halfling.world.entity.model.Player;
import io.halfling.world.entity.brain.BasicActorBrain;

/**
 * Created by wwheeler on 7/7/17.
 */
public class Game {
	private static final Logger log = LoggerFactory.getLogger(Game.class);
	
	private final Levels levels;
	private final Player player;
	private GameScene scene;
	
	public Game() {
		this.levels = new Levels();
		this.player = new Player();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public GameScene getScene() {
		return scene;
	}
	
	public void spawnPlayer() {
		log.trace("Spawning player");
		if (scene == null) {
			throw new IllegalStateException("scene can't be null");
		}
		scene.spawnPlayer();
	}
	
	public void incrementPlayerLevel() {
		player.incrementLevel();
		player.setActor(createLexi());
		Level level = levels.getLevel(player.getLevel());
		
		// TODO Figure out how to add the AudioHandler whenever we create a
		// new scene. Maybe we do that when there's a new level event?
		this.scene = new GameScene(player, level);
	}
	
	private Lexi createLexi() {
		return new Lexi(new BasicActorBrain(), 0, 0);
	}
}
