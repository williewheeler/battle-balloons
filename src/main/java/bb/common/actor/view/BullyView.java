package bb.common.actor.view;

// TODO We should probably be able to use a generic sprite view here.
// See e.g. BullyView and the other animal views. [WLW]

import bb.common.actor.model.Bully;
import bb.common.resource.SpriteFactory;
import retroge.actor.Actor;
import retroge.actor.Direction;
import retroge.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.common.BBConfig.SPRITE_HEIGHT_PX;
import static bb.common.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 7/5/17.
 */
public class BullyView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = SPRITE_HEIGHT_PX / 2;

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
		g.drawImage(sprite, 0, 0, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(Bully bully) {
		Direction direction = bully.getDirection();
		int walkCounter = bully.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getBullyWalking()[walkIndex];
	}
}
