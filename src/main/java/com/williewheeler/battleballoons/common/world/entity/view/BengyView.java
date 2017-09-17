package com.williewheeler.battleballoons.common.world.entity.view;

import com.williewheeler.battleballoons.common.BBConfig;
import com.williewheeler.battleballoons.common.world.entity.model.Bengy;
import com.williewheeler.battleballoons.common.view.resource.SpriteFactory;
import io.halfling.world.entity.model.Actor;
import io.halfling.world.entity.model.Direction;
import io.halfling.world.entity.view.AbstractActorView;
import io.halfling.core.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

// TODO Consolidate this with JudoView. [WLW]

/**
 * Created by willie on 7/16/17.
 */
public class BengyView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public BengyView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintEntering(Graphics g, Actor actor) {
		final Bengy bengy = (Bengy) actor;
		final int spriteIndex = bengy.getEnterTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getBengyEntering()[spriteIndex];
		final int xOffset = bengy.getX() - sprite.getWidth() / 2;
		final int yOffset = bengy.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Bengy bengy = (Bengy) actor;
		final int xOffset = bengy.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = bengy.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getWalkingSprite(bengy);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintExiting(Graphics g, Actor actor) {
		final Bengy bengy = (Bengy) actor;
		final int spriteIndex = Bengy.EXIT_TTL - bengy.getExitTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getBengyExiting()[spriteIndex];
		final int xOffset = bengy.getX() - sprite.getWidth() / 2;
		final int yOffset = bengy.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(Bengy bengy) {
		Direction direction = bengy.getDirection();
		int walkCounter = bengy.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getBengyWalking()[walkIndex];
	}
}
