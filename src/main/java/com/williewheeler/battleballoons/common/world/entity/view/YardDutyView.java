package com.williewheeler.battleballoons.common.world.entity.view;

import com.williewheeler.battleballoons.common.BBConfig;
import com.williewheeler.battleballoons.common.world.entity.model.YardDuty;
import com.williewheeler.battleballoons.common.view.resource.SpriteFactory;
import io.halfling.world.entity.model.Actor;
import io.halfling.world.entity.model.Direction;
import io.halfling.world.entity.view.AbstractActorView;
import io.halfling.core.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class YardDutyView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public YardDutyView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintEntering(Graphics g, Actor actor) {
		final YardDuty yardDuty = (YardDuty) actor;
		final int spriteIndex = yardDuty.getEnterTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getYardDutyEntering()[spriteIndex];
		final int xOffset = yardDuty.getX() - sprite.getWidth() / 2;
		final int yOffset = yardDuty.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final YardDuty yardDuty = (YardDuty) actor;
		final int xOffset = yardDuty.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = yardDuty.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getWalkingSprite(yardDuty);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintExiting(Graphics g, Actor actor) {
		final YardDuty yardDuty = (YardDuty) actor;
		final int spriteIndex = YardDuty.EXIT_TTL - yardDuty.getExitTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getYardDutyExiting()[spriteIndex];
		final int xOffset = yardDuty.getX() - sprite.getWidth() / 2;
		final int yOffset = yardDuty.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(YardDuty yardDuty) {
		Direction direction = yardDuty.getDirection();
		int walkCounter = yardDuty.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getYardDutyWalking()[walkIndex];
	}
}
