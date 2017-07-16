package com.williewheeler.bb.common.actor.view;

// TODO We should probably be able to use a generic sprite view here.
// See e.g. BullyView and the other animal views. [WLW]

import com.williewheeler.bb.common.actor.model.Bully;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.bb.common.BBConfig;
import com.williewheeler.retroge.actor.Actor;
import com.williewheeler.retroge.actor.Direction;
import com.williewheeler.retroge.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by willie on 7/5/17.
 */
public class BullyView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public BullyView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Bully bully = (Bully) actor;
		final int xOffset = bully.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = bully.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getWalkingSprite(bully);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(Bully bully) {
		Direction direction = bully.getDirection();
		int walkCounter = bully.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getBullyWalking()[walkIndex];
	}
}
