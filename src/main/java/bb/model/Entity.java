package bb.model;

/**
 * Created by wwheeler on 6/11/17.
 */
public interface Entity {
	
	int getX();
	
	int getY();
	
	int getWidth();
	
	int getHeight();
	
	Direction getDirection();
	
	int getAnimationCounter();
	
	void update();
}
