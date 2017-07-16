package com.williewheeler.bb.common.actor.view;

import com.williewheeler.bb.common.actor.model.Obstacle;
import com.williewheeler.retroge.actor.Actor;
import com.williewheeler.retroge.actor.ActorView;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by willie on 7/1/17.
 */
public class ObstacleView implements ActorView {

	@Override
	public void paint(Graphics g, Actor actor) {
		final Obstacle obstacle = (Obstacle) actor;
		final int halfWidth = obstacle.getWidth() / 2;
		final int halfHeight = obstacle.getHeight() / 2;
		final int xOffset = obstacle.getX() - halfWidth;
		final int yOffset = obstacle.getY() - halfHeight;

		g.translate(xOffset, yOffset);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, obstacle.getWidth(), obstacle.getHeight());
		g.translate(-xOffset, -yOffset);
	}
}
