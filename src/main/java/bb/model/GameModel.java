package bb.model;

import static bb.BBConfig.ARENA_INNER_HEIGHT_PX;
import static bb.BBConfig.ARENA_INNER_WIDTH_PX;

/**
 * Created by willie on 6/4/17.
 */
public class GameModel {
	private Player player;

	public GameModel() {
		this.player = new Player();
		player.setX(ARENA_INNER_WIDTH_PX / 2);
		player.setY(ARENA_INNER_HEIGHT_PX / 2);
	}

	public Player getPlayer() {
		return player;
	}
}
