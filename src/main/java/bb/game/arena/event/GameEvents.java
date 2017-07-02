package bb.game.arena.event;

import bb.framework.event.GameEvent;

/**
 * Created by willie on 6/3/17.
 */
public class GameEvents {

	// FIXME These should be available from attract mode too.
	public static final GameEvent PLAYER_WALKS = new GameEvent();
	public static final GameEvent PLAYER_THROWS_BALLOON = new GameEvent();
	public static final GameEvent PLAYER_COLLISION = new GameEvent();
	public static final GameEvent JUDO_HIT = new GameEvent();
}
