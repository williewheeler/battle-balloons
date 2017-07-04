package bb.common.actor.view;

import bb.common.actor.model.Judo;
import bb.common.resource.SpriteFactory;
import bb.framework.actor.Actor;
import bb.framework.actor.Direction;
import bb.framework.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.common.BBConfig.SPRITE_HEIGHT_PX;
import static bb.common.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 7/2/17.
 */
public class JudoView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public JudoView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintEntering(Graphics g, Actor actor) {
		final Judo judo = (Judo) actor;
		final int spriteIndex = judo.getEnterTtl();
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
		g.drawImage(sprite, 0, 0, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintExiting(Graphics g, Actor actor) {
		final Judo judo = (Judo) actor;
		final int spriteIndex = (Judo.EXIT_TTL - 1) - judo.getExitTtl();
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
