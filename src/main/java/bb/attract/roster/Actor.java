package bb.attract.roster;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.BBConfig.SPRITE_HEIGHT_PX;
import static bb.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/21/17.
 */
public class Actor {
	private ActorModel model;
	private BufferedImage[] walkingSprites;
	private BufferedImage[] blinkingSprites;
	private BufferedImage[] wavingSprites;

	public Actor(ActorModel model) {
		this.model = model;
	}

	public void setWalkingSprites(BufferedImage[] walkingSprites) {
		this.walkingSprites = walkingSprites;
	}

	public void setBlinkingSprites(BufferedImage[] blinkingSprites) {
		this.blinkingSprites = blinkingSprites;
	}

	public void setWavingSprites(BufferedImage[] wavingSprites) {
		this.wavingSprites = wavingSprites;
	}

	public void paint(Graphics g) {
		BufferedImage sprite = getCurrentSprite();
		int adjX = model.getX() - SPRITE_WIDTH_PX;
		int adjY = model.getY() - SPRITE_HEIGHT_PX;
		int width = 2 * SPRITE_WIDTH_PX;
		int height = 2 * SPRITE_HEIGHT_PX;
		g.drawImage(sprite, adjX, adjY, width, height, null);
	}

	private BufferedImage getCurrentSprite() {
		switch (model.getBehavior()) {
			case BLINKING:
				int index = (model.getEyesOpen() ? 0 : 1);
				return blinkingSprites[index];
			case WALKING:
				return null;
			case WAVING:
				return null;
			default:
				return null;
		}
	}
}
