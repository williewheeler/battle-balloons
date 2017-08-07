package com.williewheeler.bb.common.actor.view;

import com.williewheeler.bb.common.BBConfig;
import com.williewheeler.bb.common.actor.model.Turntables;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.view.AbstractActorView;
import com.williewheeler.retroge.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TurntablesView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public TurntablesView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Turntables turntables = (Turntables) actor;
		final int xOffset = turntables.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = turntables.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getTurntablesSprite(turntables);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getTurntablesSprite(Turntables turntables) {
		BufferedImage[] turntablesSprites = spriteFactory.getTurntables();
		int index = turntables.isBlinkOn() ? 1 : 0;
		return turntablesSprites[index];
	}
}
