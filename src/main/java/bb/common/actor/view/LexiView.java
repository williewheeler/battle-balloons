package bb.common.actor.view;

import bb.framework.actor.Direction;
import bb.common.actor.model.Lexi;
import bb.framework.actor.Actor;
import bb.framework.actor.ActorView;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.common.BBConfig.SPRITE_HEIGHT_PX;
import static bb.common.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/24/17.
 */
public class LexiView implements ActorView {
	private static final int HALF_SPRITE_WIDTH = SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = SPRITE_HEIGHT_PX / 2;

	private BufferedImage[] walkingSprites;
	private BufferedImage[] blinkingSprites;
	private BufferedImage[] wavingSprites;

	public void setWalkingSprites(BufferedImage[] walkingSprites) {
		this.walkingSprites = walkingSprites;
	}

	public void setBlinkingSprites(BufferedImage[] blinkingSprites) {
		this.blinkingSprites = blinkingSprites;
	}

	public void setWavingSprites(BufferedImage[] wavingSprites) {
		this.wavingSprites = wavingSprites;
	}

	@Override
	public void paint(Graphics g, Actor actor) {
		final Lexi lexi = (Lexi) actor;
		final int xOffset = lexi.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = lexi.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getCurrentSprite(lexi);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getCurrentSprite(Lexi lexi) {
		int index = -1;
		switch (lexi.getState()) {
			case BLINKING:
				index = (lexi.getEyesOpen() ? 0 : 1);
				return blinkingSprites[index];
			case WALKING:
				return getWalkingSprite(lexi);
			case WAVING:
				index = (lexi.getWavingLeft() ? 0 : 1);
				return wavingSprites[index];
			default:
				return null;
		}
	}

	private BufferedImage getWalkingSprite(Lexi lexi) {
		Direction direction = lexi.getDirection();
		int walkCounter = lexi.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return walkingSprites[walkIndex];
	}
}
