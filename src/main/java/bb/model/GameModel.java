package bb.model;

import static bb.BBConfig.ARENA_INNER_HEIGHT_PX;
import static bb.BBConfig.ARENA_INNER_WIDTH_PX;

/**
 * Created by willie on 6/4/17.
 */
public class GameModel {
	private final Player player = new Player();

	public Player getPlayer() { return player; }

	public void update() {
		// TODO: We need to update the player whenever the game updates.
	}
}
