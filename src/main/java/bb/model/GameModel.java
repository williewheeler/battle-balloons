package bb.model;

/**
 * Created by willie on 6/4/17.
 */
public class GameModel {
	private final Player player = new Player();

	public Player getPlayer() {
		return player;
	}

	public void update() {
		player.update();
	}
}
