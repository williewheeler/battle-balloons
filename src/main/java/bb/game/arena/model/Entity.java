package bb.game.arena.model;

import bb.game.arena.event.EntityState;
import bb.framework.actor.Direction;

/**
 * Created by wwheeler on 6/11/17.
 */
public interface Entity {
	
	EntityState getState();
	
	int getX();
	
	int getY();
	
	int getWidth();
	
	int getHeight();
	
	Direction getDirection();
	
	int getAnimationCounter();
	
	void update();
}
