package bb.game.arena.level;

/**
 * Created by willie on 7/4/17.
 */
public class Level {
	private int obstacles;
	private int judos;

	public Level(int obstacles, int judos) {
		this.obstacles = obstacles;
		this.judos = judos;
	}

	public int getObstacles() {
		return obstacles;
	}

	public int getJudos() {
		return judos;
	}
}
