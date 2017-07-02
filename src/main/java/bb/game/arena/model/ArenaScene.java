package bb.game.arena.model;

import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.scene.Scene;
import bb.game.arena.event.EntityState;
import bb.game.arena.event.GameEvents;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScene extends Scene {
	private static final int INIT_NUM_OBSTACLES = 15;
	private static final int INIT_NUM_JUDOS = 10;

	private final Player player;
	private final List<Balloon> playerBalloons = new LinkedList<>();
	private final List<Obstacle> obstacles = new LinkedList<>();
	private final List<Judo> judos = new LinkedList<>();

	private CollisionDetector collisionDetector = new CollisionDetector(this);

	private final List<GameListener> gameListeners = new LinkedList<>();

	public ArenaScene() {
		this.player = new Player(this);

		for (int i = 0; i < INIT_NUM_OBSTACLES; i++) {
			obstacles.add(new Obstacle(this));
		}
		for (int i = 0; i < INIT_NUM_JUDOS; i++) {
			judos.add(new Judo(this));
		}
	}

	public Player getPlayer() {
		return player;
	}
	
	public List<Balloon> getPlayerBalloons() {
		return playerBalloons;
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public List<Judo> getJudos() {
		return judos;
	}

	@Override
	public void update() {
		super.update();
		garbageCollectEntities();
		updateEntities();
	}

	public void addGameListener(GameListener listener) {
		gameListeners.add(listener);
	}

	protected void fireEvent(GameEvent event) {
		gameListeners.forEach(listener -> listener.handleEvent(event));
	}

	private void garbageCollectEntities() {
		garbageCollectJudos();
		garbageCollectBalloons();
	}

	private void garbageCollectJudos() {
		for (ListIterator<Judo> it = judos.listIterator(); it.hasNext();) {
			Judo judo = it.next();
			if (judo.getState() == EntityState.GONE) {
				it.remove();
			}
		}
	}

	private void garbageCollectBalloons() {
		for (ListIterator<Balloon> it = playerBalloons.listIterator(); it.hasNext();) {
			Balloon balloon = it.next();
			if (!balloon.isOnScreen()) {
				it.remove();
			}
		}
	}

	private void updateEntities() {
		if (player.getState() != EntityState.GONE) {
			player.update();
			obstacles.forEach(obstacle -> obstacle.update());
			judos.forEach(judo -> judo.update());
			playerBalloons.forEach(balloon -> balloon.update());

			boolean playerDead = collisionDetector.checkCollisions();
			if (playerDead) {
				player.setState(EntityState.EXITING);
				fireEvent(GameEvents.PLAYER_COLLISION);
			}
		}
	}
}
