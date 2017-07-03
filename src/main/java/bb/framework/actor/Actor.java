package bb.framework.actor;

/**
 * Created by willie on 6/24/17.
 */
public interface Actor {

	ActorBrain getBrain();

	int getX();

	void setX(int x);

	int getY();

	void setY(int y);

	int getWidth();

	int getHeight();

	int getSpeed();

	Direction getDirection();

	/**
	 * Boolean indicating whether the actor is ready for garbage collection.
	 *
	 * @return
	 */
	boolean isReadyForGC();

	void update();
}
