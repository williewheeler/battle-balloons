package bb.actor.lexi;

import bb.actor.ActorModel;
import bb.actor.ActorView;
import bb.core.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.BBConfig.SPRITE_HEIGHT_PX;
import static bb.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/24/17.
 */
public class LexiActorView implements ActorView {
	private ActorModel model;
	private LexiActorViewState viewState;

	private BufferedImage[] walkingSprites;
	private BufferedImage[] blinkingSprites;
	private BufferedImage[] wavingSprites;

	public LexiActorView(ActorModel model) {
		Assert.notNull(model, "model can't be null");
		this.model = model;
		this.viewState = new LexiActorViewState();
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

		// TODO Move these microstates into the view state.
		BufferedImage[] sprites;
		int index = -1;
		switch (model.getState()) {
			case BLINKING:
				index = (model.getEyesOpen() ? 0 : 1);
				return blinkingSprites[index];
			case WALKING:
				return null;
			case WAVING:
				index = (model.getWavingLeft() ? 0 : 1);
				return wavingSprites[index];
			default:
				return null;
		}
	}
}
