package bb.common.view;

import bb.common.model.LexiModel;
import bb.framework.util.Assert;
import bb.framework.view.ActorView;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.BBConfig.SPRITE_HEIGHT_PX;
import static bb.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/24/17.
 */
public class LexiView implements ActorView {
	private LexiModel model;
	private BufferedImage[] blinkingSprites;
	private BufferedImage[] wavingSprites;

	public LexiView(LexiModel model) {
		Assert.notNull(model, "model can't be null");
		this.model = model;
	}

	public void setBlinkingSprites(BufferedImage[] blinkingSprites) {
		this.blinkingSprites = blinkingSprites;
	}

	public void setWavingSprites(BufferedImage[] wavingSprites) {
		this.wavingSprites = wavingSprites;
	}

	// TODO Consider moving this to an abstract base class. [WLW]
	@Override
	public void paint(Graphics g) {
		BufferedImage sprite = getCurrentSprite();
		int adjX = model.getX() - SPRITE_WIDTH_PX;
		int adjY = model.getY() - SPRITE_HEIGHT_PX;
		int width = 2 * SPRITE_WIDTH_PX;
		int height = 2 * SPRITE_HEIGHT_PX;
		g.drawImage(sprite, adjX, adjY, width, height, null);
	}

	private BufferedImage getCurrentSprite() {
		int index = -1;
		switch (model.getState()) {
			case BLINKING:
				index = (model.getEyesOpen() ? 0 : 1);
				return blinkingSprites[index];
			case WAVING:
				index = (model.getWavingLeft() ? 0 : 1);
				return wavingSprites[index];
			default:
				return null;
		}
	}
}
