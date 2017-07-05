package bb.common.event;

import bb.framework.event.GameEvent;

/**
 * Created by willie on 6/3/17.
 */
public class GameEvents {
	public static final GameEvent NEXT_LEVEL = new GameEvent("nextLevel");
	public static final GameEvent GAME_OVER = new GameEvent("gameOver");

	public static final GameEvent PLAYER_WALKS = new GameEvent("playerWalks");
	public static final GameEvent PLAYER_THROWS_BALLOON = new GameEvent("playerThrowsBalloon");
	public static final GameEvent PLAYER_DIES = new GameEvent("playerDies");

	public static final GameEvent OBSTACLE_DESTROYED = new GameEvent("obstacleDestroyed");

	public static final GameEvent JUDO_DIES = new GameEvent("judoDies");
}
