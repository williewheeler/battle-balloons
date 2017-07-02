package bb.game.arena.model;

/**
 * Created by willie on 6/9/17.
 */
public class DirectionIntent {
	public boolean up, down, left, right;

	public void reset() {
		this.up = this.down = this.left = this.right = false;
	}
}
