package bb.game;

import bb.common.actor.model.Lexi;
import bb.framework.actor.Player;
import bb.framework.actor.brain.BasicActorBrain;
import bb.game.arena.level.Level;
import bb.game.arena.level.Levels;
import bb.game.arena.scene.ArenaScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		this.scene = new ArenaScene(player, level);
	}
	
	private Lexi createLexi() {
		return new Lexi(new BasicActorBrain(), 0, 0);
	}
}
