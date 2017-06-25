package bb.common.view.actor;

import bb.common.model.LexiModel;
import bb.framework.view.actor.AbstractActorView;

import java.awt.image.BufferedImage;

/**
 * Created by willie on 6/24/17.
 */
public class
LexiView extends AbstractActorView {
	private BufferedImage[] walkingSprites;
	private BufferedImage[] blinkingSprites;
	private BufferedImage[] wavingSprites;

	public LexiView(LexiModel model) {
		super(model, 2);
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

	@Override
	public BufferedImage getCurrentSprite() {

		// TODO Use generics?
		LexiModel model = (LexiModel) getModel();

		int index = -1;
		switch (model.getState()) {
			case WALKING:
				index = 1;
				return walkingSprites[index];
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
