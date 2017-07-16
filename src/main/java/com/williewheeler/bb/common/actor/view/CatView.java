package com.williewheeler.bb.common.actor.view;

// TODO We should probably be able to use a generic sprite view here.
// See e.g. BullyView and the other animal views. [WLW]

import com.williewheeler.bb.common.BBConfig;
import com.williewheeler.bb.common.actor.model.Cat;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.retroge.actor.Actor;
import com.williewheeler.retroge.actor.Direction;
import com.williewheeler.retroge.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

// TODO DRY this up. It is almost identical to DogView. [WLW]

/**
 * Created by willie on 7/6/17.
 */
public class CatView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public CatView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Cat cat = (Cat) actor;
		final int xOffset = cat.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = cat.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getWalkingSprite(cat);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(Cat cat) {
		Direction direction = cat.getDirection();
		int walkCounter = cat.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getCatWalking()[walkIndex];
	}
}
