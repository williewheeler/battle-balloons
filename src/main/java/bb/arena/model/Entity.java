package bb.arena.model;

import bb.arena.event.EntityState;

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
