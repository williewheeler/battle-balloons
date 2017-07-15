package bb.game.level;

/**
 * Created by willie on 7/4/17.
 */
public class Level {
	private int obstacles;
	private int judos;
	private int bullies;
	private int dogs;

	public Level(int judos, int obstacles, int dogs, int bullies) {
		this.obstacles = obstacles;
		this.judos = judos;
		this.bullies = bullies;
		this.dogs = dogs;
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

	public int getDogs() {
		return dogs;
	}
}
