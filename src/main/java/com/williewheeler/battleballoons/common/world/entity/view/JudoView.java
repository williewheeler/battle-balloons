package com.williewheeler.battleballoons.common.world.entity.view;

import com.williewheeler.battleballoons.common.world.entity.model.Judo;
import com.williewheeler.battleballoons.common.view.resource.SpriteFactory;
import com.williewheeler.battleballoons.common.BBConfig;
import io.halfling.world.entity.model.Actor;
import io.halfling.world.entity.model.Direction;
import io.halfling.world.entity.view.AbstractActorView;
import io.halfling.core.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by willie on 7/2/17.
 */
public class JudoView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public JudoView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintEntering(Graphics g, Actor actor) {
		final Judo judo = (Judo) actor;
		final int spriteIndex = judo.getEnterTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getJudoEntering()[spriteIndex];
		final int xOffset = judo.getX() - sprite.getWidth() / 2;
		final int yOffset = judo.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Judo judo = (Judo) actor;
		final int xOffset = judo.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = judo.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getWalkingSprite(judo);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintExiting(Graphics g, Actor actor) {
		final Judo judo = (Judo) actor;
		final int spriteIndex = Judo.EXIT_TTL - judo.getExitTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getJudoExiting()[spriteIndex];
		final int xOffset = judo.getX() - sprite.getWidth() / 2;
		final int yOffset = judo.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(Judo judo) {
		Direction direction = judo.getDirection();
		int walkCounter = judo.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getJudoWalking()[walkIndex];
	}
}
