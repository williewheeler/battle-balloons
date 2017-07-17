package com.williewheeler.bb.common.actor.view;

import com.williewheeler.bb.common.BBConfig;
import com.williewheeler.bb.common.actor.model.Parrot;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.retroge.actor.view.AbstractActorView;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.model.Direction;
import com.williewheeler.retroge.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by willie on 7/16/17.
 */
public class ParrotView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public ParrotView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Parrot parrot = (Parrot) actor;
		final int xOffset = parrot.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = parrot.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getWalkingSprite(parrot);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(Parrot parrot) {
		Direction direction = parrot.getDirection();
		int walkCounter = parrot.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getParrotWalking()[walkIndex];
	}
}
