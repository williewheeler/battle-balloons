package io.halfling.world.event;

import io.halfling.core.Assert;

/**
 * Event representing something that occurred in the game itself. Publishers must fire this event <em>after</em> the
 * associated change occurs, not before.
 *
 * Created by wwheeler on 6/13/17.
 */
public class GameEvent {
	
	// Using a string because it's easier to read in trace logs. [WLW]
	private String type;

	public GameEvent(String type) {
		Assert.notNull(type, "type can't be null");
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
