package bb.model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by willie on 6/4/17.
 */
public class GameModel {
	private static final int INIT_NUM_OBSTACLES = 15;
	private static final int INIT_NUM_JUDOS = 10;
	
	private final Player player;
	private final List<Obstacle> obstacles = new LinkedList<>();
	private final List<Judo> judos = new LinkedList<>();
	
	private boolean gameRunning;
	
	public GameModel() {
		this.player = new Player(this);
		
		for (int i = 0; i < INIT_NUM_OBSTACLES; i++) {
			obstacles.add(new Obstacle(this));
		}
		
		for (int i = 0; i < INIT_NUM_JUDOS; i++) {
			judos.add(new Judo(this));
		}
		
		this.gameRunning = true;
	}

	public Player getPlayer() {
		return player;
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public List<Judo> getJudos() {
		return judos;
	}

	public void update() {
		if (gameRunning) {
			updateEntities();
			boolean playerDead = checkCollisions();
			if (playerDead) {
				this.gameRunning = false;
			}
		}
	}
	
	private void updateEntities() {
		player.update();
		obstacles.forEach(obstacle -> obstacle.update());
		judos.forEach(judo -> judo.update());
	}
	
	/**
	 * @return boolean indicating whether the player died
	 */
	private boolean checkCollisions() {
		checkJudoObstacleCollisions();
		
		if (checkPlayerObstacleCollision()) {
			return true;
		}
		
		if (checkPlayerJudoCollision()) {
			return true;
		}
		
		return false;
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
					jit.remove();
					oit.remove();
					continue checkJudos;
				}
			}
		}
	}
	
	private boolean checkPlayerObstacleCollision() {
		for (ListIterator<Obstacle> it = obstacles.listIterator(); it.hasNext();) {
			Obstacle obstacle = it.next();
			if (player.collision(obstacle)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkPlayerJudoCollision() {
		for (ListIterator<Judo> it = judos.listIterator(); it.hasNext();) {
			Judo judo = it.next();
			if (player.collision(judo)) {
				return true;
			}
		}
		return false;
	}
}
