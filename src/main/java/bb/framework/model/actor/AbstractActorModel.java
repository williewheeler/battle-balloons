package bb.framework.model.actor;

/**
 * Actor data relevant to actual gameplay, such as location and size. Excludes data purely view-related data, such as
 * whether the actor's eyes are open or whether the actor's tapping foot is up vs. down.
 *
 * Created by willie on 6/19/17.
 */
public abstract class AbstractActorModel implements ActorModel {
	private int x;
	private int y;

	public AbstractActorModel(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void update() {
		// TODO This would be both state machine and associated data
	}
}
