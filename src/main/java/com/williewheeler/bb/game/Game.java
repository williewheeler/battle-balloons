package com.williewheeler.bb.game;

import com.williewheeler.bb.common.actor.model.Lexi;
import com.williewheeler.bb.game.level.Level;
import com.williewheeler.bb.game.level.Levels;
import com.williewheeler.bb.game.scene.ArenaScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.williewheeler.retroge.actor.Player;
import com.williewheeler.retroge.actor.brain.BasicActorBrain;

/**
 * Created by wwheeler on 7/7/17.
 */
public class Game {
	private static final Logger log = LoggerFactory.getLogger(Game.class);
	
	private final Levels levels;
	private final Player player;
	private ArenaScene scene;
	
	public Game() {
		this.levels = new Levels();
		this.player = new Player();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ArenaScene getScene() {
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
		this.scene = new ArenaScene(player, level);
	}
	
	private Lexi createLexi() {
		return new Lexi(new BasicActorBrain(), 0, 0);
	}
}
