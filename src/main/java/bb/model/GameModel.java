package bb.model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by willie on 6/4/17.
 */
public class GameModel {
	private static final int INIT_NUM_OBSTACLES = 15;
	
	private final Player player;
	private final List<Obstacle> obstacles = new LinkedList<>();
	
	private boolean gameRunning;
	
	public GameModel() {
		this.player = new Player(this);
		
		for (int i = 0; i < INIT_NUM_OBSTACLES; i++) {
			obstacles.add(new Obstacle(this));
		}
		
		this.gameRunning = true;
	}

	public Player getPlayer() {
		return player;
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public void update() {
		if (gameRunning) {
			updateEntities();
			boolean collision = checkCollisions();
			if (collision) {
				this.gameRunning = false;
			}
		}
	}
	
	private void updateEntities() {
		player.update();
		obstacles.forEach(obstacle -> obstacle.update());
	}
	
	private boolean checkCollisions() {
		for (ListIterator<Obstacle> it = obstacles.listIterator(); it.hasNext();) {
			Obstacle obstacle = it.next();
			if (player.collision(obstacle)) {
				return true;
			}
		}
		return false;
	}
}
