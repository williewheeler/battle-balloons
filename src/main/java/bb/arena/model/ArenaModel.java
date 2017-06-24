package bb.arena.model;

import bb.arena.event.ArenaEvent;
import bb.arena.event.ArenaEvents;
import bb.arena.event.ArenaListener;
import bb.arena.event.EntityState;
import bb.framework.model.GameModel;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaModel implements GameModel {
	private static final int INIT_NUM_OBSTACLES = 15;
	private static final int INIT_NUM_JUDOS = 10;
	
	private final List<ArenaListener> arenaListeners = new LinkedList<>();
	
	private final Player player;
	private final List<Balloon> playerBalloons = new LinkedList<>();
	private final List<Obstacle> obstacles = new LinkedList<>();
	private final List<Judo> judos = new LinkedList<>();

	private CollisionDetector collisionDetector = new CollisionDetector(this);
	
	public ArenaModel() {
		this.player = new Player(this);
		
		for (int i = 0; i < INIT_NUM_OBSTACLES; i++) {
			obstacles.add(new Obstacle(this));
		}
		
		for (int i = 0; i < INIT_NUM_JUDOS; i++) {
			judos.add(new Judo(this));
		}
	}
	
	public void addGameListener(ArenaListener listener) {
		arenaListeners.add(listener);
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
	public boolean isActive() {
		// TODO Set this false when the game is over
		return true;
	}

	@Override
	public void update() {
		if (player.getState() != EntityState.GONE) {
			updateEntities();
			boolean playerDead = collisionDetector.checkCollisions();
			if (playerDead) {
				player.setState(EntityState.EXITING);
				fireEvent(ArenaEvents.PLAYER_COLLISION);
			}
		}
	}
	
	private void updateEntities() {
		player.update();
		obstacles.forEach(obstacle -> obstacle.update());
		judos.forEach(judo -> judo.update());
		playerBalloons.forEach(balloon -> balloon.update());
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
	protected void fireEvent(ArenaEvent event) {
		arenaListeners.forEach(listener -> listener.handleEvent(event));
	}
}
