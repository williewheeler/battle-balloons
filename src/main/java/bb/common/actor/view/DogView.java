package bb.common.actor.view;

// TODO We should probably be able to use a generic sprite view here.
// See e.g. BullyView and the other animal views. [WLW]

import bb.common.actor.model.Dog;
import bb.common.resource.SpriteFactory;
import retroge.actor.Actor;
import retroge.actor.Direction;
import retroge.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.common.BBConfig.SPRITE_HEIGHT_PX;
import static bb.common.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 7/6/17.
 */
public class DogView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = SPRITE_HEIGHT_PX / 2;

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
		g.drawImage(sprite, 0, 0, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(Dog dog) {
		Direction direction = dog.getDirection();
		int walkCounter = dog.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getDogWalking()[walkIndex];
	}
}
