package bb.common.event;

import bb.framework.event.ActorEvent;

/**
 * Created by willie on 6/3/17.
 */
public class ActorEvents {
	public static final ActorEvent PLAYER_WALKS = new ActorEvent();
	public static final ActorEvent PLAYER_THROWS_BALLOON = new ActorEvent();
	public static final ActorEvent PLAYER_COLLISION = new ActorEvent();
	public static final ActorEvent JUDO_HIT = new ActorEvent();
}
