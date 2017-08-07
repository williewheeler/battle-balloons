package com.williewheeler.bb.common.actor.view;

import com.williewheeler.bb.common.BBConfig;
import com.williewheeler.bb.common.actor.model.Beat;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.view.AbstractActorView;
import com.williewheeler.retroge.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BeatView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public BeatView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Beat beat = (Beat) actor;
		final int xOffset = beat.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = beat.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getSprite(beat);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getSprite(Beat beat) {
		BufferedImage[] sprites = spriteFactory.getBeat();
		int index = beat.getAnimationIndex();
		return sprites[index];
	}
}
