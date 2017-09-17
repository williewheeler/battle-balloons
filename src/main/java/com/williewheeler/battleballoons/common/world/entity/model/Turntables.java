package com.williewheeler.battleballoons.common.world.entity.model;

import com.williewheeler.battleballoons.game.world.entity.brain.DefaultTurntablesBrain;
import io.halfling.world.entity.model.AbstractActor;
import io.halfling.world.collision.Scene;

public class Turntables extends AbstractActor {
	private static final int WIDTH = 13;
	private static final int HEIGHT = 9;
	private static final int SPEED = 3;
	private static final int SCORE = 200;
	private static final int MAX_BLINK_TTL = 30;

	private int blinkTtl = -1;

	public Turntables(Scene scene, int x, int y) {
		super(new DefaultTurntablesBrain(), x, y, WIDTH, HEIGHT);
		setScene(scene);
		setSpeed(SPEED);
	}

	@Override
	public int getScore() {
		return SCORE;
	}

	public boolean isBlinkOn() {
		return blinkTtl > (MAX_BLINK_TTL / 2);
	}

	@Override
	public void updateBodyActive() {
		this.blinkTtl = (blinkTtl < 0 ? MAX_BLINK_TTL : blinkTtl - 1);
		doMove();
	}
}
