package bb.common.event;

import retroge.event.GameEvent;

/**
 * Created by willie on 6/3/17.
 */
public class GameEvents {
	public static final GameEvent NEXT_LEVEL = new GameEvent("nextLevel");
	public static final GameEvent GAME_OVER = new GameEvent("gameOver");

	public static final GameEvent PLAYER_WALKED = new GameEvent("playerWalked");
	public static final GameEvent PLAYER_THREW_BALLOON = new GameEvent("playerThrewBalloon");
	public static final GameEvent PLAYER_DIED = new GameEvent("playerDies");
	public static final GameEvent PLAYER_GONE = new GameEvent("playerGone");

	public static final GameEvent ANIMAL_RESCUED = new GameEvent("animalRescued");
	public static final GameEvent ANIMAL_DIED = new GameEvent("animalDied");

	public static final GameEvent OBSTACLE_DESTROYED = new GameEvent("obstacleDestroyed");

	public static final GameEvent JUDO_DIED = new GameEvent("judoDies");
}
