package bb.framework.model;

/**
 * Actor's sensorimotor model.
 *
 * Created by willie on 6/25/17.
 */
public interface ActorBrain {

	boolean moveUp();

	boolean moveDown();

	boolean moveLeft();

	boolean moveRight();

	void update();
}
