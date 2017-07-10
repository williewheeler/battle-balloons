package bb.framework.scene;

import bb.framework.actor.Player;

/**
 * Physics engine interface.
 *
 * Created by wwheeler on 7/8/17.
 */
public interface Scene {
	
	boolean isActive();
	
	Player getPlayer();
	
	void update();
}
