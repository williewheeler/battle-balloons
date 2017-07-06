package bb.game.arena.level;

/**
 * Created by willie on 7/4/17.
 */
public class Level {
	private int obstacles;
	private int judos;
	private int bullies;

	public Level(int obstacles, int judos, int bullies) {
		this.obstacles = obstacles;
		this.judos = judos;
		this.bullies = bullies;
	}

	public int getObstacles() {
		return obstacles;
	}

	public int getJudos() {
		return judos;
	}

	public int getBullies() {
		return bullies;
	}
}
