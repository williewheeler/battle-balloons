package bb.framework.model.actor;

/**
 * Created by willie on 6/24/17.
 */
public interface ActorModel {

	int getX();

	void changeX(int deltaX);

	int getY();

	void changeY(int deltaY);

	void update();
}
