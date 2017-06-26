package bb.framework.model.actor;

import bb.common.model.Direction;

/**
 * Created by willie on 6/24/17.
 */
public interface ActorModel {

	int getX();

	int getY();

	int getSpeed();

	Direction getDirection();

	void update();
}
