package bb.model;

import bb.model.event.EntityState;
import bb.model.event.GameEvent;
import bb.model.event.GameEvents;
import bb.model.event.GameListener;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by willie on 6/4/17.
 */
public class GameModel {
	private static final int INIT_NUM_OBSTACLES = 15;
	private static final int INIT_NUM_JUDOS = 50;
	
	private final List<GameListener> gameListeners = new LinkedList<>();
	
	private final Player player;
	private final List<Balloon> playerBalloons = new LinkedList<>();
	private final List<Obstacle> obstacles = new LinkedList<>();
	private final List<Judo> judos = new LinkedList<>();
	
	public GameModel() {
		this.player = new Player(this);
		
		for (int i = 0; i < INIT_NUM_OBSTACLES; i++) {
			obstacles.add(new Obstacle(this));
		}
		
		for (int i = 0; i < INIT_NUM_JUDOS; i++) {
			judos.add(new Judo(this));
		}
	}
	
	public void addGameListener(GameListener listener) {
		gameListeners.add(listener);
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

	public void update() {
		if (player.getState() != EntityState.GONE) {
			updateEntities();
			boolean playerDead = checkCollisions();
			if (playerDead) {
				player.setState(EntityState.EXITING);
				fireEvent(GameEvents.PLAYER_COLLISION);
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
	
	/**
	 * @return boolean indicating whether the player died
	 */
	private boolean checkCollisions() {
		checkPlayerBalloonCollisions();
		checkJudoObstacleCollisions();
		
		if (checkPlayerObstacleCollision()) {
			return true;
		}
		
		if (checkPlayerJudoCollision()) {
			return true;
		}
		
		return false;
	}
	
	private void checkPlayerBalloonCollisions() {
		checkBalloons:
		for (ListIterator<Balloon> bit = playerBalloons.listIterator(); bit.hasNext();) {
			Balloon balloon = bit.next();
			for (ListIterator<Obstacle> oit = obstacles.listIterator(); oit.hasNext();) {
				Obstacle obstacle = oit.next();
				if (balloon.collision(obstacle)) {
					player.incrementScore(Obstacle.SCORE);
					bit.remove();
					oit.remove();
					continue checkBalloons;
				}
			}
			for (ListIterator<Judo> jit = judos.listIterator(); jit.hasNext();) {
				Judo judo = jit.next();
				if (judo.getState() == EntityState.ACTIVE && balloon.collision(judo)) {
					player.incrementScore(Judo.SCORE);
					judo.setState(EntityState.EXITING);
					bit.remove();
					continue checkBalloons;
				}
			}
		}
	}
	
	private void checkJudoObstacleCollisions() {
		checkJudos:
		for (ListIterator<Judo> jit = judos.listIterator(); jit.hasNext();) {
			Judo judo = jit.next();
			for (ListIterator<Obstacle> oit = obstacles.listIterator(); oit.hasNext();) {
				Obstacle obstacle = oit.next();
				if (judo.collision(obstacle)) {
					player.incrementScore(Judo.SCORE);
					player.incrementScore(Obstacle.SCORE);
					judo.setState(EntityState.EXITING);
					oit.remove();
					continue checkJudos;
				}
			}
		}
	}
	
	private boolean checkPlayerObstacleCollision() {
		if (player.getState() == EntityState.ACTIVE) {
			for (ListIterator<Obstacle> it = obstacles.listIterator(); it.hasNext(); ) {
				Obstacle obstacle = it.next();
				if (player.collision(obstacle)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkPlayerJudoCollision() {
		if (player.getState() == EntityState.ACTIVE) {
			for (ListIterator<Judo> it = judos.listIterator(); it.hasNext(); ) {
				Judo judo = it.next();
				if (judo.getState() == EntityState.ACTIVE && player.collision(judo)) {
					return true;
				}
			}
		}
		return false;
	}
	
	protected void fireEvent(GameEvent event) {
		gameListeners.forEach(listener -> listener.handleEvent(event));
	}
}
