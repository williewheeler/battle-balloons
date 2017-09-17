package com.williewheeler.battleballoons.common.world.entity.view;

import com.williewheeler.battleballoons.common.world.entity.model.Balloon;
import io.halfling.world.entity.model.Actor;
import io.halfling.world.entity.view.ActorView;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by willie on 7/3/17.
 */
public class BalloonView implements ActorView {

	@Override
	public void paint(Graphics g, Actor actor) {
		switch (actor.getState()) {
			case ACTIVE:
				paintActive(g, actor);
				break;
			case EXITING:
				paintExiting(g, actor);
				break;
		}
	}
	
	private void paintActive(Graphics g, Actor actor) {
		final Balloon balloon = (Balloon) actor;
		final int halfWidth = balloon.getWidth() / 2;
		final int halfHeight = balloon.getHeight() / 2;
		final int xOffset = balloon.getX() - halfWidth;
		final int yOffset = balloon.getY() - halfHeight;
		
		g.translate(xOffset, yOffset);
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, balloon.getWidth(), balloon.getHeight());
		g.translate(-xOffset, -yOffset);
	}
	
	private void paintExiting(Graphics g, Actor actor) {
		final Balloon balloon = (Balloon) actor;
		final int splashRadius = Balloon.EXIT_TTL - balloon.getExitTtl();
		final int xOffset = balloon.getX() - splashRadius;
		final int yOffset = balloon.getY() - splashRadius;
		
		g.translate(xOffset, yOffset);
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 2, 2);
		g.fillRect(2 * splashRadius, 0, 2, 2);
		g.fillRect(0, 2 * splashRadius, 2, 2);
		g.fillRect(2 * splashRadius, 2 * splashRadius, 2, 2);
		g.translate(-xOffset, -yOffset);
	}
}
