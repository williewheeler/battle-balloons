package io.halfling.world.collision;

import io.halfling.world.entity.model.Player;
import io.halfling.world.event.GameEvent;

/**
 * Physics engine interface.
 *
 * Created by wwheeler on 7/8/17.
 */
public interface Scene {
	
	boolean isActive();
	
	int getMinWorldX();
	
	int getMaxWorldX();
	
	int getMinWorldY();
	
	int getMaxWorldY();
	
	Player getPlayer();
	
	void update();
	
	void fireGameEvent(GameEvent event);
}
