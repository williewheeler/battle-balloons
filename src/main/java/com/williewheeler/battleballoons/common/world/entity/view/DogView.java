package com.williewheeler.battleballoons.common.world.entity.view;

// TODO We should probably be able to use a generic sprite view here.
// See e.g. BullyView and the other animal views. [WLW]

import com.williewheeler.battleballoons.common.world.entity.model.Dog;
import com.williewheeler.battleballoons.common.view.resource.SpriteFactory;
import com.williewheeler.battleballoons.common.BBConfig;
import io.halfling.world.entity.model.Actor;
import io.halfling.world.entity.model.Direction;
import io.halfling.world.entity.view.AbstractActorView;
import io.halfling.core.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by willie on 7/6/17.
 */
public class DogView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public DogView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Dog dog = (Dog) actor;
		final int xOffset = dog.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = dog.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getWalkingSprite(dog);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(Dog dog) {
		Direction direction = dog.getDirection();
		int walkCounter = dog.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getDogWalking()[walkIndex];
	}
}
