package bb.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by willie on 6/4/17.
 */
public class GameModel {
	private static final int INIT_NUM_OBSTACLES = 15;
	
	private final Player player;
	private final List<Obstacle> obstacles = new LinkedList<>();
	
	public GameModel() {
		this.player = new Player(this);
		
		for (int i = 0; i < INIT_NUM_OBSTACLES; i++) {
			obstacles.add(new Obstacle(this));
		}
	}

	public Player getPlayer() {
		return player;
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public void update() {
		player.update();
		obstacles.forEach(obstacle -> obstacle.update());
	}
}
