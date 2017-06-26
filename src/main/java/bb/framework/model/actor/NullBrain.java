package bb.framework.model.actor;

/**
 * Created by willie on 6/25/17.
 */
public class NullBrain implements ActorBrain {

	@Override
	public boolean moveUp() {
		return false;
	}

	@Override
	public boolean moveDown() {
		return false;
	}

	@Override
	public boolean moveLeft() {
		return false;
	}

	@Override
	public boolean moveRight() {
		return false;
	}

	@Override
	public void update() {
	}
}
