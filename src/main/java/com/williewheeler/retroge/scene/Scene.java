package com.williewheeler.retroge.scene;

import com.williewheeler.retroge.actor.Player;
import com.williewheeler.retroge.event.GameEvent;

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
