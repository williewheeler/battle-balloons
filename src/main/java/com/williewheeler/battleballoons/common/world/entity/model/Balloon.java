package com.williewheeler.battleballoons.common.world.entity.model;

import io.halfling.world.entity.model.AbstractActor;
import io.halfling.world.entity.model.ActorLifecycleState;

import static io.halfling.GameConfig.WORLD_SIZE;

/**
 * Created by willie on 7/3/17.
 */
public class Balloon extends AbstractActor {
	public static final int SPEED = 5;
	public static final int EXIT_TTL = 7;

	private static final int WIDTH = 3;
	private static final int HEIGHT = 3;
	
	private int exitTtl = EXIT_TTL;
	
	// TODO This mechanism is different than the direction mechanism.
	// Choose one or the other. (I kind of like dx, dy better.) [WLW]
	private int dx;
	private int dy;

	public Balloon(int x, int y, int dx, int dy) {
		super(null, x, y, WIDTH, HEIGHT);
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getExitTtl() {
		return exitTtl;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}

	@Override
	public void updateBodyActive() {
		changeX(dx);
		changeY(dy);
		checkGC();
	}
	
	@Override
	public void updateBodyExiting() {
		this.exitTtl--;
		if (exitTtl <= 0) {
			this.exitTtl = EXIT_TTL;
			setState(ActorLifecycleState.GONE);
		}
	}
	
	private void checkGC() {
		final int x = getX();
		final int y = getY();

		if (x < 0 || x > WORLD_SIZE.width || y < 0 || y > WORLD_SIZE.height) {
			setState(ActorLifecycleState.EXITING);
		}
	}
}
