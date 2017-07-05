package bb.common.event;

import bb.framework.event.GameEvent;

/**
 * Created by willie on 6/3/17.
 */
public class GameEvents {
	public static final GameEvent NEXT_LEVEL = new GameEvent();
	public static final GameEvent GAME_OVER = new GameEvent();

	public static final GameEvent PLAYER_WALKS = new GameEvent();
	public static final GameEvent PLAYER_THROWS_BALLOON = new GameEvent();
	public static final GameEvent PLAYER_COLLISION = new GameEvent();

	public static final GameEvent JUDO_HIT = new GameEvent();
}
