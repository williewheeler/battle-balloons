package com.williewheeler.bb.common.actor.view;

import com.williewheeler.bb.common.actor.model.Balloon;
import com.williewheeler.retroge.actor.Actor;
import com.williewheeler.retroge.actor.ActorView;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by willie on 7/3/17.
 */
public class BalloonView implements ActorView {

	@Override
	public void paint(Graphics g, Actor actor) {
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
}
