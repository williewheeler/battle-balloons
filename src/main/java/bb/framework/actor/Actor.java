package bb.framework.actor;

/**
 * Created by willie on 6/24/17.
 */
public interface Actor {

	int getX();

	int getY();

	int getWidth();

	int getHeight();

	int getSpeed();

	Direction getDirection();

	void update();

	/**
	 * Boolean indicating whether the actor is ready for garbage collection.
	 *
	 * @return
	 */
	boolean isReadyForGC();
}
