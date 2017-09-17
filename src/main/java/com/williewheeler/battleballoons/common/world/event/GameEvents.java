package com.williewheeler.battleballoons.common.world.event;

import io.halfling.world.event.GameEvent;

/**
 * Created by willie on 6/3/17.
 */
public class GameEvents {
	public static final GameEvent NEXT_LEVEL = new GameEvent("nextLevel");
	public static final GameEvent GAME_OVER = new GameEvent("gameOver");

	public static final GameEvent PLAYER_WALKED = new GameEvent("playerWalked");
	public static final GameEvent PLAYER_THREW_BALLOON = new GameEvent("playerThrewBalloon");
	public static final GameEvent PLAYER_DIED = new GameEvent("playerDied");
	public static final GameEvent PLAYER_GONE = new GameEvent("playerGone");

	public static final GameEvent BEAT_PLAYED = new GameEvent("beatPlayed");
	public static final GameEvent TEACHERS = new GameEvent("teachers");

	public static final GameEvent ANIMAL_RESCUED = new GameEvent("animalRescued");
	public static final GameEvent ANIMAL_DIED = new GameEvent("animalDied");

	public static final GameEvent OBSTACLE_DESTROYED = new GameEvent("obstacleDestroyed");

	public static final GameEvent JUDO_DIED = new GameEvent("judoDied");
	public static final GameEvent BENGY_DIED = new GameEvent("bengyDied");
	public static final GameEvent YARD_DUTY_DIED = new GameEvent("yardDutyDied");
	public static final GameEvent TEACHER_DIED = new GameEvent("teacherDied");
}
