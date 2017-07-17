package com.williewheeler.bb.common.actor.view;

import com.williewheeler.bb.common.actor.model.Lexi;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.bb.common.BBConfig;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.model.Direction;
import com.williewheeler.retroge.actor.view.AbstractActorView;
import com.williewheeler.retroge.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by willie on 6/24/17.
 */
public class LexiView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public LexiView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	// TODO Consolidate paintEntering() and paintExiting(), as they're nearly identical. [WLW]
	// TODO Consolidate across entities, as (e.g.) Lexi and Judo have almost identical methods here. [WLW]
	@Override
	public void paintEntering(Graphics g, Actor actor) {
		final Lexi lexi = (Lexi) actor;
		final int spriteIndex = lexi.getEnterTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getLexiEntering()[spriteIndex];

		final int xOffset = lexi.getX() - sprite.getWidth() / 2;
		final int yOffset = lexi.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Lexi lexi = (Lexi) actor;
		final int xOffset = lexi.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = lexi.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getCurrentActiveSprite(lexi);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintExiting(Graphics g, Actor actor) {
		final Lexi lexi = (Lexi) actor;
		final int spriteIndex = Lexi.EXIT_TTL - lexi.getExitTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getLexiExiting()[spriteIndex];
		final int xOffset = lexi.getX() - sprite.getWidth() / 2;
		final int yOffset = lexi.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getCurrentActiveSprite(Lexi lexi) {
		int index = -1;
		switch (lexi.getSubstate()) {
			case BLINKING:
				index = (lexi.getEyesOpen() ? 0 : 1);
				return spriteFactory.getLexiBlinking()[index];
			case BATTLING:
				return getWalkingSprite(lexi);
			case WAVING:
				index = (lexi.getWavingLeft() ? 0 : 1);
				return spriteFactory.getLexiWaving()[index];
			default:
				return null;
		}
	}

	// TODO Might be an opportunity to move this (and corresponding code in JudoView) to some more central place.
	// For example it might be useful to treat walking as a component in an entity/component system. Or even just make a
	// superclass that knows how to deal with walking.
	private BufferedImage getWalkingSprite(Lexi lexi) {
		Direction direction = lexi.getDirection();
		int walkCounter = lexi.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);

		// For fun
//		return spriteFactory.getDogWalking()[walkIndex];
		return spriteFactory.getLexiWalking()[walkIndex];
	}
}
